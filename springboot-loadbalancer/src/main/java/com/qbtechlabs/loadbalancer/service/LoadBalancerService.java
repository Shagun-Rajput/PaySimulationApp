package com.example.loadbalancer.service;

import com.example.loadbalancer.domain.Server;
import com.example.loadbalancer.strategy.LoadBalancingStrategy;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Service
public class LoadBalancerService {

    private final LoadBalancingStrategy loadBalancingStrategy;
    private final WebClient webClient;

    public LoadBalancerService(LoadBalancingStrategy loadBalancingStrategy, WebClient.Builder webClientBuilder) {
        this.loadBalancingStrategy = loadBalancingStrategy;
        this.webClient = webClientBuilder.build();
    }

    public String forwardRequest(String requestUri, List<Server> servers) {
        Server selectedServer = loadBalancingStrategy.selectServer(servers);
        return webClient.get()
                .uri(selectedServer.getUrl() + requestUri)
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }
}