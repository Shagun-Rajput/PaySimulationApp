package com.example.loadbalancer.factory;

import com.example.loadbalancer.strategy.LoadBalancingStrategy;
import com.example.loadbalancer.strategy.RoundRobinStrategy;
import com.example.loadbalancer.strategy.RandomStrategy;
import com.example.loadbalancer.strategy.WeightedRoundRobinStrategy;
import com.example.loadbalancer.strategy.LeastConnectionsStrategy;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class StrategyFactory {

    @Value("${loadbalancer.strategy}")
    private String strategyType;

    public LoadBalancingStrategy getStrategy() {
        switch (strategyType.toLowerCase()) {
            case "roundrobin":
                return new RoundRobinStrategy();
            case "random":
                return new RandomStrategy();
            case "weightedroundrobin":
                return new WeightedRoundRobinStrategy();
            case "leastconnections":
                return new LeastConnectionsStrategy();
            default:
                throw new IllegalArgumentException("Unknown load balancing strategy: " + strategyType);
        }
    }
}