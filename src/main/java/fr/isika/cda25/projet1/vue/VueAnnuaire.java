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
	
	protected TextField txtNom;
	protected TextField txtPrenom;
	protected TextField txtDepartement;
	protected TextField txtFormation;
	protected TextField txtAnnee;
	
//private Annuaire annuaire;
	private TableStagiaires listeStagiaires;
	
    public VueAnnuaire() {
    	HBox hboxbarreOption = new HBox();
    	this.listeStagiaires = new TableStagiaires();
    	
    	hboxbarreOption.setSpacing(10);

    	
    	this.add(hboxbarreOption,0,0);
    	this.add(listeStagiaires.getTable(),0,1);
    	
    	txtNom = new TextField();
    	txtNom.setPromptText("Nom");
    	hboxbarreOption.getChildren().add(txtNom);
    	
    	txtPrenom = new TextField();
    	txtPrenom.setPromptText("Prénom");
    	hboxbarreOption.getChildren().add(txtPrenom);
    	
    	txtDepartement = new TextField();
    	txtDepartement.setPromptText("N°Département");
    	hboxbarreOption.getChildren().add(txtDepartement);
    	
    	txtFormation = new TextField();
    	txtFormation.setPromptText("Formation");
    	hboxbarreOption.getChildren().add(txtFormation);
    	
    	txtAnnee = new TextField();
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

	public TableStagiaires getListeStagiaires() {
		return listeStagiaires;
	}

	public void setListeStagiaires(TableStagiaires listeStagiaires) {
		this.listeStagiaires = listeStagiaires;
	}

	public TextField getTxtNom() {
		return txtNom;
	}

	public void setTxtNom(TextField txtNom) {
		this.txtNom = txtNom;
	}

	public TextField getTxtPrenom() {
		return txtPrenom;
	}

	public void setTxtPrenom(TextField txtPrenom) {
		this.txtPrenom = txtPrenom;
	}

	public TextField getTxtDepartement() {
		return txtDepartement;
	}

	public void setTxtDepartement(TextField txtDepartement) {
		this.txtDepartement = txtDepartement;
	}

	public TextField getTxtFormation() {
		return txtFormation;
	}

	public void setTxtFormation(TextField txtFormation) {
		this.txtFormation = txtFormation;
	}

	public TextField getTxtAnnee() {
		return txtAnnee;
	}

	public void setTxtAnnee(TextField txtAnnee) {
		this.txtAnnee = txtAnnee;
	}
	
	
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
