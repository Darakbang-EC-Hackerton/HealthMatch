package com.darakthon.healthmatch.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class HealthProfileRequest {
    @NotEmpty
    private String name;

    @NotNull
    private Integer exerciseCount;

    @NotNull
    private Double weight;

    @NotNull
    private Double height;

    @NotNull
    private Integer smokeCount;

    @NotNull
    private Integer drinkCount;
}
