package com.example.loadbalancer.controller;

import com.example.loadbalancer.service.LoadBalancerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ProxyController {

    private final LoadBalancerService loadBalancerService;

    @Autowired
    public ProxyController(LoadBalancerService loadBalancerService) {
        this.loadBalancerService = loadBalancerService;
    }

    @GetMapping("/**")
    public ResponseEntity<String> handleGetRequests() {
        return loadBalancerService.forwardRequest();
    }

    @PostMapping("/**")
    public ResponseEntity<String> handlePostRequests(@RequestBody String body) {
        return loadBalancerService.forwardRequest(body);
    }

    @PutMapping("/**")
    public ResponseEntity<String> handlePutRequests(@RequestBody String body) {
        return loadBalancerService.forwardRequest(body);
    }

    @DeleteMapping("/**")
    public ResponseEntity<String> handleDeleteRequests() {
        return loadBalancerService.forwardRequest();
    }
}