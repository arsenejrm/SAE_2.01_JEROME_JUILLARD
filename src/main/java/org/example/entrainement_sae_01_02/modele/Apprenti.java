package org.example.entrainement_sae_01_02.modele;

import javafx.scene.canvas.GraphicsContext;

import static org.example.entrainement_sae_01_02.vue.ConstantesApplication.*;

public class Apprenti {

    Position positionApprenti;
    int contenuApprenti;

    public Apprenti() {
        positionApprenti = new Position(0, 0);
        contenuApprenti = 0;
    }

    public Position getPosition() {
        return positionApprenti;
    }

    public Position getPositionGraphique() {
        return new Position ((NB_LIGNES / 2 + positionApprenti.getAbscisse()) * CARRE, (NB_COLONNES / 2 + positionApprenti.getOrdonnee()) * CARRE);
    }

    public int getContenu() {
        return contenuApprenti;
    }

    public void setContenu(int contenuApprenti) {
        this.contenuApprenti = contenuApprenti;
    }

    public void afficher(GraphicsContext gc) {
        gc.fillOval(this.getPositionGraphique().getAbscisse() + CARRE /8, this.getPositionGraphique().getOrdonnee() + CARRE /4, LARGEUR_OVALE, HAUTEUR_OVALE);
    }
}
