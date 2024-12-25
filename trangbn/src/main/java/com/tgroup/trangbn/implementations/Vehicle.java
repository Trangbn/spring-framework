package com.tgroup.trangbn.implementations;

import com.tgroup.trangbn.services.VehicleService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Data
public class Vehicle {
    private String name;
    private String type;

    @Autowired
    private VehicleService vehicleService;
}
