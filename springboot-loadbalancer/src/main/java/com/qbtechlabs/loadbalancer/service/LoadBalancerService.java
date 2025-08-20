package com.qbtechlabs.loadbalancer.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.Map;

@Service
public interface LoadBalancerService{
    ResponseEntity<String> forwardRequest(String method, String requestUri, Map<String, String> headers, String body);
}