package fr.isika.cda25.projet1.vue;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

public class VueAdmin extends VueAnnuaire {

	public VueAdmin(Stage stageAnnuaire) {
		super(stageAnnuaire);
        buttonModifier.setDisable(false);
        buttonSupprimer.setDisable(false);
        buttonAdmin.setText("Déconnexion");

        buttonAdmin.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            	VueAnnuaire vueAnnuaire = new VueAnnuaire(stageAnnuaire);
                Scene sceneAnnuaire = new Scene(vueAnnuaire);
                stageAnnuaire.setScene(sceneAnnuaire);
            }
        });

        buttonSupprimer.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Alert alert = new Alert(AlertType.CONFIRMATION);
                alert.setTitle("Confirmation de suppression");
                alert.setHeaderText(null);
                alert.setContentText("Êtes-vous sûr de vouloir supprimer cet élément ?");

                ButtonType boutonOui = new ButtonType("Oui");
                ButtonType boutonNon = new ButtonType("Non");
                alert.getButtonTypes().setAll(boutonOui, boutonNon);
                alert.showAndWait();
                
                if (alert.getResult() == boutonOui) {
                    // méthode suppression
                }
            	
            }
        });
        
        
        
        
        
        
    }
}
