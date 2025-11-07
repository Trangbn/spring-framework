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
import java.util.Optional;

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
        Contact savedContact = contactRepository.save(contact);

        return savedContact.getContactId() > 0;
    }

    public List<Contact> findOpenStatusMessage(){
        return contactRepository.findByStatus(SchoolConstant.OPEN);
    }

    public boolean updateMsgStatus(int contactId, String updateBy){

        Optional<Contact> contact = contactRepository.findById(contactId);
        contact.ifPresent(contact1 -> {
            contact1.setStatus(SchoolConstant.CLOSE);
            contact1.setUpdatedBy(updateBy);
            contact1.setUpdatedAt(LocalDateTime.now());
        });


        Contact updatedContact = contactRepository.save(contact.get());
        return updatedContact.getUpdatedBy() != null;
    }
}
