package com.spring.cosumption.spring_consumption.config;

import feign.RequestInterceptor;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.boot.web.reactive.function.client.WebClientCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.ExchangeFilterFunctions;
import org.springframework.web.reactive.function.client.WebClient;

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

    @Bean
    public WebClient webClient(){
        return WebClient.builder().filter(ExchangeFilterFunctions.basicAuthentication("trangbn580@gmail.com", "123456")).build();
    }

}
