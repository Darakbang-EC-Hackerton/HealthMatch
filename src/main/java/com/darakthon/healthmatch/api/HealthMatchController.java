package com.darakthon.healthmatch.api;

import com.darakthon.healthmatch.domain.HealthMatchResult;
import com.darakthon.healthmatch.domain.HealthProfile;
import com.darakthon.healthmatch.dto.HealthMatchResponse;
import com.darakthon.healthmatch.dto.MatchHistoryDTO;
import com.darakthon.healthmatch.dto.ProfileInfoDTO;
import com.darakthon.healthmatch.service.HealthMatchService;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class HealthMatchController {
    private final HealthMatchService healthMatchService;

    @GetMapping("/health-match-result")
    public ResponseEntity<String> postHealthMatch(@RequestParam Long inviterId,
                                                  @RequestParam Long inviteeId) {
        Long matchId = healthMatchService.saveMatch(inviterId, inviteeId);
        log.info("HealthMatch saved with ID: {}", matchId);
        return new ResponseEntity<>(String.valueOf(matchId), HttpStatus.CREATED);
    }

    @GetMapping("/health-match-result/{matchId}")
    public ResponseEntity<HealthMatchResponse> getHealthMatchResult(@PathVariable Long matchId) {
        HealthMatchResult healthMatchResult = healthMatchService.findHealthMatchById(matchId);

        HealthProfile winnerProfile = healthMatchResult.getWinnerProfile();
        HealthProfile loserProfile = healthMatchResult.getLoserProfile();

        ProfileInfoDTO winnerProfileInfo = ProfileInfoDTO.builder()
                .name(winnerProfile.getName())
                .height(winnerProfile.getHeight())
                .weight(winnerProfile.getWeight())
                .smokeCount(winnerProfile.getSmokeCount())
                .exerciseCount(winnerProfile.getExerciseCount())
                .drinkCount(winnerProfile.getDrinkCount())
                .build();

        ProfileInfoDTO loserProfileInfo = ProfileInfoDTO.builder()
                .name(loserProfile.getName())
                .height(loserProfile.getHeight())
                .weight(loserProfile.getWeight())
                .smokeCount(loserProfile.getSmokeCount())
                .exerciseCount(loserProfile.getExerciseCount())
                .drinkCount(loserProfile.getDrinkCount())
                .build();

        Map<String, String> propertyWinners = Map.of(
                "exerciseCount",
                winnerProfile.getExerciseCount() > loserProfile.getExerciseCount() ? winnerProfile.getName()
                        : loserProfile.getName(),
                "weight",
                winnerProfile.getWeight() > loserProfile.getWeight() ? winnerProfile.getName() : loserProfile.getName(),
                "height",
                winnerProfile.getHeight() > loserProfile.getHeight() ? winnerProfile.getName() : loserProfile.getName(),
                "smokeCount", winnerProfile.getSmokeCount() > loserProfile.getSmokeCount() ? winnerProfile.getName()
                        : loserProfile.getName(),
                "drinkCount", winnerProfile.getDrinkCount() > loserProfile.getDrinkCount() ? winnerProfile.getName()
                        : loserProfile.getName()
        );

        HealthMatchResponse healthMatchResponse = HealthMatchResponse.builder()
                .propertyWinners(propertyWinners)
                .winnerInfo(winnerProfileInfo)
                .loserInfo(loserProfileInfo)
                .build();

        log.info("HealthMatch retrieved with ID: {}", matchId);
        return new ResponseEntity<>(healthMatchResponse, HttpStatus.OK);
    }

    @GetMapping("/health-match-results")
    public ResponseEntity<Map<String, List<MatchHistoryDTO>>> getMatchResults(@RequestParam Long profileId) {
        List<HealthMatchResult> matches = healthMatchService.getMatchHistory(profileId);
        List<MatchHistoryDTO> matchHistorys = matches.stream().map(match -> {
            boolean isWinner = match.getWinnerProfile().getId().equals(profileId);
            return MatchHistoryDTO.builder()
                    .matchId(match.getId())
                    .result(isWinner ? "win" : "loss")
                    .matchDate(match.getCreatedDate())
                    .myName(isWinner ? match.getWinnerProfile().getName() : match.getLoserProfile().getName())
                    .opponentName(isWinner ? match.getLoserProfile().getName() : match.getWinnerProfile().getName())
                    .build();
        }).toList();
        log.info("Match history retrieved for profile ID: {}", profileId);
        return ResponseEntity.ok(Map.of("match_history", matchHistorys));
    }
}
