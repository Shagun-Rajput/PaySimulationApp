package com.app.paysim;

import org.slf4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PaymentSimulationApplication {
    private static final Logger logger = org.slf4j.LoggerFactory.getLogger(PaymentSimulationApplication.class);
    public static void main(String[] args) {
        SpringApplication.run(PaymentSimulationApplication.class, args);
        logger.info("********************************************************************");
        logger.info("****** Payment Simulation Application started successfully *********");
        logger.info("********************************************************************");
    }
}