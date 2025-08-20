package com.qbtechlabs.loadbalancer.factory;

import com.qbtechlabs.loadbalancer.strategy.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


@Component
public class StrategyFactory {

    @Value("${loadbalancer.strategy}")
    private String strategyType;

    public CommonLoadBalancingStrategy getStrategy() {
        return switch (strategyType.toLowerCase()) {
            case "roundrobin" -> new RoundRobinStrategy();
            case "random" -> new RandomStrategy();
            case "weightedroundrobin" -> new WeightedRoundRobinStrategy();
            case "leastconnections" -> new LeastConnectionsStrategy();
            default -> throw new IllegalArgumentException("Unknown load balancing strategy: " + strategyType);
        };
    }
}