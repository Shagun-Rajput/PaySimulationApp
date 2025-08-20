package com.qbtechlabs.loadbalancer.controller;

import com.qbtechlabs.loadbalancer.service.LoadBalancerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ProxyController {
    /*******************************************************************************************************************
     *  This controller acts as a proxy for all incoming requests.
     * It forwards the requests to the appropriate server based on the load balancing strategy.
     ******************************************************************************************************************/
    private final LoadBalancerService loadBalancerService;
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