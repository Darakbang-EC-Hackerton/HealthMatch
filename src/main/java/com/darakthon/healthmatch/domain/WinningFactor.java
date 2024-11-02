package com.darakthon.healthmatch.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class WinningFactor {

    @Id
    @GeneratedValue
    private Long id;

    private String factorName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "health_match_result_id")
    private HealthMatchResult healthMatchResult;

    @Builder
    public WinningFactor(String factorName, HealthMatchResult winningHealthMatchResult) {
        this.factorName = factorName;
        this.healthMatchResult = winningHealthMatchResult;
    }


}
