package com.tgroup.trangbn.services;

import com.tgroup.trangbn.interfaces.LogAspect;
import com.tgroup.trangbn.interfaces.Speaker;
import com.tgroup.trangbn.interfaces.Tyres;
import com.tgroup.trangbn.model.Song;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class VehicleService {

    @Autowired
    private Tyres tyres;

    @Autowired
    private Speaker speaker;

    public void setTyres(Tyres tyres) {
        this.tyres = tyres;
    }

    public void setSpeaker(Speaker speaker) {
        this.speaker = speaker;
    }

    public Tyres getTyres() {
        return tyres;
    };

    public Speaker getSpeaker() {
        return speaker;
    };

    @LogAspect
    public String playMusic(boolean vehicleStarted, Song song) {
        return speaker.makeSound(song);
    };

    public String moveVehicle(boolean vehicleStarted) {
//        throw new NullPointerException("Vehicle is not started");
        return tyres.rotate();
    };

    public String applyBreaks(boolean vehicleStarted) {
        return tyres.stop();
    };

}
