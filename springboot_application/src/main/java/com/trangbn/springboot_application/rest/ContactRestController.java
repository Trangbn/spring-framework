package com.trangbn.springboot_application.rest;

import com.trangbn.springboot_application.model.Contact;
import com.trangbn.springboot_application.repository.ContactRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/api/contact")
public class ContactRestController {

    @Autowired
    ContactRepository contactRepository;

    @GetMapping("/getMessagesByStatus")
    @ResponseBody
    public List<Contact> getMessagesByStatus(@RequestParam("status") String status) {
        return contactRepository.findByStatus(status);
    }

/* <<<<<<<<<<<<<<  ✨ Windsurf Command ⭐ >>>>>>>>>>>>>>>> */
    /**
     * Retrieve all messages by a given status.
     *
     * @param contact the contact object with the status to be searched.
     * @return a list of contact objects with the given status.
     */
/* <<<<<<<<<<  9095cb44-4bee-47a0-ae33-d46d8bd017a1  >>>>>>>>>>> */
    @GetMapping("/getAllMsgsByStatus")
    @ResponseBody
    public List<Contact> getAllMsgsByStatus(@RequestBody Contact contact) {

        if (null != contact && null != contact.getStatus()) {
            return contactRepository.findByStatus(contact.getStatus());
        } else {
            return List.of();
        }
    }
}
