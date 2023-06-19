package fr.isika.cda25.projet1.vue;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class VueConnexion extends GridPane {

    protected PasswordField champMotDePasse;
    protected Stage stageAnnuaire;
    protected Label labelErreur;

    public VueConnexion(Stage stage, Stage stageConnexion) {
        this.stageAnnuaire = stage;
        champMotDePasse = new PasswordField();
        labelErreur = new Label();
        labelErreur.setTextFill(Color.RED);

        Label labelConnexion = new Label("Connexion au compte admin");

        Button buttonConnexion = new Button("Connexion");

        buttonConnexion.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String password = champMotDePasse.getText();
                if (password.equals("cda25admin")) {
                    VueAdmin vueAdmin = new VueAdmin(stageAnnuaire);
                    Scene sceneAdmin = new Scene(vueAdmin);
                    stageAnnuaire.setScene(sceneAdmin);

                    stageAnnuaire.setMaximized(true);

                    Screen screen = Screen.getPrimary();
                    double screenWidth = screen.getBounds().getWidth();
                    double screenHeight = screen.getBounds().getHeight();
                    stageAnnuaire.setWidth(screenWidth);
                    stageAnnuaire.setHeight(screenHeight);

                    stageConnexion.close();
                } else {
                    labelErreur.setText("Mot de passe incorrect");
                }
            }
        });

        this.setAlignment(Pos.CENTER);
        this.add(labelConnexion, 0, 0);
        this.add(champMotDePasse, 0, 1);
        this.add(buttonConnexion, 0, 2);
        this.add(labelErreur, 0, 3);
    }
}
