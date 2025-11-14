package com.trangbn.springboot_application.services;

import com.trangbn.springboot_application.constants.SchoolConstant;
import com.trangbn.springboot_application.model.Contact;
import com.trangbn.springboot_application.repository.ContactRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
        Contact savedContact = contactRepository.save(contact);

        return savedContact.getContactId() > 0;
    }

//    public List<Contact> findOpenStatusMessage(){
//        int pageSize = 5;
//        Pageable pageable = PageRequest.of(pageNum - 1, pageSize,
//                sortDir.equals("asc") ? Sort.by(sortField).ascending()
//                        : Sort.by(sortField).descending());
//        return contactRepository.findByStatus(SchoolConstant.OPEN, );
//    }

    public Page<Contact> findMsgsWithOpenStatus(int pageNum, String sortField, String sortDir){
        int pageSize = 5;
        Pageable pageable = PageRequest.of(pageNum - 1, pageSize,
                sortDir.equals("asc") ? Sort.by(sortField).ascending()
                        : Sort.by(sortField).descending());
        Page<Contact> msgPage = contactRepository.findByStatus(SchoolConstant.OPEN, pageable);
        return msgPage;
    }

    public boolean updateMsgStatus(int contactId){

        Optional<Contact> contact = contactRepository.findById(contactId);
        contact.ifPresent(contact1 -> {
            contact1.setStatus(SchoolConstant.CLOSE);
        });


        Contact updatedContact = contactRepository.save(contact.get());
        return updatedContact.getUpdatedBy() != null;
    }

    public boolean updateStatusById(int contactId){
        int rows = contactRepository.updateStatusById(SchoolConstant.CLOSE, contactId);
        return rows > 0;
    }
}
