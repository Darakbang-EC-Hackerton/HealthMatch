package com.darakthon.healthmatch.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HealthProfileResponse {

    @JsonProperty("profile")
    private ProfileDTO profile;

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class ProfileDTO {

        @JsonProperty("user_id")
        private String userId;

        @JsonProperty("name")
        private String name;

        @JsonProperty("height_cm")
        private Double heightCm;

        @JsonProperty("weight_kg")
        private Double weightKg;

        @JsonProperty("smoke_count")
        private Integer smokeCount;

        @JsonProperty("drinking_frequency_per_week")
        private Integer drinkingFrequencyPerWeek;

        @JsonProperty("weekly_exercise_days")
        private Integer weeklyExerciseDays;

        @JsonProperty("health_grade")
        private String healthGrade;

        @JsonProperty("bmi")
        private Double bmi;

        @JsonProperty("ideal_values")
        private IdealValuesDTO idealValues;
    }
}
