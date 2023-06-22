package fr.isika.cda25.projet1.vue;

import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

import java.util.List;

import fr.isika.cda25.projet1.model.Annuaire;
import fr.isika.cda25.projet1.model.NoeudCellule;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
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
                VueAjouter vueAjouter = new VueAjouter(stageAnnuaire, listeStagiaires);
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
}
    