package fr.isika.cda25.projet1.program;

import java.io.IOException;
import java.util.Optional;

import fr.isika.cda25.projet1.model.Annuaire;
import fr.isika.cda25.projet1.oldvue.VueAdmin;
import fr.isika.cda25.projet1.oldvue.VueAnnuaire;
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
	

	private VueAnnuaire vueAnnuaire;
    private Annuaire annuaire = new Annuaire();

    @Override
    public void start(Stage stage) {
    	VueAnnuaire vueAnnuaire = new VueAnnuaire(stage);

    	
    	
        Scene scene = new Scene(vueAnnuaire);
        //stage.setMaximized(true);
        stage.setTitle("Liste des stagiaires");
        stage.setScene(scene);
        stage.show();
        vueAnnuaire.getButtonRechercher().requestFocus();
        
   
        
        
        vueAnnuaire.getButtonRechercher().setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				//Ici on lance la méthode de l'annuaire qui sort une List et la donne au TableView
				vueAnnuaire.getListeStagiaires().afficherStagiaires(annuaire.rechercheAvancee(vueAnnuaire.getTxtNom().getText().toUpperCase(),
						vueAnnuaire.getTxtPrenom().getText(), vueAnnuaire.getTxtDepartement().getText(), vueAnnuaire.getTxtFormation().getText(), vueAnnuaire.getTxtAnnee().getText()));
				
				
			}
		});
        
        
        
        
    }

    public static void main(String[] args) {
        launch();
    }

}
