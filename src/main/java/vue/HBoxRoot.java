package vue;

import javafx.scene.layout.HBox;

public class HBoxRoot extends HBox {
    GrilleOrdonnateur grille = new GrilleOrdonnateur();
    public HBoxRoot() {
        this.getChildren().add(grille);
    }
}
