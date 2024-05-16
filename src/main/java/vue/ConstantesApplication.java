package vue;

import javafx.scene.paint.Color;

public interface ConstantesApplication {
    int CARRE = 27;
    int NB_COLONNES = 32;
    int NB_LIGNES = 32;
    int LARGEUR_CANVAS = CARRE * NB_COLONNES;
    int HAUTEUR_CANVAS = CARRE * NB_LIGNES;
    int LARGEUR_OVALE = CARRE * 4/5;
    int HAUTEUR_OVALE = CARRE * 3/5;
    Color COULEUR_APPRENTI = Color.BLUE;
    Color COULEUR_GRILLE = Color.BLACK;
    Color COULEUR_FOND = Color.AZURE;
}
