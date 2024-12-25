package com.tgroup.trangbn.implementations;

import com.tgroup.trangbn.interfaces.Tyres;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
public class BridgeStoneTyre implements Tyres {

    @Override
    public String rotate() {
        return "BridgeStone Tyre is rotating";
    }

    @Override
    public String stop() {
        return "Vehicle stopped with the help of BridgeStone tyres";
    }
}
