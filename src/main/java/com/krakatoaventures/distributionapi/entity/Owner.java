package com.krakatoaventures.distributionapi.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.*;

@Data
@AllArgsConstructor
public class Owner {
    private String name;
    private int commitment;
    private Map<String, Integer> sharesByClass;
    private double distribution;

    public int sumUnits() {
        int totalUnits = sharesByClass.values().stream().mapToInt(i->i).sum();
        return totalUnits;
    };
}
