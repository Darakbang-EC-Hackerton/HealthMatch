package com.darakthon.healthmatch.dto;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class MatchHistoryDTO {
    private String matchId;
    private String result;
    private LocalDateTime matchDate;
    private String myName;
    private String opponentName;
}
