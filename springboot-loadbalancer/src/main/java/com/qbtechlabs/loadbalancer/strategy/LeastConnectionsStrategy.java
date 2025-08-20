package com.qbtechlabs.loadbalancer.strategy;


import com.qbtechlabs.loadbalancer.domain.Server;
import com.qbtechlabs.loadbalancer.enums.NumberEnum;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

import static com.qbtechlabs.loadbalancer.constant.Constants.MSG_NO_SERVER_AVL;

/**
 * @author shagun.rajput
 */
@Service
public final class LeastConnectionsStrategy implements CommonLoadBalancingStrategy {
    /*******************************************************************************************************************
     * Selects a server from the provided list of servers based on the Least Connections load balancing strategy.
     *
     * Steps:
     * 1. Filter the list of servers to include only those that are alive (`isAlive` is true).
     * 2. Use a comparator to find the server with the fewest active connections.
     * 3. If no alive servers are available, throw an exception indicating no servers can handle the request.
     * 4. Increment the active connections count for the selected server to track the load.
     *
     * @param servers A list of available servers to choose from
     * @return The selected server with the least active connections, or throws an exception if no alive servers are available
     ******************************************************************************************************************/
    @Override
    public Server selectServer(List<Server> servers) {
        return servers.stream()
                .filter(Server::isAlive) // Only consider servers that are alive
                .min(Comparator.comparingInt(Server::getActiveConnections)) // Find the server with the least active connections
                .orElseThrow(() -> new IllegalStateException(MSG_NO_SERVER_AVL));
    }
}