package com.qbtechlabs.loadbalancer.service;

import com.qbtechlabs.loadbalancer.configuration.AppProperties;
import com.qbtechlabs.loadbalancer.domain.Server;
import com.qbtechlabs.loadbalancer.enums.NumberEnum;
import com.qbtechlabs.loadbalancer.factory.StrategyFactory;
import com.qbtechlabs.loadbalancer.util.ServerUtil;
import org.slf4j.Logger;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Map;

import static com.qbtechlabs.loadbalancer.constant.Constants.*;

/**
 * @author shagun.rajput
 */
@Service
@ConfigurationProperties(prefix = BACKEND_SERVERS)
public class LoadBalancerServiceImpl implements LoadBalancerService {
    /*******************************************************************************************************************
     * LoadBalancerServiceImpl is a service that implements the LoadBalancerService interface.
     * It uses a StrategyFactory to select a load balancing strategy and WebClient to forward requests to selected servers.
     ******************************************************************************************************************/
    private static final Logger logger = org.slf4j.LoggerFactory.getLogger(LoadBalancerServiceImpl.class);
    private final StrategyFactory strategyFactory;
    private final WebClient webClient;
    private final ServerUtil serverUtil;
    private final List<Server> servers;
    private final AppProperties    appProperties;
    public LoadBalancerServiceImpl(StrategyFactory strategyFactory,
                                   WebClient.Builder webClientBuilder,
                                   ServerUtil serverUtil,
                                   AppProperties appProperties) {
        this.strategyFactory = strategyFactory;
        this.webClient = webClientBuilder.build();
        this.serverUtil = serverUtil;
        this.servers = serverUtil.getServers();
        this.appProperties = appProperties;
    }
    /*******************************************************************************************************************
     * Forwards the request to a selected server based on the load balancing strategy.
     * This method uses the strategy factory to select a server,
     * prepares the request with the specified method, URI, headers, and body,
     * and returns the response from the server.
     *
     * @param method      HTTP method (GET, POST, etc.)
     * @param requestUri  Request URI
     * @param headers     Request headers
     ******************************************************************************************************************/
    @Override
    public ResponseEntity<String> forwardRequest(String method, String requestUri, Map<String, String> headers, String body) {
        logger.info(MSG_FORWARDING_REQUEST, method, requestUri, headers, body, servers.stream().map(Server::getUrl).toList());
        Server selectedServer = strategyFactory.getStrategy().selectServer(servers);
        logger.info(MSG_SELECTED_SERVER, selectedServer.getUrl());
        selectedServer.setActiveConnections(selectedServer.getActiveConnections() + NumberEnum.ONE.value());
        try {
            return appProperties.getLiveServersProvided() ? prepareRequest(selectedServer, method, requestUri, headers, body)
                    .retrieve()
                    .toEntity(String.class)
                    .block()
                    : requestFullfilled(method, requestUri, headers, body, selectedServer);
        }finally {
            selectedServer.setActiveConnections(selectedServer.getActiveConnections() - NumberEnum.ONE.value());
        }
    }
    /*******************************************************************************************************************
     * Logs the request details and returns a ResponseEntity indicating that the request was fulfilled.
     *
     * @param method      HTTP method (GET, POST, etc.)
     * @param requestUri  Request URI
     * @param headers     Request headers
     * @param body        Request body (optional)
     * @param selectedServer The server to which the request was forwarded
     * @return A ResponseEntity indicating the request was fulfilled
     ******************************************************************************************************************/
    private ResponseEntity<String> requestFullfilled(String method, String requestUri, Map<String, String> headers, String body, Server selectedServer) {
        return ResponseEntity.ok(MSG_REQUEST_FWD_TO + selectedServer.getUrl() + MSG_WITH_METHOD + method + MSG_COMMA_URI + requestUri);
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