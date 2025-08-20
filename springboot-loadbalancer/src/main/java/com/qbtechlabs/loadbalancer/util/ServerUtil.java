package com.qbtechlabs.loadbalancer.util;

import com.qbtechlabs.loadbalancer.domain.Server;
import com.qbtechlabs.loadbalancer.enums.NumberEnum;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.qbtechlabs.loadbalancer.constant.Constants.*;

@Service
@ConfigurationProperties(prefix = BACKEND_SERVERS)
public class ServerUtil {
    private static Logger logger = org.slf4j.LoggerFactory.getLogger(ServerUtil.class);
    private List<Server> servers;
    public List<Server> getServers() {
        return servers;
    }
    public void setServers(List<Server> servers) {
        this.servers = servers;
    }
    /*******************************************************************************************************************
     * Initializes the server list from the application properties.
     * Sets each server's alive status to true and active connections to zero.
     * Throws an exception if the server list is empty.
     ******************************************************************************************************************/
    @PostConstruct
    private void initializeServers() {
        if (servers == null || servers.isEmpty()) {
            throw new IllegalStateException(MSG_SERVER_LIST_CNNOT_BE_EMPTY);
        }
        logger.info(MSG_INIT_SERVERS, servers.stream().map(Server::getUrl).toList());
        servers.parallelStream().forEach(server -> {
            server.setAlive(true);
            server.setActiveConnections(NumberEnum.ZERO.value());
        });
    }
}