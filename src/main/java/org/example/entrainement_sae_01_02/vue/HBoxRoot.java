package org.example.entrainement_sae_01_02.vue;

import javafx.scene.layout.HBox;
import org.example.entrainement_sae_01_02.controleur.Controleur;

/**
 * La classe HBoxRoot est l'objet graphique principal de l'application.
 * Elle se charge de créer la vue de la grille à gauche,
 * la vue contenant les zones de commandes et stats à droite et
 * le contrôleur qui se charge de faire le lein entre les deux dans le cadre de l'architecture MVC.
 */
public class HBoxRoot extends HBox {

    public static GrilleOrdonnateur grille;
    public static VBoxStats stats;
    public static Controleur controleur;

    /**
     * Le constructeur crée les deux vues et le contrôleur et affiche les deux vues.
     */
    public HBoxRoot() {
        controleur = new Controleur();
        grille = new GrilleOrdonnateur();
        stats = new VBoxStats();
        this.getChildren().addAll(grille, stats);
    }

    public static GrilleOrdonnateur getGrille() {
        return grille;
    }

    public static VBoxStats getStats() {
        return stats;
    }

    public static Controleur getControleur() {
        return controleur;
    }
}
