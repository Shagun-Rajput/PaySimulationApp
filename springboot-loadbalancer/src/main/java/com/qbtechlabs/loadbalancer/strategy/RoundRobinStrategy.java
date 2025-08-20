package com.qbtechlabs.loadbalancer.strategy;

import com.qbtechlabs.loadbalancer.domain.Server;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public final class RoundRobinStrategy implements CommonLoadBalancingStrategy {

    private final AtomicInteger index = new AtomicInteger(0);

    @Override
    public Server selectServer(List<Server> servers) {
        if (servers.isEmpty()) {
            return null;
        }
        int currentIndex = index.getAndUpdate(i -> (i + 1) % servers.size());
        return servers.get(currentIndex);
    }
}