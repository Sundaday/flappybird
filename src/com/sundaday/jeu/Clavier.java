package com.sundaday.jeu;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Clavier implements KeyListener {
    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            Main.scene.flappyBird.monte();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
