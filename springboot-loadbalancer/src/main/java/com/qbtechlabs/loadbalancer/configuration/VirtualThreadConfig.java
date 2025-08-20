package com.qbtechlabs.loadbalancer.configuration;

import org.apache.catalina.connector.Connector;
import org.apache.coyote.ProtocolHandler;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.Executors;

@Configuration
public class VirtualThreadConfig {
    @Bean
    public WebServerFactoryCustomizer<TomcatServletWebServerFactory> tomcatVirtualThreads() {
        return factory -> factory.addConnectorCustomizers((Connector connector) -> {
            ProtocolHandler protocolHandler = connector.getProtocolHandler();
            if (protocolHandler != null) {
                protocolHandler.setExecutor(Executors.newVirtualThreadPerTaskExecutor());
            }
        });
    }
}