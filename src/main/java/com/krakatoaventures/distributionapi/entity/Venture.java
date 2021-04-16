package com.krakatoaventures.distributionapi.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import java.util.*;

@Data
@AllArgsConstructor
public class Venture {
    private String name;
    private List<UnitClass> unitClasses;
    private List<Owner> owners;
    private int distribution;
}
