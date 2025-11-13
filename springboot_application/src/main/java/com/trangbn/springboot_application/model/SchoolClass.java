package com.trangbn.springboot_application.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.Set;


// @Data will generate toString() method which cause StackOverFlow error when use with JPA mapping (E.g One-To-Many, One-One ..)
// This case use @Getter, Setter instead ( avoid using @Data)
@Slf4j
@Entity
@Table(name="class")
@Getter
@Setter
public class SchoolClass extends  BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int classId;

    @NotBlank(message = "Name must not be blank")
    @Size(min = 3, message = "Nam must be at least 3 characters")
    private String name;

    @OneToMany(mappedBy = "schoolClass", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST, targetEntity = Person.class)
    private Set<Person> persons;

}
