package com.darakthon.healthmatch.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class HealthProfile extends BaseEntity {
    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private Integer weight;

    private Integer height;

    private Integer exerciseCount;
}
