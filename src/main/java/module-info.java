module org.example.entrainement_sae_01_02 {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;

    opens org.example.entrainement_sae_01_02 to javafx.fxml;
    exports vue;

}