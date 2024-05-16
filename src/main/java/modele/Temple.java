package modele;

import javafx.scene.image.Image;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import static vue.ConstantesApplication.*;

public class Temple {
    private Position position;
    private int couleurTemple;
    private int couleurCristal;
    private Image ImageTemple;
    private Image ImageCristal;

    public Temple(int abscisse, int ordonnee, int couleurTemple, int couleurCristal) throws FileNotFoundException {
        this.couleurTemple = couleurTemple;
        this.couleurCristal = couleurCristal;
        this.position = new Position(abscisse, ordonnee);
        this.ImageTemple = new Image(new FileInputStream("images" + File.separator + "temple-" + this.couleurTemple + ".png"));
        this.ImageCristal = new Image(new FileInputStream("images" + File.separator + "cristal-" + this.couleurCristal + ".png"));
    }

    public Position getPosition() { return position; }

    public int getCouleurTemple() { return couleurTemple; }

    public int getCouleurCristal() { return couleurCristal; }

    public Image getImageTemple() { return ImageTemple; }

    public Image getImageCristal() { return ImageCristal; }

    public void setPosition(Position positionTemple) { this.position = positionTemple; }

    public void setCouleurTemple(int couleurTemple) {
        try {
            this.ImageTemple = new Image(new FileInputStream("images" + File.separator + "temple-" + couleurTemple + ".png"));
            this.couleurTemple = couleurTemple;
        }
        catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    public void setCouleurCristal(int couleurCristal) {
        try {
            this.ImageCristal = new Image(new FileInputStream("images" + File.separator + "cristal-" + couleurCristal + ".png"));
            this.couleurCristal = couleurCristal;
        }
        catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    public String toString() {return "Temple avec la couleur " + couleurTemple + " : Position = " + position + ", Contenu = " + "\nCristal : Couleur = " + couleurCristal;}
}
