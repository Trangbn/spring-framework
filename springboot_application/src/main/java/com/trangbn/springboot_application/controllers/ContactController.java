package com.trangbn.springboot_application.controllers;

import com.trangbn.springboot_application.model.Contact;
import com.trangbn.springboot_application.services.ContactServices;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
@Slf4j
public class ContactController {

    @Autowired
    private ContactServices contactServices;

    @RequestMapping("/contact")
    public String displayContactPage(){
        return "contact.html";
    }

    @RequestMapping(value = "/saveMsg", method = POST)
    public ModelAndView saveMessage(Contact contact){
        contactServices.saveMessageDetails(contact);
        return new ModelAndView("redirect:/contact");
    }

}
