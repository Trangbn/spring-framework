package com.tgroup.trangbn.implementations;

import com.tgroup.trangbn.interfaces.Speaker;
import com.tgroup.trangbn.model.Song;
import org.springframework.stereotype.Component;

@Component
public class SonySpeaker implements Speaker {
    @Override
    public String makeSound(Song song) {
        return "Playing the song "+ song.getTitle()+ " by "
                + song.getSingerName()+
                " with Sony speakers";
    }
}
