package com.qbtechlabs.loadbalancer.service.impl;

import com.qbtechlabs.loadbalancer.domain.Server;
import com.qbtechlabs.loadbalancer.factory.StrategyFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Service
public class LoadBalancerServiceImpl implements com.qbtechlabs.loadbalancer.service.LoadBalancerService {

    private final StrategyFactory strategyFactory;
    private final WebClient webClient;

    public LoadBalancerServiceImpl(StrategyFactory strategyFactory,
                                   WebClient.Builder webClientBuilder) {
        this.strategyFactory = strategyFactory;
        this.webClient = webClientBuilder.build();
    }

    public String forwardRequest(String requestUri, List<Server> servers) {
        Server selectedServer = strategyFactory.getStrategy().selectServer(servers);
        return webClient.get()
                .uri(selectedServer.getUrl() + requestUri)
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }

    @Override
    public String forwardRequest(String requestUri, String body, List<Server> servers) {
        Server selectedServer = strategyFactory.getStrategy().selectServer(servers);
        return webClient.post()
                .uri(selectedServer.getUrl() + requestUri)
                .bodyValue(body)
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }

    @Override
    public ResponseEntity<String> forwardRequest() {
        return null;
    }

    @Override
    public ResponseEntity<String> forwardRequest(String body) {
        return null;
    }
}