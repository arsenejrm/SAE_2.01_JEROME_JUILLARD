package modele;

import javafx.scene.image.Image;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Position{
    private int nombreDePas = 0;
    private int abscisse;
    private int ordonnee;

    public Position (int abscisse, int ordonnee) {
        this.abscisse = abscisse;
        this.ordonnee = ordonnee;
    }

    public int getAbscisse() { return abscisse;}
    public int getOrdonnee() { return ordonnee;}
    public int getNombreDePas() {return nombreDePas;}

    public void setOrdonnee(int ordonnee) {
        this.ordonnee = ordonnee;
    }

    public void setAbscisse(int abscisse) {
        this.abscisse = abscisse;
    }
    public void setNombreDePas(int nombreDePas) {
        this.nombreDePas = nombreDePas;
    }

    public void deplacementUneCase (Position parPosition) {
        nombreDePas++;
        if (this.abscisse > parPosition.abscisse) {
            this.abscisse -= 1;
            return;
        }
        if (this.ordonnee > parPosition.ordonnee) {
            this.ordonnee -= 1;
            return;
        }
        if (this.abscisse < parPosition.abscisse) {
            this.abscisse += 1;
            return;
        }
        if (this.ordonnee < parPosition.ordonnee) {
            this.ordonnee += 1;
            return;
        }
    }

    public boolean equals(Position parPosition) {
        return (this.ordonnee == parPosition.ordonnee && this.abscisse == parPosition.abscisse);
    }

    public String toString() {
        return ("(" + abscisse + ", " + ordonnee + "), Nombre de pas = " + nombreDePas);
    }
}
