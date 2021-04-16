package com.krakatoaventures.distributionapi.service.impl;

import com.krakatoaventures.distributionapi.entity.Owner;
import com.krakatoaventures.distributionapi.entity.Venture;
import com.krakatoaventures.distributionapi.service.AllocationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

/*
* ownerAllocation service (distribution amount)
 *  - set distribution amount of the ventures
 *  - get total units
 *  - get commitments
 *  - substract commitments from total distribution
 *  - loop through each unitClass,, calculate distribution and setDistribution
* */
public class OwnerAllocationService implements AllocationService {
    private final Logger log = LoggerFactory.getLogger(OwnerAllocationService.class);
    private final Venture venture;

    public OwnerAllocationService(Venture venture) {
        this.venture = venture;
    }

//    private String name;
//    private int commitment;
//    private Map<String, Integer> sharesByClass;
//    private int distribution;
    @Override
    public void allocate(int distribution) {
        log.info(" Owner Allocation Service is Triggered: =========");
        List<Owner> owners = venture.getOwners();
        int totalUnits = owners.stream().mapToInt(owner->owner.sumUnits()).sum();
        int totalCommitment = owners.stream().mapToInt(owner->owner.getCommitment()).sum();
        int distributionExldCommitment = distribution - totalCommitment;
        for (Owner owner: owners) {
            double allocationPercentage = (double) owner.sumUnits() / totalUnits;
            int distributionAllo = (int)((int) distributionExldCommitment * allocationPercentage + owner.getCommitment());
            owner.setDistribution(distributionAllo);
            log.info(owner.getName() + " allocated distribution: " + owner.getDistribution());
        }
    }
}
