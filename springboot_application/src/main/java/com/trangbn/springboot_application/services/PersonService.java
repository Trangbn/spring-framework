package com.trangbn.springboot_application.services;

import com.trangbn.springboot_application.constants.SchoolConstant;
import com.trangbn.springboot_application.model.Person;
import com.trangbn.springboot_application.model.Roles;
import com.trangbn.springboot_application.repository.PersonRepository;
import com.trangbn.springboot_application.repository.RolesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private RolesRepository rolesRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public boolean createNewPerson(Person person){
        Roles role = rolesRepository.getByRoleName(SchoolConstant.STUDENT_ROLE);
        person.setRoles(role);
        person.setPwd(passwordEncoder.encode(person.getPwd()));
        Person savedPerson  = personRepository.save(person);

        return savedPerson.getPersonId() > 0;
    }

}
