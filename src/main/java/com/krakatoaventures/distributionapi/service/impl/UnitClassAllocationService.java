package com.krakatoaventures.distributionapi.service.impl;

import com.krakatoaventures.distributionapi.entity.UnitClass;
import com.krakatoaventures.distributionapi.entity.Venture;
import com.krakatoaventures.distributionapi.service.AllocationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;


/*
*    * unitClassAllocation service (distribution amount)
 *  - set distribution amount of the ventures
 *  - get total units
 *  - get commitments
 *  - substract commitments from total distribution
 *  - loop through each class, calculate distribution and setDistribution
* */
@Component
public class UnitClassAllocationService implements AllocationService {
    private final Logger log = LoggerFactory.getLogger(UnitClassAllocationService.class);
    private final Venture venture;

    public UnitClassAllocationService(Venture venture) {
        this.venture = venture;
    }

    @Override
    public void allocate(int distribution) {
        // get total units
        log.info(" UnitClass Allocation Service is Triggered: ");
        List<UnitClass> unitClasses = venture.getUnitClasses();
        int totalUnits = unitClasses.stream().mapToInt(unitClass->unitClass.getUnits()).sum();
        int totalCommitment = unitClasses.stream().mapToInt(unitClass->unitClass.getCommitment()).sum();
        int distributionExldCommitment = distribution - totalCommitment;
        for(UnitClass unitClass: unitClasses) {
            double allocationPercentage = (double) unitClass.getUnits() / totalUnits;
            double distributionAllo = Math.round((distributionExldCommitment * allocationPercentage + unitClass.getCommitment())*100.0)/100.0;
            unitClass.setDistribution(distributionAllo);
            log.info("Class" + unitClass.getClassLabel() + " allocated distribution: " + unitClass.getDistribution());

        }

    }
}
