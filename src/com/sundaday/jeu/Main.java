package com.sundaday.jeu;

import javax.swing.*;

public class Main {
    public static JFrame fenetre;
    public static Scene scene; // Permet d'acceder à la class Scene partout dans le soft

    public static void main(String[] args) {


        //Creation de la fenetre du jeu
        fenetre = new JFrame("Flappybird");
        fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //ferme le jeu en clic croix
        fenetre.setSize(295,425); // Taille
        fenetre.setLocationRelativeTo(null); // Centre la fenetre à l'écran
        fenetre.setResizable(false); // Interdiction de redimentionner
        fenetre.setAlwaysOnTop(true); // Place le jeu devant toutes les autres fenetres

        //Instanciation de l'objet Scene
        scene = new Scene();

        fenetre.setContentPane(scene); // Associe la scene à la fenetre du soft
        fenetre.setVisible(true); // Rend la fenetre visible

    }
}
