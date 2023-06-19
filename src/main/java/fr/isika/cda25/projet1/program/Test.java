package fr.isika.cda25.projet1.program;

import java.util.Optional;

import fr.isika.cda25.projet1.model.Stagiaire;
import fr.isika.cda25.projet1.vue.VueAdmin;
import fr.isika.cda25.projet1.vue.VueAnnuaire;
import fr.isika.cda25.projet1.vue.VueSuppression;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextInputDialog;
import javafx.stage.Stage;

public class Test extends Application{

	private VueAnnuaire vueAnnuaire;

    @Override
    public void start(Stage stage) {
    	VueSuppression vueSuppression = new VueSuppression(new Stagiaire("Dechaumet", "Lucas","75","CDA25",2023));
    	
        Scene scene = new Scene(vueSuppression);
        scene.getRoot().setStyle("-fx-font-family: 'serif'");
		
        stage.setMaximized(true);
        stage.setTitle("Liste des stagiaires");
        stage.setScene(scene);
        stage.show();
         
    }

    public static void main(String[] args) {
        launch();
    }

}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

