package com.sundaday.jeu;

import javax.swing.*;
import java.awt.*;

public class Tuyau {

    //Variable
    private int largeur;
    private int hauteur;
    private int x;
    private int y;
    private String strImage;
    private ImageIcon icoTuyau;
    private Image imgTuyau;

    //Constructeur
    public Tuyau(int x,int y, String strImage){
        this.largeur = 50;
        this.hauteur = 300;
        this.x = x;
        this.y = y;
        this.strImage = strImage;
        this.icoTuyau = new ImageIcon(getClass().getResource(this.strImage));
        this.imgTuyau = this.icoTuyau.getImage();
    }

    //Getter
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
    public String getStrImage() {
        return strImage;
    }
    public ImageIcon getIcoTuyau() {
        return icoTuyau;
    }
    public Image getImgTuyau() {
        return imgTuyau;
    }

    //Setter
    public void setX(int x) {
        this.x = x;
    }
    public void setY(int y) {
        this.y = y;
    }
}
