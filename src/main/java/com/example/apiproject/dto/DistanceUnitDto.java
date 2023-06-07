package com.example.apiproject.dto;

import lombok.Data;

@Data
public class DistanceUnitDto {
    private final Integer id;
    private final String unit;
    private final Byte status;
}
