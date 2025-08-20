package com.qbtechlabs.loadbalancer.service.impl;

import com.qbtechlabs.loadbalancer.domain.Server;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class HealthCheckService {

    private final RestTemplate restTemplate;
    private final List<Server> servers;

    @Value("${healthcheck.interval:5000}")
    private long healthCheckInterval;

    public HealthCheckService(RestTemplate restTemplate, List<Server> servers) {
        this.restTemplate = restTemplate;
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
                String response = restTemplate.getForObject(server.getUrl() + "/health", String.class);
                server.setAlive(response != null && response.contains("UP"));
            } catch (Exception e) {
                server.setAlive(false);
            }
        }
    }


}