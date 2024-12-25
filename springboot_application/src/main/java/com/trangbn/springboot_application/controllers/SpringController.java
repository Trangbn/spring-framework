package com.trangbn.springboot_application.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SpringController {
    @RequestMapping("/hello")
    public String hello() {
        return "index.html";
    }
}
