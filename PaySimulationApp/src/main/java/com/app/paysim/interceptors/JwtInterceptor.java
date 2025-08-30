package com.app.paysim.interceptors;

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
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("Missing auth-token.......");
            return false;
        }
        try {
            // Validate and decode the token
            ObjectMapper objectMapper = new ObjectMapper();
            Map<String, Object> claims = objectMapper.readValue(userService.decodeToken(authHeader), Map.class);
            // Set claims into the request attribute to use in controller if needed for logging or other purposes
            request.setAttribute("claims", claims);
            logger.info("JWT token validated successfully, claims set in request attributes");
        } catch (Exception exception) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("Invalid or expired JWT token");
            return false;
        }
        return true;
    }
}
