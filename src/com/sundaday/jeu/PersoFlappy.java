package com.sundaday.jeu;

import javax.swing.*;
import java.awt.*;

import static java.lang.Thread.sleep;

public class PersoFlappy implements Runnable {

    //Variable

    private int largeur;
    private int hauteur;
    private int x;
    private int y;
    private int dy;
    private String strImage;
    private ImageIcon icoBird;
    private Image imgBird;
    private final int PAUSE = 10;

    //Constructeur
    public PersoFlappy(int x, int y, String strImage) {
        this.largeur = 34;
        this.hauteur = 24;
        this.x = x;
        this.y = y;
        this.strImage = strImage;
        this.icoBird = new ImageIcon(getClass().getResource(this.strImage));
        this.imgBird = this.icoBird.getImage();
        Thread chronoWings = new Thread((this));
        chronoWings.start();
    }

    //Getters
    public int getLargeur() {
        return largeur;
    }

    public int getHauteur() {
        return hauteur;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Image getImgBird() {
        return imgBird;
    }

    //Setters

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    //Methodes
    public void monte() {
        this.dy = 50;
    }

    private void wings(int dy) {
        System.out.println("1");
        if (dy > 10) {
            this.icoBird = new ImageIcon(getClass().getResource("/Image/oiseau2.png"));
            this.imgBird = this.icoBird.getImage();
            this.y = this.y - 3;
        } else if (dy > 5) {
            this.y = this.y - 2;
        } else if (dy > 1) {
            this.y = this.y - 1;
        } else if (dy == 1) {
            this.icoBird = new ImageIcon(getClass().getResource("/Image/oiseau1.png"));
            this.imgBird = this.icoBird.getImage();
        }
    }

    public boolean collision(Tuyau tuyau) {
        if (tuyau.getY() < 50) { //detecte un tuyau haut
            if (this.y <= tuyau.getY() + tuyau.getHauteur() && this.x + this.largeur >= tuyau.getX() && this.x <= tuyau.getX() + tuyau.getLargeur()) {
                return true;
            } else {
                return false;
            }
        } else if (this.y + this.hauteur >= tuyau.getY() && this.x + this.largeur >= tuyau.getX() && this.x <= tuyau.getX() + tuyau.getLargeur()) {
                return true;
            } else {
                return false;
            }
        }


    @Override
    public void run() {
        while (true) {
            this.wings(dy);
            this.dy--;
            try {
                sleep(PAUSE);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
