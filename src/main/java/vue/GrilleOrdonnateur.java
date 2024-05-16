package vue;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.effect.BlendMode;
import javafx.scene.layout.VBox;

import modele.*;

import java.io.FileNotFoundException;
import java.util.Timer;
import java.util.TimerTask;

public class GrilleOrdonnateur extends VBox implements ConstantesApplication{

    Label labelNombreDePas = new Label("Nombre de pas : 0");
    Position positionApprenti = new Position((LARGEUR_CANVAS/CARRE)/2, (HAUTEUR_CANVAS/CARRE)/2);
    Scenario scenario1 = new Scenario("scenario5.txt");
    Canvas canvasCarte = new Canvas();
    GraphicsContext graphicsContext2D = canvasCarte.getGraphicsContext2D();

    public GrilleOrdonnateur() throws FileNotFoundException {
        canvasCarte.setWidth(LARGEUR_CANVAS);
        canvasCarte.setHeight(HAUTEUR_CANVAS);
        graphicsContext2D.setGlobalBlendMode(BlendMode.SRC_OVER);
        graphicsContext2D.setStroke(COULEUR_GRILLE);
        for (int i = 0; i < LARGEUR_CANVAS; i += CARRE) {
            for (int j = 0; j < HAUTEUR_CANVAS; j += CARRE) {
                graphicsContext2D.strokeRect(i, j, CARRE, CARRE);
            }
        }
        this.getChildren().add(labelNombreDePas);
        VBox.setMargin(labelNombreDePas, new Insets(20, 0, 0, 30));
        this.getChildren().add(canvasCarte);
        VBox.setMargin(canvasCarte, new Insets(20, 20, 0, 30));
        int numCol = -((LARGEUR_CANVAS/CARRE) - 1) / 2;
        graphicsContext2D.setFill(COULEUR_GRILLE);
        for (int i = CARRE; i < LARGEUR_CANVAS; i += CARRE) {
            graphicsContext2D.fillText(Integer.toString(numCol), i + CARRE / 3, CARRE/2);
            numCol++;
        }
        int numLigne = -((HAUTEUR_CANVAS/CARRE) - 1) / 2;
        graphicsContext2D.setFill(COULEUR_GRILLE);
        for (int i = CARRE; i < HAUTEUR_CANVAS; i += CARRE) {
            graphicsContext2D.fillText(Integer.toString(numLigne), CARRE/3, i+CARRE/2);
            numLigne++;
        }
        scenario1.actualiserTemples(graphicsContext2D);
        graphicsContext2D.setFill(COULEUR_APPRENTI);
        graphicsContext2D.fillOval(positionApprenti.getAbscisse()*CARRE + CARRE/8,
                positionApprenti.getOrdonnee()*CARRE + CARRE/4, LARGEUR_OVALE, HAUTEUR_OVALE);
        canvasCarte.setOnMouseClicked(event -> {
            int abscisse = (int) event.getX() / CARRE;
            int ordonnee = (int) event.getY() / CARRE;
            if (abscisse != 0 && ordonnee != 0) {
                Position positionCliquee = new Position(abscisse, ordonnee);
                System.out.println(positionCliquee);
                deplacementAvecTimer(graphicsContext2D, positionApprenti, positionCliquee, labelNombreDePas);
            }
            else {
                System.out.println("Position hors de la grille");
            }
        });
    }

    private void deplacementAvecTimer(GraphicsContext graphicsContext2D, Position positionCourante, Position positionCible, Label labelNombreDePas) {
        Timer timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
                public void run() {
                    graphicsContext2D.setFill(COULEUR_FOND);
                    graphicsContext2D.fillRect(positionCourante.getAbscisse() * CARRE + 1, positionCourante.getOrdonnee() * CARRE + 1, CARRE - 2, CARRE - 2);
                    scenario1.actualiserTemples(graphicsContext2D);
                    positionCourante.deplacementUneCase(positionCible);
                    graphicsContext2D.setFill(COULEUR_APPRENTI);
                    graphicsContext2D.fillOval(positionCourante.getAbscisse()*CARRE + CARRE/8,
                        positionCourante.getOrdonnee()*CARRE + CARRE/4, LARGEUR_OVALE, HAUTEUR_OVALE);
                    if (positionCourante.equals(positionCible)) {
                        timer.cancel();
                    }
                    Platform.runLater(() -> {
                        labelNombreDePas.setText("Nombre de pas : " + positionCourante.getNombreDePas());
                    });
            }
        };
        timer.scheduleAtFixedRate(timerTask, 1000, 500);
    }
    public GraphicsContext getGraphicsContext2D() {
        return graphicsContext2D;
    }
}
