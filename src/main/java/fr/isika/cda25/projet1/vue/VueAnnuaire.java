package fr.isika.cda25.projet1.vue;

import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

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
	
	protected TableStagiaires listeStagiaires;

    public VueAnnuaire(Stage stageAnnuaire) {
        HBox hboxbarreOption = new HBox();
        hboxbarreOption.setSpacing(10);

        listeStagiaires = new TableStagiaires();

        this.add(hboxbarreOption, 0, 0);
        this.add(listeStagiaires.getTable(), 0, 1);

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
        buttonAjouter.setStyle("-fx-font-size: 12px; -fx-font-weight: bold; -fx-background-color: green;");
        hboxbarreOption.getChildren().add(buttonAjouter);
        
        
        buttonAjouter.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            	Stage stageAjouter = new Stage();
                VueAjouter vueAjouter = new VueAjouter(stageAnnuaire);
                Scene sceneAjouter = new Scene(vueAjouter, 300,200);
                
                stageAjouter.setScene(sceneAjouter);
                stageAjouter.setTitle("Ajout d'un stagiaire");
                stageAjouter.show();
                
            }
        });

        buttonModifier = new Button("✎");
        buttonModifier.setStyle("-fx-font-size: 12px; -fx-font-weight: bold;");
        hboxbarreOption.getChildren().add(buttonModifier);
        buttonModifier.setDisable(true);

        buttonSupprimer = new Button("➖");
        buttonSupprimer.setStyle("-fx-font-size: 12px; -fx-font-weight: bold; -fx-background-color: #FF0000;");
        hboxbarreOption.getChildren().add(buttonSupprimer);
        buttonSupprimer.setDisable(true);

        buttonAdmin = new Button("Connexion");
        hboxbarreOption.getChildren().add(buttonAdmin);
        
        buttonAdmin.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            	Stage stageConnexion = new Stage();
                VueConnexion vueConnexion = new VueConnexion(stageAnnuaire, stageConnexion);
                Scene sceneConnexion = new Scene(vueConnexion, 300,200);
                
                stageConnexion.setScene(sceneConnexion);
                stageConnexion.setTitle("Connexion");
                stageConnexion.show();
                
            }
        });
    }

    public Button getButtonRechercher() {
        return buttonRechercher;
    }

	public Button getButtonAdmin() {
		return buttonAdmin;
	}

	public void setButtonAdmin(Button buttonAdmin) {
		this.buttonAdmin = buttonAdmin;
	}

	public Button getButtonModifier() {
		return buttonModifier;
	}

	public void setButtonModifier(Button buttonModifier) {
		this.buttonModifier = buttonModifier;
	}

	public Button getButtonSupprimer() {
		return buttonSupprimer;
	}

	public void setButtonSupprimer(Button buttonSupprimer) {
		this.buttonSupprimer = buttonSupprimer;
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

	public TableStagiaires getListeStagiaires() {
		return listeStagiaires;
	}

	public void setListeStagiaires(TableStagiaires listeStagiaires) {
		this.listeStagiaires = listeStagiaires;
	}

	public void setButtonRechercher(Button buttonRechercher) {
		this.buttonRechercher = buttonRechercher;
	}
    
    
}
