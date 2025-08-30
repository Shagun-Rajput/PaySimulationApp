package com.app.paysim.configuration;

import com.app.paysim.interceptors.JwtInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import static com.app.paysim.constant.ApiURIs.URI_USER_AUTH_GEN;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Autowired
    private JwtInterceptor jwtInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(jwtInterceptor)
                .addPathPatterns("/**") // Intercept all paths
                .excludePathPatterns(URI_USER_AUTH_GEN); // Exclude specific paths - excluding user auth token generation endpoint
    }
}