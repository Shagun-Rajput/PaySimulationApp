package com.qbtechlabs.loadbalancer.service;

import com.qbtechlabs.loadbalancer.domain.Server;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface LoadBalancerService {
    String forwardRequest(String requestUri, List<Server> servers);

    String forwardRequest(String requestUri, String body, List<Server> servers);

    ResponseEntity<String> forwardRequest();

    ResponseEntity<String> forwardRequest(String body);
}
