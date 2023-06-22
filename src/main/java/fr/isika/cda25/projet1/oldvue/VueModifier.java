package fr.isika.cda25.projet1.oldvue;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class VueModifier extends GridPane {
	
	 private TextField txtNom; 

    public VueModifier(Stage stageAnnuaire) {
    	
    	
    	String nom = "test";
    	String prenom = "de";
    	String departement = "modifier";
    	String formation = "Formation";
    	String annee = "Année";
    	
    	
    	
        txtNom = new TextField();
        txtNom.setPromptText(nom);

        TextField txtPrenom = new TextField();
        txtPrenom.setPromptText(prenom);

        TextField txtDepartement = new TextField();
        txtDepartement.setPromptText(departement);

        TextField txtFormation = new TextField();
        txtFormation.setPromptText(formation);

        TextField txtAnnee = new TextField();
        txtAnnee.setPromptText(annee);

        Button btnModifier = new Button("Modifier");

        btnModifier.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				Alert alert = new Alert(AlertType.CONFIRMATION);
                alert.setTitle("Confirmation de modification");
                alert.setHeaderText(null);
                alert.setContentText("Êtes-vous sûr de vouloir modifier cet élément ?");

                ButtonType boutonOui = new ButtonType("Oui");
                ButtonType boutonNon = new ButtonType("Non");
                alert.getButtonTypes().setAll(boutonOui, boutonNon);
                alert.showAndWait();
                
                if (alert.getResult() == boutonOui) {
                	stageModifier.close();
                    // méthode de modif
                }
            	
            }
        });

        setPadding(new Insets(10));
        setHgap(10);
        setVgap(10);

        add(txtNom, 0, 0);
        add(txtPrenom, 1, 0);
        add(txtDepartement, 2, 0);
        add(txtFormation, 0, 1);
        add(txtAnnee, 1, 1);

        add(btnModifier, 0, 2);
    }
}
