package org.example.entrainement_sae_01_02.vue;

import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import java.io.File;
import java.io.FileNotFoundException;

/**
 * La classe Application est la classe principale du programme,
 * c'est elle qu'on démarre pour lancer l'exécution
 */
public class Application extends javafx.application.Application {
    public void start(Stage stage) {
        HBox root = new HBoxRoot();
        Scene scene = new Scene(root, 1800, 950);
        File css = new File("css"+ File.separator+"Canvas.css");
        scene.getStylesheets().add(css.toURI().toString());
        stage.setScene(scene);
        stage.show();
    }

    /**
     * C'est cette méthode qu'on lance pour exécuter le programme
     *
     * @param args
     */
    public static void main(String[] args) {
        javafx.application.Application.launch(args);}
}
