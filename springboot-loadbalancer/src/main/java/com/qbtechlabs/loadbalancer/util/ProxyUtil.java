package com.qbtechlabs.loadbalancer.util;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

@Component
public class ProxyUtil {
    /*******************************************************************************************************************
     * Extracts headers from the HttpServletRequest and returns them as a Map.
     ******************************************************************************************************************/
    public Map<String, String> extractHeaders(HttpServletRequest request) {
        Map<String, String> headers = new HashMap<>();
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String headerName = headerNames.nextElement();
            headers.put(headerName, request.getHeader(headerName));
        }
        return headers;
    }
}
