package vue;

import javafx.scene.layout.HBox;

import java.io.FileNotFoundException;


public class HBoxRoot extends HBox {
    GrilleOrdonnateur grille = new GrilleOrdonnateur();
    public HBoxRoot() throws FileNotFoundException {
        this.getChildren().add(grille);
    }
}
