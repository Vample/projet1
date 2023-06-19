package fr.isika.cda25.projet1.program;

import java.io.IOException;
import java.util.Optional;

import fr.isika.cda25.projet1.model.Annuaire;
import fr.isika.cda25.projet1.vue.VueAdmin;
import fr.isika.cda25.projet1.vue.VueAnnuaire;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextInputDialog;
import javafx.stage.Stage;


/**
 * JavaFX App
 */



public class App extends Application {
	
    @Override
	public void init() throws Exception {
		// TODO Auto-generated method stub
		super.init();
	}

	private VueAnnuaire vueAnnuaire;
    private Annuaire annuaire = new Annuaire();

    @Override
    public void start(Stage stage) {
    	VueAnnuaire vueAnnuaire = new VueAnnuaire();
    	VueAdmin vueAdmin = new VueAdmin();
    	
    	
        Scene scene = new Scene(vueAnnuaire);
        //stage.setMaximized(true);
        stage.setTitle("Liste des stagiaires");
        stage.setScene(scene);
        stage.setWidth(scene.getWidth()*1.1);
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
        
        vueAnnuaire.getButtonRechercher().setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				//Ici on lance la méthode de l'annuaire qui sort une List et la donne au TableView
				vueAnnuaire.getListeStagiaires().extraireStagiairesSpecifiques(annuaire.rechercheAvancee(vueAnnuaire.getTxtNom().getText().toUpperCase(),
						vueAnnuaire.getTxtPrenom().getText(), vueAnnuaire.getTxtDepartement().getText(), vueAnnuaire.getTxtFormation().getText(), vueAnnuaire.getTxtAnnee().getText()));
				
				
			}
		});
        
        
        
        
    }

    public static void main(String[] args) {
        launch();
    }

}
