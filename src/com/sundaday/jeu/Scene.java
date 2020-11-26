package com.sundaday.jeu;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Scene extends JPanel {

    private ImageIcon icoBandeFond;
    private Image imgBandefond;

    public Tuyau tuyauHaut1;
    public Tuyau tuyauBas1;
    public Tuyau tuyauHaut2;
    public Tuyau tuyauBas2;
    public Tuyau tuyauHaut3;
    public Tuyau tuyauBas3;

    public PersoFlappy flappyBird;
    private int score;
    private Font police;

    private final int LARGEUR_BANDE_FOND = 140;
    private final int DISTANCE_TUYAU = 250;
    private final int ECART_TUYAU = 120;

    public int xFond;
    private int xTuyau;
    public boolean finDuJeu;
    private Random hasard;

    //Constructeur//
    public Scene(){
        super();
        this.icoBandeFond = new ImageIcon(getClass().getResource("/Image/bandeFondEcran.png"));
        this.imgBandefond = this.icoBandeFond.getImage();

        this.xFond = 0;
        this.xTuyau = 400;
        this.finDuJeu = false;

        this.tuyauHaut1 = new Tuyau(this.xTuyau,-150,"/Image/tuyauHaut.png");
        this.tuyauBas1 = new Tuyau(this.xTuyau,250,"/Image/tuyauBas.png");
        this.tuyauHaut2 = new Tuyau(this.xTuyau +  this.DISTANCE_TUYAU,-100,"/Image/tuyauHaut.png");
        this.tuyauBas2 = new Tuyau(this.xTuyau +  this.DISTANCE_TUYAU,300,"/Image/tuyauBas.png");
        this.tuyauHaut3 = new Tuyau(this.xTuyau +  this.DISTANCE_TUYAU*2,-120,"/Image/tuyauHaut.png");
        this.tuyauBas3 = new Tuyau(this.xTuyau +  this.DISTANCE_TUYAU*2,280,"/Image/tuyauBas.png");

        this.flappyBird = new PersoFlappy(100,150,"/Image/oiseau1.png");
        hasard = new Random();

        this.setFocusable(true);
        this.requestFocusInWindow();
        this.addKeyListener(new Clavier());

        this.score = 0;
        this.police = new Font("Arial",Font.PLAIN,40)
        ;
        Thread chronoEcran = new Thread(new Chrono());
        chronoEcran.start();
    }

    //Methodes
    private void deplacementFond(Graphics g){
        if (this.xFond == -this.LARGEUR_BANDE_FOND){
            this.xFond = 0;
        }
        g.drawImage(this.imgBandefond,this.xFond,0,null);
        g.drawImage(this.imgBandefond,this.xFond + this.LARGEUR_BANDE_FOND,0,null);
        g.drawImage(this.imgBandefond,this.xFond + this.LARGEUR_BANDE_FOND*2,0,null);
        g.drawImage(this.imgBandefond,this.xFond + this.LARGEUR_BANDE_FOND*3,0,null);
    }
    private void score(){
        if(this.tuyauBas1.getX() + this.tuyauBas1.getLargeur() == 95 || this.tuyauBas2.getX() + this.tuyauBas2.getLargeur() == 95 || this.tuyauBas3.getX() + this.tuyauBas3.getLargeur() == 95){
            this.score++;
            Audio.playSound("/Audio/sonnerie.wav");
        }
    }

    //deplacement tuyau
    private void deplacementTuyau(Graphics g){
        //Tuyau 1
        this.tuyauHaut1.setX(this.tuyauHaut1.getX() - 1);
        this.tuyauBas1.setX(this.tuyauHaut1.getX());

        if(this.tuyauHaut1.getX() == -100){
            this.tuyauHaut1.setX(this.tuyauHaut3.getX() + this.DISTANCE_TUYAU);
            this.tuyauHaut1.setY(-100 - 10* this.hasard.nextInt(18));
            this.tuyauBas1.setY(this.tuyauHaut1.getY() + this.tuyauHaut1.getHauteur() + this.ECART_TUYAU);
        }
        g.drawImage(tuyauHaut1.getImgTuyau(), this.tuyauHaut1.getX(),this.tuyauHaut1.getY(),null);
        g.drawImage(tuyauBas1.getImgTuyau(), this.tuyauBas1.getX(),this.tuyauBas1.getY(),null);

        //Tuyau 2
        this.tuyauHaut2.setX(this.tuyauHaut2.getX() - 1);
        this.tuyauBas2.setX(this.tuyauHaut2.getX());

        if(this.tuyauHaut2.getX() == -100){
            this.tuyauHaut2.setX(this.tuyauHaut1.getX() + this.DISTANCE_TUYAU);
            this.tuyauHaut2.setY(-100 - 10* this.hasard.nextInt(18));
            this.tuyauBas2.setY(this.tuyauHaut2.getY() + this.tuyauHaut2.getHauteur() + this.ECART_TUYAU);
        }
        g.drawImage(tuyauHaut2.getImgTuyau(), this.tuyauHaut2.getX(),this.tuyauHaut2.getY(),null);
        g.drawImage(tuyauBas2.getImgTuyau(), this.tuyauBas2.getX(),this.tuyauBas2.getY(),null);

        //Tuyau 3
        this.tuyauHaut3.setX(this.tuyauHaut3.getX() - 1);
        this.tuyauBas3.setX(this.tuyauHaut3.getX());

        if(this.tuyauHaut3.getX() == -100){
            this.tuyauHaut3.setX(this.tuyauHaut2.getX() + this.DISTANCE_TUYAU);
            this.tuyauHaut3.setY(-100 - 10* this.hasard.nextInt(18));
            this.tuyauBas3.setY(this.tuyauHaut3.getY() + this.tuyauHaut3.getHauteur() + this.ECART_TUYAU);
        }
        g.drawImage(tuyauHaut3.getImgTuyau(), this.tuyauHaut3.getX(),this.tuyauHaut3.getY(),null);
        g.drawImage(tuyauBas3.getImgTuyau(), this.tuyauBas3.getX(),this.tuyauBas3.getY(),null);

    }

    private boolean collisionFlappy() {
        boolean finDuJeu = false;
        // proche tuyau1
        if (this.flappyBird.getX() + this.flappyBird.getLargeur() > this.tuyauBas1.getX() - 20 && this.flappyBird.getX() < this.tuyauBas1.getX() + this.tuyauBas1.getLargeur() + 20) {
            finDuJeu = this.flappyBird.collision(this.tuyauBas1);
            if (finDuJeu == false) {
                finDuJeu = this.flappyBird.collision(this.tuyauHaut1);
            }
        } else {
            // proche tuyau2
            if (this.flappyBird.getX() + this.flappyBird.getLargeur() > this.tuyauBas2.getX() - 20 && this.flappyBird.getX() < this.tuyauBas2.getX() + this.tuyauBas2.getLargeur() + 20) {
                finDuJeu = this.flappyBird.collision(this.tuyauBas2);
                if (finDuJeu == false) {
                    finDuJeu = this.flappyBird.collision(this.tuyauHaut2);
                }
            } else {
                // proche tuyau3
                if (this.flappyBird.getX() + this.flappyBird.getLargeur() > this.tuyauBas3.getX() - 20 && this.flappyBird.getX() < this.tuyauBas3.getX() + this.tuyauBas3.getLargeur() + 20) {
                    finDuJeu = this.flappyBird.collision(this.tuyauBas3);
                    if (finDuJeu == false) {
                        finDuJeu = this.flappyBird.collision(this.tuyauHaut3);
                    }
                } else {
                    // contact avec le plafond ou le sol
                    if (this.flappyBird.getY() < 0 || this.flappyBird.getY() + this.flappyBird.getHauteur() > 355) {
                        finDuJeu = true;
                    } else {
                        finDuJeu = false;
                    }
                }
            }
        }
        return finDuJeu;
    }



    //Affichage
    public void paintComponent(Graphics g){
        this.deplacementFond(g);
        this.deplacementTuyau(g);
        this.score();
        g.setFont(police);
        g.drawString("" + score,140, 50);
        this.finDuJeu = this.collisionFlappy();
        this.flappyBird.setY(this.flappyBird.getY()+1);
        g.drawImage(this.flappyBird.getImgBird(),this.flappyBird.getX(), this.flappyBird.getY(),null);
        if (this.finDuJeu == true) {
            g.drawString("Fin du jeu", 60, 200);

        }
    }
}