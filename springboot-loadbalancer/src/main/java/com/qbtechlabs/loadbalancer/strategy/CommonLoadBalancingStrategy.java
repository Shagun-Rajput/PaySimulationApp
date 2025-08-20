package com.qbtechlabs.loadbalancer.strategy;

import com.qbtechlabs.loadbalancer.domain.Server;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public sealed interface CommonLoadBalancingStrategy permits RoundRobinStrategy, WeightedRoundRobinStrategy, LeastConnectionsStrategy, RandomStrategy {
    Server selectServer(List<Server> servers);
}