package com.qbtechlabs.loadbalancer.service;

import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface LoadBalancerService {
    ResponseEntity<String> forwardRequest(String method, String requestUri, Map<String, String> headers, String body);
}