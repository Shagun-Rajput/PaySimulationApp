package com.app.dvm;

import org.slf4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DVMApplication {
    private static final Logger logger = org.slf4j.LoggerFactory.getLogger(DVMApplication.class);
    public static void main(String[] args) {
        SpringApplication.run(DVMApplication.class, args);
        logger.info("********************************************************************");
        logger.info("**************** Load Balancer started successfully ****************");
        logger.info("********************************************************************");
    }
}