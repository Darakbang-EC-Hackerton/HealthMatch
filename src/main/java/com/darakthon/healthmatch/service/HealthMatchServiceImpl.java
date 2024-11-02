package com.darakthon.healthmatch.service;

import com.darakthon.healthmatch.domain.HealthMatchResult;
import com.darakthon.healthmatch.domain.HealthProfile;
import com.darakthon.healthmatch.domain.LosingFactor;
import com.darakthon.healthmatch.domain.WinningFactor;
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
        List<LosingFactor> losingFactors = new ArrayList<>();
        List<WinningFactor> winningFactors = new ArrayList<>();

        int invitorScore = 0;
        int inviteeScore = 0;

        if (invitorProfile.getExerciseCount() < inviteeProfile.getExerciseCount()) {
            losingFactors.add(
                    LosingFactor.builder().factorName("exerciseCount").losingHealthMatchResult(matchResult).build());
            invitorScore++;
        } else {
            winningFactors.add(WinningFactor.builder().factorName("exerciseCount").build());
            inviteeScore++;
        }
        if (invitorProfile.getWeight() > inviteeProfile.getWeight()) {
            losingFactors.add(LosingFactor.builder().factorName("weight").build());
            invitorScore++;
        } else {
            winningFactors.add(WinningFactor.builder().factorName("weight").build());
            inviteeScore++;
        }
        if (invitorProfile.getHeight() < inviteeProfile.getHeight()) {
            losingFactors.add(LosingFactor.builder().factorName("height").build());
            invitorScore++;
        } else {
            winningFactors.add(WinningFactor.builder().factorName("height").build());
            inviteeScore++;
        }
        if (invitorProfile.getSmokeCount() > inviteeProfile.getSmokeCount()) {
            losingFactors.add(LosingFactor.builder().factorName("smokeCount").build());
            invitorScore++;
        } else {
            winningFactors.add(WinningFactor.builder().factorName("smokeCount").build());
            inviteeScore++;
        }
        if (invitorProfile.getDrinkCount() > inviteeProfile.getDrinkCount()) {
            losingFactors.add(LosingFactor.builder().factorName("drinkCount").build());
            invitorScore++;
        } else {
            winningFactors.add(WinningFactor.builder().factorName("drinkCount").build());
            inviteeScore++;
        }

        if (invitorScore >= inviteeScore) {
            matchResult = HealthMatchResult.builder()
                    .winnerProfile(invitorProfile)
                    .loserProfile(inviteeProfile)
                    .build();
        } else {
            matchResult = HealthMatchResult.builder()
                    .winnerProfile(inviteeProfile)
                    .loserProfile(invitorProfile)
                    .build();
        }

        healthMatchResultRepository.save(matchResult);
        return matchResult.getId();
    }

    @Override
    public HealthMatchResult findHealthMatchById(Long matchId) {
        return healthMatchResultRepository.findById(matchId).orElseThrow(() ->
                new IllegalArgumentException("No match found with id: " + matchId));
    }

    @Override
    public List<HealthMatchResult> getMatchHistory(Long profileId) {
        return healthMatchResultRepository.findByProfileId(profileId);
    }
}
