package org.example.entrainement_sae_01_02.vue;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.example.entrainement_sae_01_02.modele.Temple;

import java.io.File;
import java.util.ArrayList;

/**
 * La classe VBoxStats fait partie des deux vues principales de l'application.
 * Située à droite sur la fenêtre, elle se charge d'afficher les commandes et
 * les statistiques qui concernent la simulation en cours.
 */
public class VBoxStats extends VBox {
    Label labelScenario = new Label("Scénario");
    ChoiceBox<String> choiceBoxScenario = new ChoiceBox<>();
    HBox choixSimulation = new HBox();
    HBox actionSimulation = new HBox();
    Button prendreCristal = new Button("Prendre");
    Button echangerCristal = new Button("Echanger");
    Button poserCristal = new Button("Poser");
    VBox resultatSimulation = new VBox();
    Label labelResultat;
    ScrollPane scrollHistorique;
    VBox stockHistorique;
    public VBoxStats() {
        File [] fichiersScenario = new File("scenario").listFiles();            // On fait la liste des fichiers qui se trouvent dans le dossier "scenario" du projet
        assert fichiersScenario != null;            // On crée une erreur si le dossier est vide
        for (File scenario : fichiersScenario) {            // On vérifie pour chaque fichier si c'est bien un fichier texte et si c'est le cas, on l'ajoute à la liste des scénarios du ChoiceBox
            String fileType = scenario.getName().substring(scenario.getName().lastIndexOf(".") + 1);
            if (fileType.equals("txt")) {
                choiceBoxScenario.getItems().add(scenario.getName());
            }
        }
        choiceBoxScenario.setOnAction(HBoxRoot.getControleur());            // On active la prise en compte des évènements de ce bouton par le contrôleur
        choixSimulation.getChildren().addAll(labelScenario, choiceBoxScenario);
        VBox.setMargin(choixSimulation, new Insets(50, 0, 0, 20));
        prendreCristal.setOnAction(HBoxRoot.getControleur());
        echangerCristal.setOnAction(HBoxRoot.getControleur());
        poserCristal.setOnAction(HBoxRoot.getControleur());
        actionSimulation.getChildren().addAll(prendreCristal, echangerCristal, poserCristal);
        VBox.setMargin(actionSimulation, new Insets(100, 0, 0, 50));
        this.getChildren().addAll(choixSimulation, actionSimulation, resultatSimulation);            // On ajoute le ChoiceBox et son Label sur la vue
    }

    /**
     * Cette méthode permet de récupérer le sénario choisi par l'utilisateur
     * grâce au ChoiceBox choiceBoxScenario.
     *
     * @return String, le nom du ficher du scénario choisi
     */
    public String getChoixScenario() {
        System.out.println(choiceBoxScenario.getValue());
        return choiceBoxScenario.getValue();
    }

    public void setChoixScenario(String choixValeur) {
        this.choiceBoxScenario.setValue(choixValeur);
    }

    public void finScenario() {
        labelResultat = new Label("Résultat de la simulation");
        scrollHistorique = new ScrollPane();
        ArrayList<Temple> historiqueTemples = HBoxRoot.getGrille().getScenario().getHistoriqueTemple();
        stockHistorique = new VBox();
        Label templeHistorique;
        for (int i = 1; i < historiqueTemples.size(); i++) {
            templeHistorique = new Label(i + " : Temple de couleur " + historiqueTemples.get(i).getCouleurTemple());
            stockHistorique.getChildren().add(templeHistorique);
        }
        scrollHistorique.setContent(stockHistorique);
        resultatSimulation.getChildren().addAll(labelResultat, scrollHistorique);
    }

    public void resetResultat() {
        resultatSimulation.getChildren().clear();
    }
}
