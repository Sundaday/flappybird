package com.sundaday.jeu;

import javax.sound.sampled.*;

import java.io.IOException;

public class Audio {
    //variables
    private Clip clip;
    //constructeur
    public Audio(String son){
        try{
            AudioInputStream audio = AudioSystem.getAudioInputStream(getClass().getResource(son));
            clip = AudioSystem.getClip();
            clip.open(audio);
        } catch (IOException | UnsupportedAudioFileException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }
    //getters
    public Clip getClip() {
        return clip;
    }
    //methodes
    public void play(){
        clip.start();
    }
    public void stop(){
        clip.stop();
    }
    public static void playSound(String son){
        Audio s = new Audio(son);
        s.play();
    }

}
