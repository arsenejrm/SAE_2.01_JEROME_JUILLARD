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
            if (!HBoxRoot.getGrille().isOnActionTimer()) {
                try {
                    Scenario scenarioSelection = new Scenario(HBoxRoot.getStats().getChoixScenario());          // on récupère le scénario choisi en passant par HBoxRoot,
                    if (HBoxRoot.getGrille().getScenario() == null) {           // si il n'y avait pas de scénario chargé avant
                        HBoxRoot.getGrille().scenarioOn(scenarioSelection);         // on charge le nouveau scénario sans se soucier de ce qu'il y avait avant
                    } else {
                        HBoxRoot.getGrille().changeScenario(scenarioSelection);         // sinon on efface les temples précédents et la position de l'apprenti avant de charger le nouveau scénario
                    }
                } catch (Exception e) {
                    System.out.println("Erreur Scenario: " + e.getMessage());
                }
            }
            else {
                HBoxRoot.getStats().setChoixScenario("");
                System.out.println("Le scénario ne peut pas être changé tant qu'un déplacement est en cours");
            }
        }
        if (HBoxRoot.getGrille().getScenario() != null && !HBoxRoot.getGrille().isOnActionTimer()) {
            if (event.getSource() instanceof Button && ((Button) event.getSource()).getText().equals("Prendre")) {
                if (HBoxRoot.getGrille().getApprenti().getContenu() == 0) {
                    for (Temple temple : HBoxRoot.getGrille().getScenario().getTemples()) {
                        if (HBoxRoot.getGrille().getApprenti().getPosition().equals(temple.getPositionTemple())) {
                            HBoxRoot.getGrille().getScenario().actualiserCristal(HBoxRoot.getGrille().getGraphicsContext2D(), temple.getCouleurContenu(), temple.getPositionGraphiqueTemple());
                            HBoxRoot.getGrille().getApprenti().setContenu(temple.getCouleurContenu());
                            temple.setCouleurContenu(0);
                            break;
                        }
                    }
                }
                else {
                    System.out.println("L'apprenti possède déjà un cristal !");
                }
            }
            if (event.getSource() instanceof Button && ((Button) event.getSource()).getText().equals("Echanger")) {
                if (HBoxRoot.getGrille().getApprenti().getContenu() != 0) {
                    boolean cristalTemple = false;
                    for (Temple temple : HBoxRoot.getGrille().getScenario().getTemples()) {
                        if (HBoxRoot.getGrille().getApprenti().getPosition().equals(temple.getPositionTemple())) {
                            if (temple.getCouleurContenu() == 0) {
                                cristalTemple = true;
                                break;
                            }
                            int contenuApprenti = HBoxRoot.getGrille().getApprenti().getContenu();
                            HBoxRoot.getGrille().getScenario().actualiserCristal(HBoxRoot.getGrille().getGraphicsContext2D(), temple.getCouleurContenu(), temple.getPositionGraphiqueTemple());
                            HBoxRoot.getGrille().getApprenti().setContenu(temple.getCouleurContenu());
                            temple.setCouleurContenu(contenuApprenti);
                            break;
                        }
                    }
                    if (cristalTemple) {
                        System.out.println("Le temple n'a pas de cristal !");
                    }
                }
                else {
                    System.out.println("L'apprenti n'a pas de cristal !");
                }
            }
            if (event.getSource() instanceof Button && ((Button) event.getSource()).getText().equals("Poser")) {
                if (HBoxRoot.getGrille().getApprenti().getContenu() != 0) {
                    boolean cristalTemple = true;
                    for (Temple temple : HBoxRoot.getGrille().getScenario().getTemples()) {
                        if (HBoxRoot.getGrille().getApprenti().getPosition().equals(temple.getPositionTemple())) {
                            if (temple.getCouleurContenu() != 0) {
                                cristalTemple = false;
                                break;
                            }
                            temple.setCouleurContenu(HBoxRoot.getGrille().getApprenti().getContenu());
                            HBoxRoot.getGrille().getApprenti().setContenu(0);
                            HBoxRoot.getGrille().getApprenti().afficher(HBoxRoot.getGrille().getGraphicsContext2D());
                            break;
                        }
                    }
                    if (!cristalTemple) {
                        System.out.println("Le temple a déjà un cristal !");
                    }
                }
                else {
                    System.out.println("L'apprenti n'a pas de cristal !");
                }
                if (HBoxRoot.getGrille().getScenario().finSimulation()) {
                    HBoxRoot.getStats().finScenario();
                    HBoxRoot.getGrille().getScenario().setEstFini(true);
                    HBoxRoot.getGrille().scenarioOff();
                }
            }
        }
        else {
            System.out.println("Attender la fin du déplacement du joueur avant de toucher à son contenu !");
        }
    }
}
