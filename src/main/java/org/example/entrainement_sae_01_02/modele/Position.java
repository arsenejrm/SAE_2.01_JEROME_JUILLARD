package org.example.entrainement_sae_01_02.modele;

/**
 * La classe Position permet de stocker les coordonnées des objets graphiques comme les temples ou l'apprenti
 */
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

    /**
     * Cette méthode permet de changer la position d'une case de la grille
     * avec comme paramètre une Position vers laquelle se diriger
     *
     * @param parPosition La position qu'on cherche à atteindre
     */
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

    public int distanceTo(Position parPosition) {
        int resultatX;
        int resultatY;
        if (this.abscisse >= parPosition.abscisse) {
            resultatX = this.abscisse - parPosition.abscisse;
        }
        else {
            resultatX = parPosition.abscisse - this.abscisse;
        }
        if (this.ordonnee >= parPosition.ordonnee) {
            resultatY = this.ordonnee- parPosition.ordonnee;
        }
        else {
            resultatY = parPosition.ordonnee- this.ordonnee;
        }
        return resultatX + resultatY;
    }

    public boolean equals(Position parPosition) {
        return (this.ordonnee == parPosition.ordonnee && this.abscisse == parPosition.abscisse);
    }

    public String toString() {
        return ("(" + abscisse + ", " + ordonnee + "), Nombre de pas = " + nombreDePas);
    }
}
