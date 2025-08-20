package com.qbtechlabs.loadbalancer.service.impl;

import com.qbtechlabs.loadbalancer.domain.Server;
import com.qbtechlabs.loadbalancer.enums.NumberEnum;
import com.qbtechlabs.loadbalancer.factory.StrategyFactory;
import com.qbtechlabs.loadbalancer.service.LoadBalancerService;
import jakarta.annotation.PostConstruct;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Map;

@Service
public class LoadBalancerServiceImpl implements LoadBalancerService {
    private final StrategyFactory strategyFactory;
    private final WebClient webClient;
    private final List<Server> servers;
    public LoadBalancerServiceImpl(StrategyFactory strategyFactory,
                                   WebClient.Builder webClientBuilder,
                                   List<Server> servers) {
        this.strategyFactory = strategyFactory;
        this.webClient = webClientBuilder.build();
        this.servers = servers;
    }
    /*******************************************************************************************************************
     * Validates the list of servers to ensure it is not empty.
     *
     * This method is called after the bean is constructed to ensure that the server list is properly configured.
     * If the server list is empty, an IllegalStateException is thrown.
     ******************************************************************************************************************/
    @PostConstruct
    private void validateServers() {
        if (servers == null || servers.isEmpty()) {
            throw new IllegalStateException("Server list cannot be empty. Please configure servers in the application properties.");
        }
    }
    /*******************************************************************************************************************
     * Forwards the request to a selected server based on the load balancing strategy.
     * This method uses the strategy factory to select a server,
     * prepares the request with the specified method, URI, headers, and body,
     * and then sends the request using WebClient.
     * @param method      HTTP method (GET, POST, etc.)
     * @param requestUri  Request URI
     * @param headers     Request headers
     ******************************************************************************************************************/
    @Override
    public ResponseEntity<String> forwardRequest(String method, String requestUri, Map<String, String> headers, String body) {

        Server selectedServer = strategyFactory.getStrategy().selectServer(servers);
        try {
            return prepareRequest(selectedServer, method, requestUri, headers, body)
                    .retrieve()
                    .toEntity(String.class)
                    .block();
        }finally {
            // Decrement active connections after the request is completed
            selectedServer.setActiveConnections(selectedServer.getActiveConnections() - NumberEnum.ONE.value());
        }
    }
    /*******************************************************************************************************************
     * Prepares a WebClient.RequestBodySpec for forwarding the request to the selected server.
     *
     * @param server      The server to which the request will be forwarded
     * @param method      HTTP method (GET, POST, etc.)
     * @param requestUri  Request URI
     * @param headers     Request headers
     * @param body        Request body (optional)
     * @return A WebClient.RequestBodySpec configured with the provided parameters
     ******************************************************************************************************************/
    private WebClient.RequestBodySpec prepareRequest(Server server, String method, String requestUri, Map<String, String> headers, String body) {
        WebClient.RequestBodySpec requestSpec = webClient.method(org.springframework.http.HttpMethod.valueOf(method))
                .uri(server.getUrl() + requestUri);
        // Add headers to the request
        headers.forEach(requestSpec::header);
        // If the body is not null, set it in the request else ignore
        if (body != null) {
            requestSpec.bodyValue(body);
        }
        return requestSpec;
    }
    /*************************************************** END  ***********************************************/

}