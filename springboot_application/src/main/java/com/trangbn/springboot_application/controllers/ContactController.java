package com.trangbn.springboot_application.controllers;

import com.trangbn.springboot_application.model.Contact;
import com.trangbn.springboot_application.services.ContactServices;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.net.Authenticator;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
@Slf4j
public class ContactController {

    @Autowired
    private ContactServices contactServices;

    @RequestMapping("/contact")
    public String displayContactPage(Model model) {
        model.addAttribute("contact", new Contact());
        return "contact.html";
    }

    @RequestMapping(value = "/saveMsg", method = POST)
    public String saveMessage(@Valid @ModelAttribute("contact") Contact contact, Errors errors){

        if (errors.hasErrors()) {
            log.error("Contact form validation failed due to : " + errors.toString());
            return "contact.html";
        }
        contactServices.saveMessageDetails(contact);
        return "redirect:/contact";
    }

    @RequestMapping(value = "/displayMessages/page/{pageNum}", method = GET)
    public ModelAndView displayMessage(Model model, @PathVariable(value = "pageNum", required = false) int pageNum, @RequestParam(value = "sortField", required = false) String sortBy, @RequestParam(value = "sortDir", required = true) String direction) {
        Page<Contact> msgsWithOpenStatus = contactServices.findMsgsWithOpenStatus(pageNum, sortBy, direction);
        List<Contact> contactMsgs = msgsWithOpenStatus.getContent();
        ModelAndView modelAndView = new ModelAndView("messages.html");
        model.addAttribute("currentPage", pageNum);
        model.addAttribute("totalPages", msgsWithOpenStatus.getTotalPages());
        model.addAttribute("totalMsgs", msgsWithOpenStatus.getTotalElements());
        model.addAttribute("sortField", sortBy);
        model.addAttribute("sortDir", direction);
        model.addAttribute("reverseSortDir", direction.equals("asc") ? "desc" : "asc");
        modelAndView.addObject("contactMsgs", contactMsgs);
        return modelAndView;
    }

    @RequestMapping(value = "/closeMsg", method = GET)
    public String closeMessage(@RequestParam int id) {
        contactServices.updateStatusById(id);
        return "redirect:/displayMessages/page/1?sortField=name&sortDir=desc";
    }

}
