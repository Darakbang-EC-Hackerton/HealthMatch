package com.darakthon.healthmatch.service;

import com.darakthon.healthmatch.domain.Factor;
import com.darakthon.healthmatch.domain.HealthMatchResult;
import com.darakthon.healthmatch.domain.HealthProfile;
import com.darakthon.healthmatch.repository.HealthMatchResultRepository;
import com.darakthon.healthmatch.repository.HealthProfileRepository;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class HealthMatchServiceImpl implements HealthMatchService {
    private final HealthProfileRepository healthProfileRepository;
    private final HealthMatchResultRepository healthMatchResultRepository;

    @Override
    @Transactional
    public Long saveMatch(Long invitorId, Long inviteeId) {
        HealthProfile invitorProfile = healthProfileRepository.findById(invitorId).get();
        HealthProfile inviteeProfile = healthProfileRepository.findById(inviteeId).get();

        HealthMatchResult matchResult = new HealthMatchResult();
        List<Factor> losingFactors = new ArrayList<>();
        List<Factor> winningFactors = new ArrayList<>();

        int invitorScore = 0;
        int inviteeScore = 0;

        if (invitorProfile.getExerciseCount() < inviteeProfile.getExerciseCount()) {
            losingFactors.add(new Factor("exerciseCount"));
            invitorScore++;
        } else {
            winningFactors.add(new Factor("exerciseCount"));
            inviteeScore++;
        }
        if (invitorProfile.getWeight() > inviteeProfile.getWeight()) {
            losingFactors.add(new Factor("weight"));
            invitorScore++;
        } else {
            winningFactors.add(new Factor("weight"));
            inviteeScore++;
        }
        if (invitorProfile.getHeight() < inviteeProfile.getHeight()) {
            losingFactors.add(new Factor("height"));
            invitorScore++;
        } else {
            winningFactors.add(new Factor("height"));
            inviteeScore++;
        }
        if (invitorProfile.getSmokeCount() > inviteeProfile.getSmokeCount()) {
            losingFactors.add(new Factor("smokeCount"));
            invitorScore++;
        } else {
            winningFactors.add(new Factor("smokeCount"));
            inviteeScore++;
        }

        if (invitorScore >= inviteeScore) {
            matchResult = HealthMatchResult.builder()
                    .winnerProfile(invitorProfile)
                    .loserProfile(inviteeProfile)
                    .winningFactors(winningFactors)
                    .losingFactors(losingFactors)
                    .build();
        } else {
            matchResult = HealthMatchResult.builder()
                    .winnerProfile(inviteeProfile)
                    .loserProfile(invitorProfile)
                    .winningFactors(winningFactors)
                    .losingFactors(losingFactors)
                    .build();
        }

        winningFactors.forEach(matchResult::addWinningFactor);
        losingFactors.forEach(matchResult::addLosingFactor);

        healthMatchResultRepository.save(matchResult);
        return matchResult.getId();
    }


}
