package org.example.entrainement_sae_01_02.modele;

import java.util.ArrayList;
import java.util.Set;
import java.util.TreeMap;

public class Algorithmes {
    Scenario scenario;
    ArrayList<Integer> triTemples;
    public Position position1=new Position(0,0);
    public Position position2=new Position(0,0);
    int etape = 0;
    public Algorithmes(){
        position1 = new Position(0,0);
        position2 = new Position(0,0);
        etape = 0;
    }
    public void majPosition(Scenario scenario){
        TreeMap<Integer,Temple> temple_restant = new TreeMap<>();
        for (Temple temple: scenario.getTemples()){
            if(temple.getCouleurTemple()!=temple.getCouleurContenu()){
                temple_restant.put(temple.getCouleurTemple(),temple);
            }
        }
        etape = 0;
        int couleur_temple = temple_restant.firstKey();
        position1 = new Position(temple_restant.get(couleur_temple).getPositionTemple().getAbscisse(),temple_restant.get(couleur_temple).getPositionTemple().getOrdonnee());
        Set<Integer> couleur = temple_restant.keySet();
        for (int c:couleur){
            if (temple_restant.get(c).getCouleurContenu()==couleur_temple){
                System.out.println("test");
                position2 = new Position(temple_restant.get(c).getPositionTemple().getAbscisse(),temple_restant.get(c).getPositionTemple().getOrdonnee());
            }

        }

    }
    public Position getPosition1(){return position1;}
    public Position getPosition2(){return position2;}
    /**
     * Cette methode permet de passer à l'etape suivante dans les deplacements
     */
    public void etape_suivante(){
        etape+=1;
    }
    public int getEtape(){return etape;}
    public Algorithmes(Scenario scenario) {
        this.scenario = scenario;
        triTemples = new ArrayList<>();
        triTemples.add(0);
        for (Temple temple : scenario.getTemples()) {
            triTemples.add(temple.getCouleurContenu());
        }
    }

    public void actualiserTemples(Scenario scenario) {
        triTemples.clear();
        for (Temple temple : scenario.getTemples()) {
            triTemples.add(temple.getCouleurContenu());
        }
    }

    public ArrayList<Integer> getTriTemples() {
        return triTemples;
    }

    public void etapeTriSelection(int firstUnsorted) {
        int min = firstUnsorted;
        for (int i = min; i < triTemples.size(); i++) {
            if (triTemples.get(min) > triTemples.get(i)) {
                min = i;
            }
        }
        int cristalMin = triTemples.get(min);
        triTemples.set(min, triTemples.get(firstUnsorted));
        triTemples.set(firstUnsorted, cristalMin);
    }
}
