package com.darakthon.healthmatch.service;

import com.darakthon.healthmatch.domain.HealthMatchResult;
import java.util.List;

public interface HealthMatchService {
    Long saveMatch(Long healthProfileId1, Long healthProfileId2);

    HealthMatchResult findHealthMatchById(Long matchId);

    List<HealthMatchResult> getMatchHistory(Long profileId);
}
