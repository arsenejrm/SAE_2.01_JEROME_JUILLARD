module org.example.entrainement_sae_01_02 {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires java.desktop;

    opens org.example.entrainement_sae_01_02 to javafx.fxml;
    exports org.example.entrainement_sae_01_02.vue;
    exports org.example.entrainement_sae_01_02.controleur;

}