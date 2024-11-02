package com.darakthon.healthmatch.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity
public class HealthMatch extends BaseEntity {
    @Id
    @GeneratedValue
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "health_profile_id")
    private HealthProfile challengerProfile;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "health_profile_id")
    private HealthProfile opponentProfile;

}
