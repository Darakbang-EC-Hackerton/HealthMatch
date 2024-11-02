package com.darakthon.healthmatch.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class HealthProfile extends BaseEntity {
    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private Integer exerciseCount;

    private Double weight;

    private Double height;

    private Integer smokeCount;

    private Integer drinkCount;

    @Builder
    public HealthProfile(String name, Integer exerciseCount, Double weight, Double height, Integer smokeCount,
                         Integer drinkCount) {
        this.name = name;
        this.exerciseCount = exerciseCount;
        this.weight = weight;
        this.height = height;
        this.smokeCount = smokeCount;
        this.drinkCount = drinkCount;
    }

    public void updateProfile(String name, Integer exerciseCount, Double weight, Double height, Integer smokeCount,
                              Integer drinkCount) {
        this.name = name;
        this.exerciseCount = exerciseCount;
        this.weight = weight;
        this.height = height;
        this.smokeCount = smokeCount;
        this.drinkCount = drinkCount;
    }
}
