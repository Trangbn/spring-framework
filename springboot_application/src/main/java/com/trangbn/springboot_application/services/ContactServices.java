package com.trangbn.springboot_application.services;

import com.trangbn.springboot_application.model.Contact;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.context.annotation.SessionScope;

@Service
@Slf4j
//@RequestScope
@SessionScope
public class ContactServices {

    private int counter = 0;

    public ContactServices() {
        System.out.println("Contact service bean initialized");
    }

    public boolean saveMessageDetails(Contact contact){
        boolean isSaved=true;
        log.info(contact.toString());
        return isSaved;
    }

    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }
}
