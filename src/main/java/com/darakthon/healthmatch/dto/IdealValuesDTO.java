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
public class IdealValuesDTO {

    @JsonProperty("ideal_weight_kg")
    private Double idealWeightKg;

    @JsonProperty("ideal_drinking_frequency_per_week")
    private Integer idealDrinkingFrequencyPerWeek;

    @JsonProperty("ideal_weekly_exercise_days")
    private Integer idealWeeklyExerciseDays;
}
