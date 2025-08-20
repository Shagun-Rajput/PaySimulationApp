package com.qbtechlabs.loadbalancer.service.health;

import com.qbtechlabs.loadbalancer.configuration.AppProperties;
import com.qbtechlabs.loadbalancer.domain.Server;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

import static com.qbtechlabs.loadbalancer.constant.Constants.*;

@Service
@ConditionalOnProperty(value = LIVE_SERVERS_PROVIDED)
public class ServerHealthCheck {
    private static final Logger LOGGER = org.slf4j.LoggerFactory.getLogger(ServerHealthCheck.class);
    private final WebClient webClient;
    private final List<Server> servers;
    private final AppProperties appProperties;
    public ServerHealthCheck(WebClient webClient, List<Server> servers, AppProperties appProperties) {
        this.webClient = webClient;
        this.servers = servers;
        this.appProperties = appProperties;
    }
    /*******************************************************************************************************************
     * This method starts a thread that periodically checks the health of all servers.
     * It logs the status of each server and updates their alive status based on the response from the health endpoint.
     ******************************************************************************************************************/
    @PostConstruct
    public void startHealthCheck() {
        new Thread(() -> {
            while (true) {
                LOGGER.info(CHECKING_SERVER_HEALTH);
                checkServersHealth();
                try {
                    Thread.sleep(appProperties.getHealthCheckInterval());
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }).start();
    }
    /*******************************************************************************************************************
     * This method checks the health of all servers in the list.
     * It sends a GET request to each server's health endpoint and updates the server's alive status.
     ******************************************************************************************************************/
    private void checkServersHealth() {
        servers.parallelStream().forEach(server -> {
            try {
                String response = webClient.get()
                        .uri(server.getUrl() + URI_HEALTH)
                        .retrieve()
                        .bodyToMono(String.class)
                        .block();
                server.setAlive(response != null && response.contains(UP));
            } catch (Exception exception) {
                server.setAlive(Boolean.FALSE);
            }
        });
    }
    /******************************************************************************************************************/
}