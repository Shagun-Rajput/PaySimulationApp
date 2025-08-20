package com.qbtechlabs.loadbalancer.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class LoadBalancerConfig {
    // This class can be used to define any additional beans or configurations
    // related to the load balancer if needed in the future.
    @Bean
    public WebClient webClient() {
        return WebClient.builder()
                .baseUrl("http://localhost:8080") // Default base URL, can be overridden
                .build();
    }
}
