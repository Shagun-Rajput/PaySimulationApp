package com.qbtechlabs.loadbalancer;

import org.slf4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LoadBalancerApplication {
    private static final Logger logger = org.slf4j.LoggerFactory.getLogger(LoadBalancerApplication.class);
    public static void main(String[] args) {
        SpringApplication.run(LoadBalancerApplication.class, args);
        logger.info("********************************************************************");
        logger.info("**************** Load Balancer started successfully ****************");
        logger.info("********************************************************************");
    }
}