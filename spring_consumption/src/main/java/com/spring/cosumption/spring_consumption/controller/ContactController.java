package com.spring.cosumption.spring_consumption.controller;

import com.spring.cosumption.spring_consumption.model.Contact;
import com.spring.cosumption.spring_consumption.model.Response;
import com.spring.cosumption.spring_consumption.proxy.ContactProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
public class ContactController {

    @Autowired
    ContactProxy contactProxy;

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    WebClient webClient;

    @GetMapping("/getMessages")
    public List<Contact> getMessages(@RequestParam("status") String status) {
        return contactProxy.getMessagesByStatus(status);
    }

    @PostMapping("/saveMsg")
    public ResponseEntity<Response> saveMsg(@RequestBody Contact contact) {
        String uri = "http://localhost:8080/api/contact/saveMessage";
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("invocationFrom", "RestTemplate");
        HttpEntity<Contact> httpEntity = new HttpEntity<>(contact, httpHeaders);
        ResponseEntity<Response> responseEntity = restTemplate.exchange(uri, HttpMethod.POST, httpEntity, Response.class);
        return responseEntity;
    }

    @PostMapping("/saveMsgWebClient")
    public Mono<Response> saveMsgWebClient(@RequestBody Contact contact) {
        String uri = "http://localhost:8080/api/contact/saveMessage";
        return webClient.post().uri(uri)
                .header("invocationFrom", "WebClient")
                .body(Mono.just(contact), Contact.class)
                .retrieve()
                .bodyToMono(Response.class);
    }
}
