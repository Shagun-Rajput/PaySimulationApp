package com.qbtechlabs.loadbalancer.strategy;

import com.qbtechlabs.loadbalancer.domain.Server;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
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