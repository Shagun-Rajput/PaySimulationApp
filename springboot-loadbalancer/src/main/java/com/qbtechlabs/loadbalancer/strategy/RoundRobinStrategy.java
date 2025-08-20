package com.qbtechlabs.loadbalancer.strategy;

import com.qbtechlabs.loadbalancer.domain.Server;
import com.qbtechlabs.loadbalancer.enums.NumberEnum;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public final class RoundRobinStrategy implements CommonLoadBalancingStrategy {
    private final AtomicInteger index = new AtomicInteger(0);
    /*******************************************************************************************************************
     * Selects a server using the Round Robin load balancing strategy.
     *
     * This method cycles through the list of servers, returning each server in turn.
     * It uses an AtomicInteger to keep track of the current index, ensuring thread safety.
     *
     * @param servers A list of available servers to choose from
     * @return The selected server based on the Round Robin algorithm
     ******************************************************************************************************************/
    @Override
    public Server selectServer(List<Server> servers) {
        return servers.get(index.getAndUpdate(
                i -> (i + NumberEnum.ONE.value()) % servers.size())
        );
    }
}