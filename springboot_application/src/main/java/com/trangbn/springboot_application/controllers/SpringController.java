package com.trangbn.springboot_application.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SpringController {
    @RequestMapping(value={"/hello", "/",""})
    public String hello(Model model) {
        model.addAttribute("username", "Trangbn");
        return "index.html";
    }
}
