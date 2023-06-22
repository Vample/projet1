package fr.isika.cda25.projet1.old;

import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import fr.isika.cda25.projet1.model.Annuaire;
import fr.isika.cda25.projet1.oldvue.VueAnnuaire;
import javafx.scene.control.Button;


/**
 * JavaFX App
 */
public class VueAdminOld extends VueAnnuaire {
//	private Button buttonRechercher;
//	private Button buttonAdmin;
	
	public VueAdminOld(Stage stageAnnuaire) {
		super(stageAnnuaire);
		buttonModifier.setDisable(false);
		buttonSupprimer.setDisable(false);
		buttonAdmin.setText("Déconnexion");
		
	}
	
	

    
    public Button getButtonRechercher() {
    	return buttonRechercher;
    }
    
    public Button getButtonAdmin() {
    	return buttonAdmin;
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
}
