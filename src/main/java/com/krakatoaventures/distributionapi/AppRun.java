package com.krakatoaventures.distributionapi;

import com.krakatoaventures.distributionapi.entity.Owner;
import com.krakatoaventures.distributionapi.entity.UnitClass;
import com.krakatoaventures.distributionapi.entity.Venture;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.*;
/*
Krakatoa Ventures is a Venture Capital (VC) Firm.
Krakatoa Ventures issues m​embership units​ in 3 classes, ​Class A,​ ​Class B,​ ​Class C
● Alex​ and Becky ​are General Partners; each commit $250 into Krakatoa Ventures and each receive 10 Class B  membership units.
● David is an associate and receives 10 C​lass A ​membershipunits.
● Becky additionally​ r​eceives 5 Class C membership units as a Managing Partner.
* */

@Component
public class AppRun implements CommandLineRunner {
    private final Logger log = LoggerFactory.getLogger(AppRun.class);

    @Override
    public void run(String... args) throws Exception {
        // init and set Venture entity
        init();
        // call allocation service
    }

//    private String name;
//    private int commitment;
//    private Map<String, Integer> sharesByClass;
//    private int distribution;

    public Venture init() {
        log.info("Init process started.");
        HashMap<String, Integer> mapAlex = new HashMap<>();
        mapAlex.put("B", 10);
        HashMap<String, Integer> mapBecky = new HashMap<>();
        mapBecky.put("B", 10);
        mapBecky.put("C", 5);
        HashMap<String, Integer> mapDavid = new HashMap<>();
        mapAlex.put("A", 10);

        List<Owner> owners = new ArrayList<>();
        owners.add(new Owner("Alex", 250, mapAlex, 0));
        owners.add(new Owner("Becky", 250, mapBecky, 0));
        owners.add(new Owner("David", 0, mapDavid, 0));
//        List<Owner> owners = Arrays.asList(){
//            new Owner("Alex", 250, mapAlex, 0),
//            new Owner("Becky", 250, mapBecky, 0),
//            new Owner("David", 0, mapDavid, 0)
//        };

//        private String classLabel;
//        private int units;
//        private int commitment;
//        private int distribution;
        List<UnitClass> unitClasses = new ArrayList<>();
        unitClasses.add(new UnitClass("A", 10, 0, 0));
        unitClasses.add(new UnitClass("B", 20, 500, 0));
        unitClasses.add(new UnitClass("C", 5, 0, 0));
//        List<UnitClass> unitClasses = Arrays.asList(){
//            new UnitClass("A", 10, 0, 0),
//            new UnitClass("B", 20, 500, 0),
//            new UnitClass("C", 5, 0, 0)
//        }

        Venture venture = new Venture("Krakatoa Ventures", unitClasses, owners, 0);
        log.info("initial venture created: " + venture);
        return venture;
    }
}