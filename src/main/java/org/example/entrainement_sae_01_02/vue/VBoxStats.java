package org.example.entrainement_sae_01_02.vue;

import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.File;

/**
 * La classe VBoxStats fait partie des deux vues principales de l'application.
 * Située à droite sur la fenêtre, elle se charge d'afficher les commandes et
 * les statistiques qui concernent la simulation en cours.
 */
public class VBoxStats extends VBox {
    Label labelScenario = new Label("Scénario");
    ChoiceBox<String> choiceBoxScenario = new ChoiceBox<>();
    HBox choixSimulation = new HBox();
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
        this.getChildren().addAll(choixSimulation);            // On ajoute le ChoiceBox et son Label sur la vue
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
}
