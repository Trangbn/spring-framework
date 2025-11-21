package com.spring.cosumption.spring_consumption.config;

import feign.RequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Base64;

@Configuration
public class ProjectConfiguration {

    @Bean
    public RequestInterceptor basicAuthenticationInterceptor() {
        return template -> {
            String auth = "trangbn580@gmail.com:123456";
            String encodedAuth = Base64.getEncoder().encodeToString(auth.getBytes());
            template.header("Authorization", "Basic " + encodedAuth);
        };
    }
}
