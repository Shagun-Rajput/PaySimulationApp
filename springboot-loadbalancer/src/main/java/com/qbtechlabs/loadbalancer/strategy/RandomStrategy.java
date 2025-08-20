package com.qbtechlabs.loadbalancer.strategy;

import com.qbtechlabs.loadbalancer.domain.Server;

import java.util.List;
import java.util.Random;

public final class RandomStrategy implements CommonLoadBalancingStrategy {

    private final Random random = new Random();

    @Override
    public Server selectServer(List<Server> servers) {
        if (servers.isEmpty()) {
            return null;
        }
        int index = random.nextInt(servers.size());
        return servers.get(index);
    }
}