package wesp.company.feirafacilapi;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FeiraFacilApiApplication {

    public static void main(String[] args) {
        Logger logger = LoggerFactory.getLogger(FeiraFacilApiApplication.class);
        logger.info("Starting FeiraFacilApiApplication...");
        SpringApplication.run(FeiraFacilApiApplication.class, args);
        logger.info("FeiraFacilApiApplication started successfully");
    }
}
