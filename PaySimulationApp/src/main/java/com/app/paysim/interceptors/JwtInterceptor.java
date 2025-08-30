package com.app.paysim.interceptors;

import com.app.paysim.exceptions.InvalidTokenException;
import com.app.paysim.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Map;


@Component
public class JwtInterceptor implements HandlerInterceptor {
    private static Logger logger = org.slf4j.LoggerFactory.getLogger(JwtInterceptor.class);
    @Autowired
    private UserService userService;

    // List of endpoints to exclude from interception
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        logger.info("Inside JwtInterceptor preHandle method - Intercepting request: " + request.getRequestURI());
        // Extract the Authorization header
        String authHeader = request.getHeader("auth-token");
        if (authHeader == null || authHeader.isEmpty()) {
            logger.warn("Missing auth-token header");
            throw new InvalidTokenException("Missing auth-token header.....");
        }
        try {
            // Validate and decode the token
            ObjectMapper objectMapper = new ObjectMapper();
            Map<String, Object> claims = objectMapper.readValue(userService.decodeToken(authHeader), Map.class);
            // Set claims into the request attribute to use in controller if needed for logging or other purposes
            request.setAttribute("claims", claims);
            logger.info("JWT token validated successfully, claims set in request attributes");
        } catch (Exception exception) {
            throw new InvalidTokenException("Invalid or expired auth-token");
        }
        return true;
    }
}
