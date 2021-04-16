package com.krakatoaventures.distributionapi.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UnitClass {
    private String classLabel;
    private int units;
    private int commitment;
    private int distribution;
}
