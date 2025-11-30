package com.trangbn.springboot_application.controllers;

import com.trangbn.springboot_application.model.Person;
import com.trangbn.springboot_application.repository.PersonRepository;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
public class DashboardController {

    @Autowired
    PersonRepository personRepository;

    @Value("${school.pagesize}")
    private int pageSize;

    @Value("${school.contact.successMsg}")
    private String message;

    @RequestMapping("/dashboard")
    public String displayDashboard(Model model, Authentication authentication, HttpSession session) {

        Person person = personRepository.readByEmail(authentication.getName());
        model.addAttribute("username", person.getName());
        model.addAttribute("roles", authentication.getAuthorities().toString());
        if (person.getSchoolClass() != null && !person.getSchoolClass().getName().isEmpty()) {
            model.addAttribute("enrolledClass", person.getSchoolClass().getName());
        }
        logMessage();
        session.setAttribute("loggedInPerson", person);
        return "dashboard.html";
    }

    private void logMessage(){
        log.error("Default page size: " + pageSize);
        log.error("Default message: " + message);
    }

}