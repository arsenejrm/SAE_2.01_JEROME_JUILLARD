package vue;

import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.File;

public class Application extends javafx.application.Application {
    public void start(Stage stage) {
        HBox root = new HBoxRoot();
        Scene scene = new Scene(root, 1800, 950);
        File css = new File("css"+ File.separator+"Canvas.css");
        scene.getStylesheets().add(css.toURI().toString());
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        javafx.application.Application.launch(args);}
}
