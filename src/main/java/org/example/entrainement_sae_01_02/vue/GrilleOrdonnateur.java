package org.example.entrainement_sae_01_02.vue;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.effect.BlendMode;
import javafx.scene.layout.VBox;

import org.example.entrainement_sae_01_02.modele.*;

import java.util.Timer;
import java.util.TimerTask;

public class GrilleOrdonnateur extends VBox implements ConstantesApplication{           // VBox contenant l'affichage du nombre de pas de l'apprenti ordonnateur et la grille de visualisation de la simulation

    Label labelNombreDePas = new Label("Nombre de pas : 0");                         // Label contenant l'affichage du nombre de pas de l'apprenti ordonnateur
    Position positionApprenti = new Position((LARGEUR_CANVAS/CARRE)/2, (HAUTEUR_CANVAS/CARRE)/2);       // Position de l'apprenti sur la grille
    Scenario scenario;                                                          // Initialisation du scénario de la simulation
    Canvas canvasCarte = new Canvas();                                                                                   // Création du Canvas de la grille d'affichage
    GraphicsContext graphicsContext2D = canvasCarte.getGraphicsContext2D();                                              // Extraction du GraphicsContext du Canvas qui permet de dessiner sur le Canvas
    boolean onActionTimer = false;                                                                                       // Booléen qui permet de désactiver l'action de déplacement de l'apprenti ordonnateur lorsqu'il est en mouvement

    /**
     * Le constructeur crée le Canva, dessine la grille
     * avec ses ordonnées et abscisses et les ajoutent à la vue
     */
    public GrilleOrdonnateur() {
        canvasCarte.setWidth(LARGEUR_CANVAS);
        canvasCarte.setHeight(HAUTEUR_CANVAS);
        graphicsContext2D.setGlobalBlendMode(BlendMode.SRC_OVER);       // Permet de définir le type de relation entre ce qu'il y a sur le Canvas et un nouvel ajout. Ici, nous choisissons SRC_OVER pour que chaque ajout recouvre sans condition les anciennes modifications.
        graphicsContext2D.setStroke(COULEUR_GRILLE);
        for (int i = 0; i < LARGEUR_CANVAS; i += CARRE) {               // Dessin de la grille sur le Canva, à partir des dimensions d'une unité et de la taille du Canvas
            for (int j = 0; j < HAUTEUR_CANVAS; j += CARRE) {
                graphicsContext2D.strokeRect(i, j, CARRE, CARRE);
            }
        }
        this.getChildren().add(labelNombreDePas);           // Ajout du Label contenant le nombre de pas au VBox
        VBox.setMargin(labelNombreDePas, new Insets(20, 0, 0, 30));         // Ajout des marges du Label dans le VBox : 20 au dessus, 0 à droite, 0 en bas et 30 à gauche
        this.getChildren().add(canvasCarte);
        VBox.setMargin(canvasCarte, new Insets(20, 0, 0, 30));
        int numCol = -((LARGEUR_CANVAS/CARRE) - 1) / 2;         // numCol est définie sur le premier numéro de colonne. Si on a 31 colonnes ayant un numéro sur la grille, numCol sera instanciée à -15.
        graphicsContext2D.setFill(COULEUR_GRILLE);
        for (int i = CARRE; i < LARGEUR_CANVAS; i += CARRE) {
            graphicsContext2D.fillText(Integer.toString(numCol), i + CARRE / 3, CARRE/2);
            numCol++;
        }
        int numLigne = -((HAUTEUR_CANVAS/CARRE) - 1) / 2;           // numLigne est définie sur le premier numéro de ligne. Si on a 31 lignes ayant un numéro sur la grille, numLigne sera instanciée à -15.
        graphicsContext2D.setFill(COULEUR_GRILLE);
        for (int i = CARRE; i < HAUTEUR_CANVAS; i += CARRE) {
            graphicsContext2D.fillText(Integer.toString(numLigne), CARRE/3, i+CARRE/2);
            numLigne++;
        }
    }

    /**
     * Cette méthode permet d'afficher l'apprenti ordonnateur
     * et les temples du scénario choisi dans la grille
     *
     * @param scenarioChoisi Le scénario dont on charge les temples sur le Canva
     */
    public void scenarioOn(Scenario scenarioChoisi) {
        this.scenario = scenarioChoisi;         // On enregistre le scénario comme scénario actuel
        graphicsContext2D.setFill(COULEUR_APPRENTI);
        graphicsContext2D.fillOval(positionApprenti.getAbscisse()*CARRE + CARRE/8,          // On dessine l'apprenti ordonnateur
                positionApprenti.getOrdonnee()*CARRE + CARRE/4, LARGEUR_OVALE, HAUTEUR_OVALE);
        scenario.actualiserTemples(graphicsContext2D);          // On dessine les temples du scénario
        canvasCarte.setOnMouseClicked(event -> {            // On arme le traitement du clic de souris sur une case du Canva.
            int abscisse = (int) event.getX() / CARRE;      // C'est une action propre à la grille donc elle n'est pas traitée par le contrôleur.
            int ordonnee = (int) event.getY() / CARRE;
            if (abscisse != 0 && ordonnee != 0) {
                Position positionCliquee = new Position(abscisse, ordonnee);
                System.out.println(positionCliquee);
                if (!onActionTimer) {           // S'il n'y a pas de déplacement en cours ...
                    onActionTimer = true;           // ... on désactive la prise en compte des clics utilisateur sur le Canva
                    deplacementAvecTimer(positionApprenti, positionCliquee, labelNombreDePas);          // On lance le déplacement
                }
            }
            else {
                System.out.println("Position hors de la grille");
            }
        });
    }

