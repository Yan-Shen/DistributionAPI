package com.krakatoaventures.distributionapi;

import com.krakatoaventures.distributionapi.entity.Owner;
import com.krakatoaventures.distributionapi.entity.UnitClass;
import com.krakatoaventures.distributionapi.entity.Venture;
import com.krakatoaventures.distributionapi.service.impl.OwnerAllocationService;
import com.krakatoaventures.distributionapi.service.impl.UnitClassAllocationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.*;

@Configuration
public class AppRun implements CommandLineRunner {
    private Logger log = LoggerFactory.getLogger(AppRun.class);

    @Override
    public void run(String... args) throws Exception {
        // init and set Venture entity
        Venture kVenture =  init();
        OwnerAllocationService ownerAllocationService = new OwnerAllocationService(kVenture);
        UnitClassAllocationService unitClassAllocationService = new UnitClassAllocationService(kVenture);

        // get command line input
        Scanner scanner = new Scanner(System.in);
        System.out.print("What is the amount Krakatoa Ventures plan to distribute?  ");
        int distribution = scanner.nextInt();

        // call allocation service
        ownerAllocationService.allocate(distribution);
        unitClassAllocationService.allocate(distribution);
        log.info("venture after distribution : " + kVenture);

        // print report
        Map<String, Double> classReport = new HashMap<>();
        Map<String, Double> ownerReport = new HashMap<>();
        kVenture.getUnitClasses().stream().forEach(unitClass->{
            String label = unitClass.getClassLabel();
            double distributionAmount = unitClass.getDistribution();
            classReport.put(label, distributionAmount);
        });
        kVenture.getOwners().stream().forEach(owner->{
            String name = owner.getName();
            double distributionAmount = owner.getDistribution();
            ownerReport.put(name, distributionAmount);
        });

        generateReport("Unit class", classReport);
        generateReport("Owner", ownerReport);
    }

    public void generateReport(String reportName, Map<String, Double> report) throws NoSuchMethodException {
        System.out.println();
        log.info("****************************************" );
        log.info(reportName+ " Distribution Report (JSON):" );
        log.info(String.valueOf(report));
        log.info("****************************************" );
    }
    @Bean
    public Venture init() {
        log.info("Init process started.");
        HashMap<String, Integer> mapAlex = new HashMap<>();
        mapAlex.put("B", 10);
        HashMap<String, Integer> mapBecky = new HashMap<>();
        mapBecky.put("B", 10);
        mapBecky.put("C", 5);
        HashMap<String, Integer> mapDavid = new HashMap<>();
        mapDavid.put("A", 10);

        List<Owner> owners = new ArrayList<>();
        owners.add(new Owner("Alex", 250, mapAlex, 0));
        owners.add(new Owner("Becky", 250, mapBecky, 0));
        owners.add(new Owner("David", 0, mapDavid, 0));

        List<UnitClass> unitClasses = new ArrayList<>();
        unitClasses.add(new UnitClass("A", 10, 0, 0));
        unitClasses.add(new UnitClass("B", 20, 500, 0));
        unitClasses.add(new UnitClass("C", 5, 0, 0));

        Venture venture = new Venture("Krakatoa Ventures", unitClasses, owners, 0);
        log.info("initial venture created: " + venture);
        return venture;
    }
}
