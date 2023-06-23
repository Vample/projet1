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
	

	private VueAnnuaire vueAnnuaire;
    private Annuaire annuaire = new Annuaire();

    @Override
    public void start(Stage stage) {
    	VueAnnuaire vueAnnuaire = new VueAnnuaire(stage);

    	
    	
        Scene scene = new Scene(vueAnnuaire);
      //  scene.getRoot().setStyle(“-fx-font-family: ‘serif’“);
        //stage.setMaximized(true);
        stage.setTitle("Liste des stagiaires");
        stage.setScene(scene);
        stage.show();
        vueAnnuaire.getButtonRechercher().requestFocus();
        
   
        
        
//        
        
        
        
        
    }

    public static void main(String[] args) {
        launch();
    }

}
