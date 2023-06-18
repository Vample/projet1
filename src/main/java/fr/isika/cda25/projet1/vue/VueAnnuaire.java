package fr.isika.cda25.projet1.vue;

import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.control.Button;


/**
 * JavaFX App
 */
public class VueAnnuaire extends GridPane {
	protected Button buttonRechercher;
	protected Button buttonAdmin;
	protected Button buttonModifier;
	protected Button buttonSupprimer;
	
    public VueAnnuaire() {
    	HBox hboxbarreOption = new HBox();
    	TableStagiaires listeStagiaires = new TableStagiaires();
    	
    	hboxbarreOption.setSpacing(10);

    	
    	this.add(hboxbarreOption,0,0);
    	this.add(listeStagiaires.getTable(),0,1);
    	
    	TextField txtNom = new TextField();
    	txtNom.setPromptText("Nom");
    	hboxbarreOption.getChildren().add(txtNom);
    	
    	TextField txtPrenom = new TextField();
    	txtPrenom.setPromptText("Prénom");
    	hboxbarreOption.getChildren().add(txtPrenom);
    	
    	TextField txtDepartement = new TextField();
    	txtDepartement.setPromptText("N°Département");
    	hboxbarreOption.getChildren().add(txtDepartement);
    	
    	TextField txtFormation = new TextField();
    	txtFormation.setPromptText("Formation");
    	hboxbarreOption.getChildren().add(txtFormation);
    	
    	TextField txtAnnee = new TextField();
    	txtAnnee.setPromptText("Année d'entrée");
    	hboxbarreOption.getChildren().add(txtAnnee);
    	
    	buttonRechercher = new Button("Rechercher");
    	hboxbarreOption.getChildren().add(buttonRechercher);
    	
    	Button buttonAjouter = new Button("➕");
    	buttonAjouter.setStyle("-fx-font-size: 12px; -fx-font-weight: bold;");
    	hboxbarreOption.getChildren().add(buttonAjouter);
    	
    	buttonModifier = new Button("✎");
    	buttonModifier.setStyle("-fx-font-size: 12px; -fx-font-weight: bold;");
    	hboxbarreOption.getChildren().add(buttonModifier);
    	buttonModifier.setDisable(true);
    	
    	buttonSupprimer = new Button("➖");
    	buttonSupprimer.setStyle("-fx-font-size: 12px; -fx-font-weight: bold;");
    	hboxbarreOption.getChildren().add(buttonSupprimer);
    	buttonSupprimer.setDisable(true);
    	
    	buttonAdmin = new Button("Connexion");
    	hboxbarreOption.getChildren().add(buttonAdmin);
    	
    }
    
    public Button getButtonRechercher() {
    	return buttonRechercher;
    }
    
    public Button getButtonAdmin() {
    	return buttonAdmin;
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
}
