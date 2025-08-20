package com.qbtechlabs.loadbalancer.factory;

import com.qbtechlabs.loadbalancer.strategy.*;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


@Component
public class StrategyFactory {
    private static final Logger logger = org.slf4j.LoggerFactory.getLogger(StrategyFactory.class);
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
        logger.info("Using load balancing strategy: {}", strategyType);
        return switch (strategyType.toLowerCase()) {
            case "roundrobin" -> roundRobinStrategy;
            case "random" -> randomStrategy;
            case "weightedroundrobin" -> weightedRoundRobinStrategy;
            case "leastconnections" -> leastConnectionsStrategy;
            default -> throw new IllegalArgumentException("Unknown load balancing strategy: " + strategyType);
        };
    }
}