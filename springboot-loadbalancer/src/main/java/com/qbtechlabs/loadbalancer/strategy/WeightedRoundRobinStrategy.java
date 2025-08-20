package com.qbtechlabs.loadbalancer.strategy;

import com.qbtechlabs.loadbalancer.domain.Server;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public final class WeightedRoundRobinStrategy implements CommonLoadBalancingStrategy {

    private int currentIndex = -1;
    private int currentWeight = 0;
    private int gcdWeight = 0;
    private int maxWeight = 0;

    @Override
    public Server selectServer(List<Server> servers) {
        if (servers.isEmpty()) {
            return null;
        }

        if (gcdWeight == 0) {
            calculateWeights(servers);
        }

        while (true) {
            currentIndex = (currentIndex + 1) % servers.size();
            if (currentIndex == 0) {
                currentWeight = currentWeight - gcdWeight;
                if (currentWeight <= 0) {
                    currentWeight = maxWeight;
                    if (currentWeight == 0) {
                        return null;
                    }
                }
            }

            Server server = servers.get(currentIndex);
            if (server.getWeight() >= currentWeight) {
                return server;
            }
        }
    }

    private void calculateWeights(List<Server> servers) {
        for (Server server : servers) {
            maxWeight = Math.max(maxWeight, server.getWeight());
            gcdWeight = gcd(gcdWeight, server.getWeight());
        }
    }

    private int gcd(int a, int b) {
        if (b == 0) {
            return a;
        }
        return gcd(b, a % b);
    }
}