package com.qbtechlabs.loadbalancer.strategy;

import com.qbtechlabs.loadbalancer.domain.Server;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public sealed interface CommonLoadBalancingStrategy permits RoundRobinStrategy, WeightedRoundRobinStrategy, LeastConnectionsStrategy, RandomStrategy {
    /*******************************************************************************************************************
     * Selects a server from the provided list of servers based on the implemented load balancing strategy.
     *
     * @param servers A list of available servers to choose from
     * @return The selected server based on the load balancing algorithm
     ******************************************************************************************************************/
    Server selectServer(List<Server> servers);
}