package com.tgroup.trangbn.implementations;

import com.tgroup.trangbn.interfaces.Speaker;
import com.tgroup.trangbn.model.Song;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component(value = "boseSpeaker")
@Primary
public class BoseSpeaker implements Speaker {
    @Override
    public String makeSound(Song song) {
        return "Playing the song "+ song.getTitle()+ " by "
                + song.getSingerName()+
                " with Bose speakers";
    }
}
