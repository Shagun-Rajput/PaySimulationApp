package com.qbtechlabs.loadbalancer.strategy;


import com.qbtechlabs.loadbalancer.domain.Server;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public final class LeastConnectionsStrategy implements CommonLoadBalancingStrategy {

    @Override
    public Server selectServer(List<Server> servers) {
        return servers.stream()
                .filter(Server::isAlive)
                .min((s1, s2) -> Integer.compare(s1.getActiveConnections(), s2.getActiveConnections()))
                .orElse(null);
    }
}