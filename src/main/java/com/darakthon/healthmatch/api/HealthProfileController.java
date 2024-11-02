package com.darakthon.healthmatch.api;

import static com.darakthon.healthmatch.utils.IdealValueCalculator.calculateBMI;
import static com.darakthon.healthmatch.utils.IdealValueCalculator.determineHealthGrade;

import com.darakthon.healthmatch.domain.HealthProfile;
import com.darakthon.healthmatch.dto.HealthProfileRequest;
import com.darakthon.healthmatch.dto.HealthProfileResponse;
import com.darakthon.healthmatch.dto.IdealValuesDTO;
import com.darakthon.healthmatch.service.HealthProfileService;
import com.darakthon.healthmatch.utils.IdealValueCalculator;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class HealthProfileController {

    private final HealthProfileService healthProfileService;

    @PostMapping("/profiles")
    public ResponseEntity<Map<String, Long>> postHealthProfile(@Valid @RequestBody HealthProfileRequest request,
                                                               HttpServletResponse response) {
        HealthProfile healthProfile = HealthProfile.builder().name(request.getName())
                .exerciseCount(request.getExerciseCount())
                .weight(request.getWeight())
                .height(request.getHeight())
                .smokeCount(request.getSmokeCount())
                .drinkCount(request.getDrinkCount())
                .build();
        Long id = healthProfileService.save(healthProfile);
        log.info("HealthProfile saved with ID: {}", id);

        ResponseCookie cookie = ResponseCookie.from("healthProfileId", String.valueOf(id))
                .path("/")
                .maxAge(7 * 24 * 60 * 60)
                .build();

        response.addHeader("Set-Cookie", cookie.toString());

        Map<String, Long> responseBody = Map.of("inviteeId", id);
        return new ResponseEntity<>(responseBody, HttpStatus.CREATED);
    }

    @GetMapping("/profiles/{id}")
    public ResponseEntity<HealthProfileResponse> getHealthProfile(@PathVariable Long id) {
        HealthProfile healthProfile = healthProfileService.findById(id);
        if (healthProfile == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        double bmi = calculateBMI(healthProfile.getWeight(), healthProfile.getHeight());
        double idealWeight = IdealValueCalculator.calculateIdealWeight(healthProfile.getHeight());
        int idealSmokeCount = IdealValueCalculator.IDEAL_SMOKE_COUNT;
        int idealExerciseDays = IdealValueCalculator.IDEAL_EXERCISE_COUNT;

        String healthGrade = determineHealthGrade(bmi, healthProfile);

        IdealValuesDTO idealValues = IdealValuesDTO.builder()
                .idealWeightKg(idealWeight)
                .idealDrinkingFrequencyPerWeek(idealSmokeCount)
                .idealWeeklyExerciseDays(idealExerciseDays)
                .build();

        HealthProfileResponse.ProfileDTO profileDTO = HealthProfileResponse.ProfileDTO.builder()
                .userId(String.valueOf(healthProfile.getId()))
                .name(healthProfile.getName())
                .heightCm(healthProfile.getHeight())
                .weightKg(healthProfile.getWeight())
                .drinkingFrequencyPerWeek(healthProfile.getSmokeCount())
                .weeklyExerciseDays(healthProfile.getExerciseCount())
                .healthGrade(healthGrade)
                .bmi(bmi)
                .idealValues(idealValues)
                .build();

        HealthProfileResponse responseDTO = HealthProfileResponse.builder()
                .profile(profileDTO)
                .build();

        log.info("HealthProfile retrieved with ID: {}", id);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

}
