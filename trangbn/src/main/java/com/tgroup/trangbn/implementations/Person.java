package com.tgroup.trangbn.implementations;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Data
public class Person {

    private String name;
    private String address;

    @Autowired
    private final Vehicle vehicle;

    public Person(Vehicle vehicle) {
        System.out.println("This people setting up car");
        this.vehicle = vehicle;
    }
}
