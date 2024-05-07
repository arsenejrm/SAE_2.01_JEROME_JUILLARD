package vue;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.File;

public class ApprentiOrdonnateurApplication extends Application {
    public void start(Stage stage) {
        HBox root = new HBoxRoot();
        Scene scene = new Scene(root, 1800, 950);
        File css = new File("css"+ File.separator+"Canvas.css");
        scene.getStylesheets().add(css.toURI().toString());
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {Application.launch(args);}
}
