package com.spring.cosumption.spring_consumption.config;

import feign.RequestInterceptor;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.util.Base64;

@Configuration
public class ProjectConfiguration {

    @Bean
    public RequestInterceptor basicAuthenticationInterceptor() {
        return template -> {
            String auth = "trangbn580@gmail.com:123456789";
            String encodedAuth = Base64.getEncoder().encodeToString(auth.getBytes());
            template.header("Authorization", "Basic " + encodedAuth);
        };
    }

    @Bean
    public RestTemplate restTemplate(){
        RestTemplateBuilder restTemplateBuilder = new RestTemplateBuilder();
        return restTemplateBuilder.basicAuthentication("trangbn580@gmail.com", "123456789").build();
    }

}
