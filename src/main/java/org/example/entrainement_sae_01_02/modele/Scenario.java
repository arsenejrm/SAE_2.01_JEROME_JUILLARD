package org.example.entrainement_sae_01_02.modele;

import javafx.scene.canvas.GraphicsContext;
import org.example.entrainement_sae_01_02.vue.ConstantesApplication;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * La classe Scenario prend les données d'un fichier de scénario et en fait une liste de temples avec
 * leur coordonnées, leur couleur et leur contenu.
 */
public class Scenario implements ConstantesApplication {
    ArrayList <Temple> templesDuScenario;           // Création de la liste des temples. Elle est sous forme d'ArrayList, ce qui signifie que l'ordre des temples définit leur couleur. Si je veux le temple de couleur 4, j'écris "templesDuScenario.get(4);"
    ArrayList <Temple> templesTriage;
    ArrayList <Temple> historiqueTemple;
    boolean estFini;
    public Scenario(String fichierScenario) throws FileNotFoundException {      // Prend en paramètre le nom entier du fichier sans son extension. Si le fichier de scénario s'appelle "scenario1.txt", le paramètre fichierScenario devra avoir comme valeur "scenario1".
        templesDuScenario = new ArrayList <>();
        templesTriage = new ArrayList <>();
        historiqueTemple = new ArrayList <>();
        estFini = false;
        Scanner scanner = new Scanner(new File("scenario" + File.separator + fichierScenario));
        Temple temple;
        while(scanner.hasNext()){                                   // Pour chaque ligne d'un fichier de scénario (c'est-à-dire chaque temple), on les données suivantes séparées par un espace :
            int posX = scanner.nextInt();                           // La valeur de l'abscisse du temple sur la grille (entre 1 et la valeur de NB_COLONNES)
            int posY = scanner.nextInt();                           // La valeur de l'ordonnée du temple sur la grille (entre 1 et la valeur de NB_LIGNES)
            int couleur = scanner.nextInt();                        // Le numéro représentant la couleur du temple (entre 1 et 9)
            int cristal = scanner.nextInt();                        // Le numéro représentant la couleur du cristal contenu dans le temple (entre 1 et 9)
            temple = new Temple(posX, posY, couleur, cristal);
            templesTriage.add(temple);
        }
        for (int i = 1; i <= templesTriage.size(); i++) {
            for (int j = 0; j < templesTriage.size(); j++) {
                if (templesTriage.get(j).getCouleurTemple() == i) {
                    templesDuScenario.add(templesTriage.get(j));
                    break;
                }
            }
        }
        for (Temple templeCristal : templesDuScenario) {            // Pour chaque temple, on actualise la position de son cristal de couleur sur la grille par rapport au contenu de chaque temple.
            templesDuScenario.get(templeCristal.getCouleurContenu() - 1).setPositionCristal(templeCristal.getPositionTemple());
        }
        scanner.close();
    }

    public ArrayList <Temple> getTemples() { return templesDuScenario; }

    /**
     * Cette méthode permet de redessiner l'image d'un temple et de son contenu sur le Canva
     *
     * @param gc le GraphicsContext du Canva qui permet de dessiner le temple sur la grille
     * @param noTemple Le numéro du temple qu'on veut actualiser
     */
    public void actualiserTemple(GraphicsContext gc, int noTemple) {
        Temple templeActu = templesDuScenario.get(noTemple - 1);
        gc.drawImage(templeActu.getImageTemple(), templeActu.getPositionGraphiqueTemple().getAbscisse(), templeActu.getPositionGraphiqueTemple().getOrdonnee(), CARRE, CARRE);
        if (templeActu.getCouleurContenu() != 0) {
            gc.drawImage(templesDuScenario.get(templeActu.getCouleurContenu() - 1).getImageCristal(), templeActu.getPositionGraphiqueTemple().getAbscisse(), templeActu.getPositionGraphiqueTemple().getOrdonnee(), CARRE, CARRE);
        }
    }

    public void actualiserCristal(GraphicsContext gc, int noCristal, Position positionGraphiqueCristal) {
        Temple templeActu = templesDuScenario.get(noCristal - 1);
        gc.drawImage(templeActu.getImageCristal(), positionGraphiqueCristal.getAbscisse(), positionGraphiqueCristal.getOrdonnee(), CARRE, CARRE);
    }

    /**
     * Cette méthode permet de dessiner l'intégralité des temples sur la grille
     *
     * @param gc Le GraphicsContext du Canva qui permet de dessiner les temples sur la grille
     */
    public void actualiserTemples(GraphicsContext gc) {
        for (Temple temple : templesDuScenario) {
            this.actualiserTemple(gc, temple.getCouleurTemple());
        }
    }

    public void ajoutHistorique(Temple temple) {
        historiqueTemple.add(temple);
    }

    public boolean finSimulation() {
        boolean finSimulation = true;
        for (Temple temple : templesDuScenario) {
            if (temple.getCouleurContenu() != temple.getCouleurTemple() || estFini) {
                finSimulation = false;
                break;
            }
        }
        return finSimulation;
    }

    public ArrayList <Temple> getHistoriqueTemple() {
        return historiqueTemple;
    }

    public boolean getEstFini() {
        return estFini;
    }

    public void setEstFini(boolean estFini) {
        this.estFini = estFini;
    }
}