    /**
     * Cette méthode permet de remplacer le scénario chargé sur la grille par
     * un autre donné en paramètre
     *
     * @param scenarioChoisi Le scénario à remplacer par celui chargé
     */
    public void changeScenario(Scenario scenarioChoisi) {
        graphicsContext2D.setFill(COULEUR_FOND);
        for (Temple temple : scenario.getTemples()) {           // On "efface" les temples présents sur la grille en dessinant un carré de la couleur du fond sur leur case
            graphicsContext2D.fillRect((NB_COLONNES / 2 + temple.getAbscisseTemple()) * CARRE + 1, (NB_COLONNES / 2 + temple.getOrdonneeTemple()) * CARRE + 1, CARRE - 2, CARRE - 2);
        }
        graphicsContext2D.fillRect(positionApprenti.getAbscisse() * CARRE + 1, positionApprenti.getOrdonnee() * CARRE + 1, CARRE - 2, CARRE - 2);           // On "efface" de la même manière l'apprenti ordonnateur
        positionApprenti = new Position((LARGEUR_CANVAS/CARRE)/2, (HAUTEUR_CANVAS/CARRE)/2);            // On remet l'apprenti ordonnateur au centre de la grille
        scenarioOn(scenarioChoisi);         // On charge le nouveau scénario sur la grille
    }

    /**
     * Cette méthode permet le déplacement de l'apprenti ordonnateur avec
     * un certain délai entre chaque déplacement d'une case à l'autre
     *
     * @param positionCourante La position actuelle de l'apprenti ordonnateur
     * @param positionCible La position de destination de l'apprenti ordonnateur
     * @param labelNombreDePas Le Label qui affiche le nombre de pas parcouru par l'apprenti
     */
    private void deplacementAvecTimer(Position positionCourante, Position positionCible, Label labelNombreDePas) {
        Timer timer = new Timer();          // On crée un Timer qui nous permet de répéter des actions plusieurs fois
        TimerTask timerTask = new TimerTask() {         // On crée une tâche à exécuter qui pourra être répété un certain nombre de fois par le Timer
            @Override
                public void run() {
                    graphicsContext2D.setFill(COULEUR_FOND);
                    graphicsContext2D.fillRect(positionCourante.getAbscisse() * CARRE + 1, positionCourante.getOrdonnee() * CARRE + 1, CARRE - 2, CARRE - 2);           // On recouvre le dessin actuel de l'apprenti
                    for (Temple temple : scenario.getTemples()) {           // On check les coordonnées des temples du scénario
                        if (positionCourante.equals(new Position(temple.getAbscisseTemple() + NB_COLONNES / 2, temple.getOrdonneeTemple() + NB_LIGNES / 2))) {
                            scenario.actualiserTemple(graphicsContext2D, temple.getCouleurTemple());            // Si un temple s'est fait recouvrir par le carré de masquage de l'apprenti, on le redessine
                        }
                        if (positionCourante.equals(new Position(temple.getAbscisseCristal() + NB_COLONNES / 2, temple.getOrdonneeCristal() + NB_LIGNES / 2))) {
                            scenario.actualiserTemple(graphicsContext2D, temple.getCouleurTemple());            // Si un cristal s'est fait recouvrir par le carré de masquage de l'apprenti, on le redessine
                        }
                    }
                    positionCourante.deplacementUneCase(positionCible);         // On actualise les coordonnées de l'apprenti
                    graphicsContext2D.setFill(COULEUR_APPRENTI);
                    graphicsContext2D.fillOval(positionCourante.getAbscisse()*CARRE + CARRE/8,          // On se sert des coordonnées actualisées pour dessiner l'apprenti à son nouvel emplacement
                        positionCourante.getOrdonnee()*CARRE + CARRE/4, LARGEUR_OVALE, HAUTEUR_OVALE);
                    if (positionCourante.equals(positionCible)) {
                        onActionTimer = false;         // Une fois que l'apprenti a atteint sa destination, on réactive la prise en compte d'un clic de l'utilisateur sur un carré et on désactive le timer
                        timer.cancel();
                    }
                    Platform.runLater(() -> {
                        labelNombreDePas.setText("Nombre de pas : " + positionCourante.getNombreDePas());
                    });
            }
        };
        timer.scheduleAtFixedRate(timerTask, 250, 250);         // On ajoute la tâche au timer pour qu'elle soit exécutée à chaque occurence
    }

    public GraphicsContext getGraphicsContext2D() {
        return graphicsContext2D;
    }

    public Scenario getScenario() {
        return scenario;
    }
}
