package com.tgroup.trangbn.implementations;

import com.tgroup.trangbn.interfaces.Tyres;
import org.springframework.stereotype.Component;

@Component
public class MichelinTyre implements Tyres {
    @Override
    public String rotate() {
        return "Michelin Tyre is rotating";
    }

    @Override
    public String stop() {
        return "Vehicle stopped with the help of Michelin tyres";
    }
}
