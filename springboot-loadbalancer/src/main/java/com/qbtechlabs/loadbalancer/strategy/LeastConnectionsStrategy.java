package com.example.loadbalancer.strategy;

import com.example.loadbalancer.domain.Server;

import java.util.List;

public class LeastConnectionsStrategy implements LoadBalancingStrategy {

    @Override
    public Server selectServer(List<Server> servers) {
        return servers.stream()
                .filter(Server::isAlive)
                .min((s1, s2) -> Integer.compare(s1.getActiveConnections(), s2.getActiveConnections()))
                .orElse(null);
    }
}