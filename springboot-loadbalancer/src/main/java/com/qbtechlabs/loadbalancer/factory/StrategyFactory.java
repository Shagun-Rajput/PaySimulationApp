package com.qbtechlabs.loadbalancer.factory;

import com.qbtechlabs.loadbalancer.configuration.AppProperties;
import com.qbtechlabs.loadbalancer.strategy.*;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;

import static com.qbtechlabs.loadbalancer.constant.Constants.*;

/**
 * @author shagun.rajput
 */
@Component
public class StrategyFactory {
    private static final Logger logger = org.slf4j.LoggerFactory.getLogger(StrategyFactory.class);
    private final RoundRobinStrategy roundRobinStrategy;
    private final RandomStrategy randomStrategy;
    private final WeightedRoundRobinStrategy weightedRoundRobinStrategy;
    private final LeastConnectionsStrategy leastConnectionsStrategy;
    private final AppProperties appProperties;
    public StrategyFactory(RoundRobinStrategy roundRobinStrategy,
                           RandomStrategy randomStrategy,
                           WeightedRoundRobinStrategy weightedRoundRobinStrategy,
                           LeastConnectionsStrategy leastConnectionsStrategy,
                           AppProperties appProperties) {
        this.roundRobinStrategy = roundRobinStrategy;
        this.randomStrategy = randomStrategy;
        this.weightedRoundRobinStrategy = weightedRoundRobinStrategy;
        this.leastConnectionsStrategy = leastConnectionsStrategy;
        this.appProperties = appProperties;
    }

    /**
     * Factory method to get the appropriate load balancing strategy based on the configuration.
     *
     * @return An instance of CommonLoadBalancingStrategy for the specified strategy type.
     * @throws IllegalArgumentException if the strategy type is not supported.
     */
    public CommonLoadBalancingStrategy getStrategy() {
        logger.info(MSG_USING_STRATEGY, appProperties.getStrategyType());
        return switch (appProperties.getStrategyType().toLowerCase()) {
            case STRATEGY_ROUND_ROBIN -> roundRobinStrategy;
            case STRATEGY_RANDOM -> randomStrategy;
            case STRATEGY_WEIGHTED_ROUND_ROBIN -> weightedRoundRobinStrategy;
            case STRATEGY_LEAST_CONNECTIONS -> leastConnectionsStrategy;
            default -> throw new IllegalArgumentException(MSG_UNKNOWN_STRATEGY + appProperties.getStrategyType());
        };
    }
}