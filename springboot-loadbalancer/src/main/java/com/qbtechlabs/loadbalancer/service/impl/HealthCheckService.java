package com.qbtechlabs.loadbalancer.service.impl;

import com.qbtechlabs.loadbalancer.domain.Server;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Service
public class HealthCheckService {
    private final WebClient webClient;
    private final List<Server> servers;
    @Value("${healthcheckInterval:5000}")
    private long healthCheckInterval;
    public HealthCheckService(WebClient webClient, List<Server> servers) {
        this.webClient = webClient;
        this.servers = servers;
    }

    @PostConstruct
    public void startHealthCheck() {
        new Thread(() -> {
            while (true) {
                checkServersHealth();
                try {
                    Thread.sleep(healthCheckInterval);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }).start();
    }

    private void checkServersHealth() {
        for (Server server : servers) {
            try {
                String response = webClient.get()
                        .uri(server.getUrl() + "/health")
                        .retrieve()
                        .bodyToMono(String.class)
                        .block();
                server.setAlive(response != null && response.contains("UP"));
            } catch (Exception e) {
                server.setAlive(false);
            }
        }
    }


}