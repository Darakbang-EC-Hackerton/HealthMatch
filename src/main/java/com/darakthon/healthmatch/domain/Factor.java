package com.darakthon.healthmatch.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
public class Factor {
    @Id
    @GeneratedValue
    private Long id;

    private String factorName;

    @ManyToOne(fetch = FetchType.LAZY)
    private HealthMatchResult healthMatchResult;

    @Builder
    public Factor(String factorName) {
        this.factorName = factorName;
    }

    public void addHealthMatchResult(HealthMatchResult healthMatchResult) {
        this.healthMatchResult = healthMatchResult;
    }
}
