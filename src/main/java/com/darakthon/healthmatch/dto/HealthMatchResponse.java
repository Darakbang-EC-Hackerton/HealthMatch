package com.darakthon.healthmatch.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HealthMatchResponse {

    @JsonProperty("propertyWinners")
    private Map<String, String> propertyWinners;

    @JsonProperty("winnerInfo")
    private ProfileInfoDTO winnerInfo;

    @JsonProperty("loserInfo")
    private ProfileInfoDTO loserInfo;

}
