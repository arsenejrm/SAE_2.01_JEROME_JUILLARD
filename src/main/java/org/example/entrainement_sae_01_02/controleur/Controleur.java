package org.example.entrainement_sae_01_02.controleur;

import javafx.event.*;
import javafx.scene.control.*;
import org.example.entrainement_sae_01_02.modele.*;
import org.example.entrainement_sae_01_02.vue.*;

/**
 * La classe Controleur se charge de faire le lien entre
 * les vues et les modèles de l'application, c'est-à-dire
 * mettre en action les commandes graphiques d'une vue
 * qui ont un impact sur une vue autre qu'elle même.
 */
public class Controleur implements EventHandler {           // L'implémentation d'EventHandler prépare la classe à traiter des évènements
    @Override
    public void handle(Event event) {           // La méthode handle s'exécute à chaque action (clic, touche du clavier, utilisation d'une commande, etc.)
        if (event.getSource() instanceof ChoiceBox) {           // Si la source de l'évènement est un ChoiceBox (Ici, si on change le scénario)
            try {
                Scenario scenarioSelection = new Scenario(HBoxRoot.getStats().getChoixScenario());          // on récupère le scénario choisi en passant par HBoxRoot,
                if (HBoxRoot.getGrille().getScenario() == null) {           // si il n'y avait pas de scénario chargé avant
                    HBoxRoot.getGrille().scenarioOn(scenarioSelection);         // on charge le nouveau scénario sans se soucier de ce qu'il y avait avant
                }
                else {
                    HBoxRoot.getGrille().changeScenario(scenarioSelection);         // sinon on efface les temples précédents et la position de l'apprenti avant de charger le nouveau scénario
                }
            }
            catch (Exception e) {
                System.out.println("Erreur Scenario: " + e.getMessage());
            }
        }
    }
}
