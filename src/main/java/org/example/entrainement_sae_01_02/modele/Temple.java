package org.example.entrainement_sae_01_02.modele;

import javafx.scene.image.Image;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Temple {
    /*
    Contient les informations d'un temple et de son cristal,
    c'est à dire sa position et celle de son cristal, sa couleur et celle de son cristal, son image et celle de son cristal.
    Le cristal associé au temple est celui qui est de la même couleur, cela implique
     */
    private Position positionTemple;            // Contient la position du temple sur la grille, et non sur le Canva
    private Position positionCristal;           // Contient la position du cristal de même couleur que le temple, sur la grille et non sur le Canva
    private final int couleurTemple;            // Contient la couleur du temple sous forme de int. Elle est comprise entre 1 et 9.
    private int couleurContenu;                 // Contient la couleur du cristal actuellement présent sur le temple, et non le cristal de même couleur. Elle vaut entre 1 et 9 si il y a quelque chose et 0 si c'est vide (principe du 0 à programmer)
    private final Image ImageTemple;            // Contient l'image du temple présente dans le dossier "images", à la racine. Elle est importée à la construction d'un temple et est définitive.
    private Image ImageCristal;                 // Contient l'image du cristal de même couleur que le temple et non l'image du cristal actuellement contenu. Importée à la construction du temple.

    public Temple(int abscisse, int ordonnee, int couleurTemple, int couleurContenu) throws FileNotFoundException {                             // Constructeur de l'objet Temple, a besoin de coordonnées de grille (abscisse, ordonnee), de la couleur du Temple et de son contenu
        this.couleurTemple = couleurTemple;
        this.couleurContenu = couleurContenu;
        this.positionTemple = new Position(abscisse, ordonnee);
        this.ImageTemple = new Image(new FileInputStream("images" + File.separator + "tiny-temple-" + this.couleurTemple + ".png"));      // On récupère l'image du temple. Si celui-ci est de couleur 4, le fichier contenu dans images s'appelle "tiny-temple-4.png"
        this.ImageCristal = new Image(new FileInputStream("images" + File.separator + "tiny-cristal-" + this.couleurTemple + ".png"));    // On récupère l'image du cristal de même couleur. Si celui-ci est de couleur 7, le fichier contenu dans images s'appelle "tiny-cristal-7.png"
    }

    public Position getPositionTemple() { return positionTemple; }

    public int getAbscisseTemple() { return positionTemple.getAbscisse(); }         // Donne l'abscisse de la position du temple sur la grille, on peut aussi l'avoir avec la position emme-même mais c'est pour réduire les longues lignes et améliorer la compréhension.

    public int getOrdonneeTemple() { return positionTemple.getOrdonnee(); }

    public Position getPositionCristal() { return positionCristal; }

    public int getAbscisseCristal() { return positionCristal.getAbscisse(); }

    public int getOrdonneeCristal() { return positionCristal.getOrdonnee(); }

    public int getCouleurTemple() { return couleurTemple; }

    public int getCouleurContenu() { return couleurContenu; }

    public Image getImageTemple() { return ImageTemple; }

    public Image getImageCristal() { return ImageCristal; }

    public void setCouleurContenu(int couleurContenu) {         // Permet d'actualiser le contenu d'un temple quand les cristaux se déplacent avec l'apprenti ordonnateur (non fonctionnel pour l'instant)
        this.couleurContenu = couleurContenu;
    }

    public void setPositionCristal(Position parPos) {           // Sert à initialiser les scénarios (fonctionnel) et à bouger les cristaux (non fonctionnel)
        positionCristal = parPos;
    }

    public String toString() {return "Temple de couleur " + couleurTemple + " : Position = " + positionTemple + ", Contenu = " + couleurContenu + "\nCristal de couleur " + couleurTemple + " : Position = " + positionCristal;}
}
