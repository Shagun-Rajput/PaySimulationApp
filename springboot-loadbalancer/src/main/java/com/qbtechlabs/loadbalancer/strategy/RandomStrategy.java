package com.qbtechlabs.loadbalancer.strategy;

import com.qbtechlabs.loadbalancer.domain.Server;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

/**
 * @author shagun.rajput
 */
@Service
public final class RandomStrategy implements CommonLoadBalancingStrategy {
    private final Random random = new Random();
    /*******************************************************************************************************************
     * Selects a server from the provided list of servers using a random selection strategy.
     *
     * @param servers A list of available servers to choose from
     * @return A randomly selected server from the list
     ******************************************************************************************************************/
    @Override
    public Server selectServer(List<Server> servers) {
        return servers.get(random.nextInt(servers.size()));
    }
}