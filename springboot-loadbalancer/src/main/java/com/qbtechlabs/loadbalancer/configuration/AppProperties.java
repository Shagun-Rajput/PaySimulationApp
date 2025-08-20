package com.qbtechlabs.loadbalancer.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * @author shagun.rajput
 */
@Configuration
public class AppProperties {
    @Value("${healthCheckInterval}")
    private long healthCheckInterval;
    @Value("${loadBalancingStrategy}")
    private String strategyType;
    @Value("${liveServersProvided}")
    private boolean liveServersProvided;
    public long getHealthCheckInterval() {
        return healthCheckInterval;
    }
    public String getStrategyType() {
        return strategyType;
    }
    public boolean getLiveServersProvided() {
        return liveServersProvided;
    }
}
