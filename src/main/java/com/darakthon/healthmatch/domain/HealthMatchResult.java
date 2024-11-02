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

@Entity
@Getter
@NoArgsConstructor
public class HealthMatchResult extends BaseEntity {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "winner_profile_id")
    private HealthProfile winnerProfile;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "loser_profile_id")
    private HealthProfile loserProfile;

    @Builder
    public HealthMatchResult(HealthProfile winnerProfile, HealthProfile loserProfile) {
        this.winnerProfile = winnerProfile;
        this.loserProfile = loserProfile;
    }
}
