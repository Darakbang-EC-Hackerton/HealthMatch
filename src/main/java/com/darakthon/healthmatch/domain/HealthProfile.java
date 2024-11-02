package com.darakthon.healthmatch.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;

@Entity
@Getter
public class HealthProfile extends BaseEntity {
    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private Integer weight;

    private Integer height;

    private Integer exerciseCount;

    public void update(String name, Integer weight, Integer height, Integer exerciseCount) {
        this.name = name;
        this.weight = weight;
        this.height = height;
        this.exerciseCount = exerciseCount;
    }
}
