package com.qbtechlabs.loadbalancer.controller;

import com.qbtechlabs.loadbalancer.service.LoadBalancerService;
import com.qbtechlabs.loadbalancer.util.ProxyUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ProxyController {

    private final LoadBalancerService loadBalancerService;
    private final ProxyUtil proxyUtil;
    public ProxyController(@Lazy LoadBalancerService loadBalancerService,
                           @Lazy ProxyUtil proxyUtil) {
        this.loadBalancerService = loadBalancerService;
        this.proxyUtil = proxyUtil;
    }
    /*******************************************************************************************************************
     * Extracts headers from the HttpServletRequest and returns them as a Map.
     *
     * @param request the HttpServletRequest from which to extract headers
     * @return a Map containing header names and values
     ******************************************************************************************************************/
    @RequestMapping(value = "/**", method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.PATCH, RequestMethod.OPTIONS, RequestMethod.HEAD, RequestMethod.TRACE})
    public ResponseEntity<?> handleAllRequests(@RequestBody(required = false) String body, HttpServletRequest request) {
        return loadBalancerService.forwardRequest(request.getMethod(),
                request.getRequestURI(), proxyUtil.extractHeaders(request), body);
    }

}