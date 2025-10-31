package com.trangbn.springboot_application.services;

import com.trangbn.springboot_application.constants.SchoolConstant;
import com.trangbn.springboot_application.model.Contact;
import com.trangbn.springboot_application.repository.ContactRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
//@RequestScope
@SessionScope
public class ContactServices {

    @Autowired
    private ContactRepository contactRepository;

    public ContactServices() {
        System.out.println("Contact service bean initialized");
    }

    public boolean saveMessageDetails(Contact contact){
        contact.setStatus(SchoolConstant.OPEN);
        contact.setCreatedBy(SchoolConstant.ANONYMOUS);
        contact.setCreatedAt(LocalDateTime.now());
        int result = contactRepository.saveContactMsg(contact);
        log.info(contact.toString());
        return result > 0;
    }

    public List<Contact> findOpenStatusMessage(){
        return contactRepository.findOpenStatusMessage(SchoolConstant.OPEN);
    }

    public boolean updateMsgStatus(int id, String updateBy){
        int result = contactRepository.updateMsgStatus(id, SchoolConstant.CLOSE, updateBy);
        return result > 0;
    }
}
