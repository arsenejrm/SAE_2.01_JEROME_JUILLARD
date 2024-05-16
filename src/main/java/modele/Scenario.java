package modele;

import javafx.scene.canvas.GraphicsContext;
import vue.ConstantesApplication;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Scenario implements ConstantesApplication {
    ArrayList <Temple> templesDuScenario;
    public Scenario(String fichierScenario) throws FileNotFoundException {
        templesDuScenario = new ArrayList <>();
        Scanner scanner = new Scanner(new File("scenario" + File.separator + fichierScenario));
        Temple temple;
        while(scanner.hasNext()){
            int posX = scanner.nextInt();
            int posY = scanner.nextInt();
            int couleur = scanner.nextInt();
            int cristal = scanner.nextInt();
            temple = new Temple(posX, posY, couleur, cristal);
            templesDuScenario.add(temple);
        }
        scanner.close();
    }

    public ArrayList <Temple> getTemplesDuScenario() { return templesDuScenario; }

    public void actualiserTemple(GraphicsContext gc, int noTemple) {
        Temple templeActu = templesDuScenario.get(noTemple);
        gc.drawImage(templeActu.getImageTemple(), (NB_COLONNES / 2 + templeActu.getPosition().getAbscisse()) * CARRE, (NB_LIGNES / 2 + templeActu.getPosition().getOrdonnee()) * CARRE, CARRE, CARRE);
        gc.drawImage(templeActu.getImageCristal(), (NB_COLONNES / 2 + templeActu.getPosition().getAbscisse()) * CARRE, (NB_LIGNES / 2 + templeActu.getPosition().getOrdonnee()) * CARRE, CARRE, CARRE);
    }

    public void actualiserTemples(GraphicsContext gc) {
        for (Temple temple : templesDuScenario) {
            System.out.println(temple.getPosition().toString());
            gc.drawImage(temple.getImageTemple(), (NB_COLONNES / 2 + temple.getPosition().getAbscisse()) * CARRE, (NB_LIGNES / 2 + temple.getPosition().getOrdonnee()) * CARRE, CARRE, CARRE);
            gc.drawImage(temple.getImageCristal(), (NB_COLONNES / 2 + temple.getPosition().getAbscisse()) * CARRE, (NB_LIGNES / 2 + temple.getPosition().getOrdonnee()) * CARRE, CARRE, CARRE);
        }
    }
}
