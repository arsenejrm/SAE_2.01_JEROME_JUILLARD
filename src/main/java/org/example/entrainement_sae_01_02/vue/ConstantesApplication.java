package org.example.entrainement_sae_01_02.vue;

import javafx.scene.paint.Color;

public interface ConstantesApplication {
    int CARRE = 27;                             // Dimensions en pixels du Canva d'une unité de la grille
    int NB_COLONNES = 32;                       // Nombre de colonnes dont dispose la grille, en comptant la colonne où sont affichés les chiffres de l'abscisse
    int NB_LIGNES = 32;                         // Nombre de lignes dont dispose la grille, en comptant la ligne où sont affichés les chiffres de l'ordonnée
    int LARGEUR_CANVAS = CARRE * NB_COLONNES;   // Largeur du Canva calculé à partir des dipensions d'une unité de grille et du nombres de colonnes. Aide à la compréhension.
    int HAUTEUR_CANVAS = CARRE * NB_LIGNES;     // Largeur du Canva calculé à partir des dipensions d'une unité de grille et du nombres de lignes. Aide à la compréhension.
    int LARGEUR_OVALE = CARRE * 4/5;            // Largeur de l'ovale représentant l'apprenti ordonnateur (provisoire, à enlever dès que l'apprenti a sa propre image)
    int HAUTEUR_OVALE = CARRE * 3/5;            // Hauteur de l'ovale représentant l'apprenti ordonnateur (provisoire, à enlever dès que l'apprenti a sa propre image)
    Color COULEUR_APPRENTI = Color.BLUE;        // Couleur de l'ovale représentant l'apprenti (provisoire à enlever dès que l'apprenti a sa propre image)
    Color COULEUR_GRILLE = Color.BLACK;         // Couleur des bordures de la grille
    Color COULEUR_FOND = Color.AZURE;           // Couleur de l'arrière plan du Canva
}
