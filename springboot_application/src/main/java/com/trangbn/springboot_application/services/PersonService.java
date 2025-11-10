package com.trangbn.springboot_application.services;

import com.trangbn.springboot_application.constants.SchoolConstant;
import com.trangbn.springboot_application.model.Person;
import com.trangbn.springboot_application.model.Roles;
import com.trangbn.springboot_application.repository.PersonRepository;
import com.trangbn.springboot_application.repository.RolesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private RolesRepository rolesRepository;

    public boolean createNewPerson(Person person){
        Roles role = rolesRepository.getByRoleName(SchoolConstant.ADMIN_ROLE);
        person.setRoles(role);
        Person savedPerson  = personRepository.save(person);

        return savedPerson.getPersonId() > 0;
    }

}
