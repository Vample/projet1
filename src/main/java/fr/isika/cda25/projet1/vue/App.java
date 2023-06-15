package fr.isika.cda25.projet1.vue;

import java.util.Optional;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextInputDialog;
import javafx.stage.Stage;


/**
 * JavaFX App
 */
public class App extends Application {
	
    private VueAnnuaire vueAnnuaire;

    @Override
    public void start(Stage stage) {
    	VueAnnuaire vueAnnuaire = new VueAnnuaire();
    	VueAdmin vueAdmin = new VueAdmin();
        Scene scene = new Scene(vueAnnuaire);
        stage.setMaximized(true);
        stage.setTitle("Liste des stagiaires");
        stage.setScene(scene);
        stage.show();
        vueAnnuaire.getButtonRechercher().requestFocus();
        
        vueAnnuaire.getButtonAdmin().setOnAction(new EventHandler<ActionEvent>() {
        	
        	@Override
        	public void handle(ActionEvent event) {
        		TextInputDialog dialog = new TextInputDialog();
                dialog.setTitle("Connexion");
                dialog.setHeaderText("Veuillez saisir le mot de passe :");
                dialog.setContentText("Mot de passe:");

                Optional<String> result = dialog.showAndWait();
                result.ifPresent(password -> {
                    // Vérifiez le mot de passe ici
                    if (password.equals("cda25admin")) {
                    	scene.setRoot(vueAdmin);
                    	vueAdmin.getButtonRechercher().requestFocus();
                    } else {
                    	Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Erreur");
                        alert.setHeaderText("Mot de passe incorrect");
                        alert.setContentText("Veuillez saisir un mot de passe valide.");
                        alert.showAndWait();
                    }
                });
            }
		});
        
        vueAdmin.getButtonAdmin().setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				scene.setRoot(vueAnnuaire);
				
			}
		});
        
        
        
        
    }

    public static void main(String[] args) {
        launch();
    }

}
