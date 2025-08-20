package com.qbtechlabs.loadbalancer.util;

import com.qbtechlabs.loadbalancer.domain.Server;
import com.qbtechlabs.loadbalancer.enums.NumberEnum;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@ConfigurationProperties(prefix = "backendservers")
public class ServerUtil {
    private static Logger logger = org.slf4j.LoggerFactory.getLogger(ServerUtil.class);
    private List<Server> servers;

    public List<Server> getServers() {
        return servers;
    }

    public void setServers(List<Server> servers) {
        this.servers = servers;
    }
    @PostConstruct
    private void initializeServers() {
        if (servers == null || servers.isEmpty()) {
            throw new IllegalStateException("Server list cannot be empty. Please configure servers in the application.yml file.");
        }
        logger.info("Initializing servers :{}", servers.stream().map(Server::getUrl).toList());
        servers.parallelStream().forEach(server -> {
            server.setAlive(true);
            server.setActiveConnections(NumberEnum.ZERO.value());
        });
    }
}