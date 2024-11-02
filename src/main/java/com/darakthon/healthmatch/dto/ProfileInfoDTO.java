package com.darakthon.healthmatch.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProfileInfoDTO {
    @JsonProperty("name")
    private String name;

    @JsonProperty("height")
    private Double height;

    @JsonProperty("weight")
    private Double weight;

    @JsonProperty("smokeCount")
    private Integer smokeCount;

    @JsonProperty("exerciseCount")
    private Integer exerciseCount;

    @JsonProperty("drinkCount")
    private Integer drinkCount;
    
}
