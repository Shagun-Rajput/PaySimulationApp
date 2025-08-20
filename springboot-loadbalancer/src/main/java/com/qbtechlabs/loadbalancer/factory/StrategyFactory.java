package com.qbtechlabs.loadbalancer.factory;

import com.qbtechlabs.loadbalancer.strategy.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


@Component
public class StrategyFactory {

    @Value("${loadBalancingStrategy}")
    private String strategyType;
    private final RoundRobinStrategy roundRobinStrategy;
    private final RandomStrategy randomStrategy;
    private final WeightedRoundRobinStrategy weightedRoundRobinStrategy;
    private final LeastConnectionsStrategy leastConnectionsStrategy;
    public StrategyFactory(RoundRobinStrategy roundRobinStrategy,
                           RandomStrategy randomStrategy,
                           WeightedRoundRobinStrategy weightedRoundRobinStrategy,
                           LeastConnectionsStrategy leastConnectionsStrategy) {
        this.roundRobinStrategy = roundRobinStrategy;
        this.randomStrategy = randomStrategy;
        this.weightedRoundRobinStrategy = weightedRoundRobinStrategy;
        this.leastConnectionsStrategy = leastConnectionsStrategy;
    }


    public CommonLoadBalancingStrategy getStrategy() {
        return switch (strategyType.toLowerCase()) {
            case "roundrobin" -> roundRobinStrategy;
            case "random" -> randomStrategy;
            case "weightedroundrobin" -> weightedRoundRobinStrategy;
            case "leastconnections" -> leastConnectionsStrategy;
            default -> throw new IllegalArgumentException("Unknown load balancing strategy: " + strategyType);
        };
    }
}