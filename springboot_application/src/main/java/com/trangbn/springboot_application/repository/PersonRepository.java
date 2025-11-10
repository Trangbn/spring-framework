package com.trangbn.springboot_application.repository;

import com.trangbn.springboot_application.model.Person;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends CrudRepository<Person,Integer> {

    Person readByEmail(String email);
}
