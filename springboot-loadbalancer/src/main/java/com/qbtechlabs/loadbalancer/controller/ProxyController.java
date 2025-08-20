package com.qbtechlabs.loadbalancer.controller;

import com.qbtechlabs.loadbalancer.enums.NumberEnum;
import com.qbtechlabs.loadbalancer.service.LoadBalancerService;
import com.qbtechlabs.loadbalancer.util.ProxyUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.qbtechlabs.loadbalancer.constant.Constants.*;

@RestController
public class ProxyController {
    /*******************************************************************************************************************
     * ProxyController is a REST controller that handles all HTTP requests and forwards them to the LoadBalancerService.
     * It extracts headers from the HttpServletRequest and uses the LoadBalancerService to forward the request.
     ******************************************************************************************************************/
    private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(ProxyController.class);
    private final LoadBalancerService loadBalancerService;
    private final ProxyUtil proxyUtil;
    public ProxyController(LoadBalancerService loadBalancerService,
                           ProxyUtil proxyUtil) {
        this.loadBalancerService = loadBalancerService;
        this.proxyUtil = proxyUtil;
    }
    /*******************************************************************************************************************
     * Extracts headers from the HttpServletRequest and returns them as a Map.
     *
     * @param request the HttpServletRequest from which to extract headers
     * @return a Map containing header names and values
     ******************************************************************************************************************/
    @RequestMapping(value = URI_ALL, method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.PATCH, RequestMethod.OPTIONS, RequestMethod.HEAD, RequestMethod.TRACE})
    public ResponseEntity<?> handleAllRequests(@RequestBody(required = false) String body, HttpServletRequest request) {
        long startTime = System.nanoTime();
        ResponseEntity<String> response = null;
        try {
            response = loadBalancerService.forwardRequest(request.getMethod(),
                    request.getRequestURI(), proxyUtil.extractHeaders(request), body);
            logger.info(MSG_LOADBALANCER_HANDLED, (System.nanoTime() - startTime) / TEN_LAC);
            return response;
        }finally {
            // Resetting startTime to zero to avoid memory leaks
            startTime= NumberEnum.ZERO.value();
            response =null;
        }
    }

}