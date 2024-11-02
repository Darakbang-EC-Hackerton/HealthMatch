package com.darakthon.healthmatch.domain;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class HealthMatchResult {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "winner_profile_id")
    private HealthProfile winnerProfile;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "loser_profile_id")
    private HealthProfile loserProfile;

    @OneToMany(mappedBy = "healthMatchResult", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Factor> winningFactors = new ArrayList<>();

    @OneToMany(mappedBy = "healthMatchResult", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Factor> losingFactors = new ArrayList<>();

    @Builder
    public HealthMatchResult(HealthProfile winnerProfile, HealthProfile loserProfile, List<Factor> winningFactors,
                             List<Factor> losingFactors) {
        this.winnerProfile = winnerProfile;
        this.loserProfile = loserProfile;
        this.winningFactors = winningFactors;
        this.losingFactors = losingFactors;
    }

    public void addWinningFactor(Factor factor) {
        factor.addHealthMatchResult(this);
        this.winningFactors.add(factor);
    }

    public void addLosingFactor(Factor factor) {
        factor.addHealthMatchResult(this);
        this.losingFactors.add(factor);
    }

}
