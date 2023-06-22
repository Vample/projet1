package fr.isika.cda25.projet1.vue;

import java.io.IOException;

import fr.isika.cda25.projet1.model.Annuaire;
import fr.isika.cda25.projet1.model.Stagiaire;
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
	private TextField txtPrenom;
	private TextField txtDepartement;
	private TextField txtFormation;
	private TextField txtAnnee;
	private Stagiaire stagiaireCible;

	public VueModifier(Stage stageAnnuaire, Stage stageModifier, TableStagiaires listeStagiaires) {
		String nom = "test";
		String prenom = "de";
		String departement = "modifier";
		String formation = "Formation";
		String annee = "Année";

		txtNom = new TextField();
		txtNom.setPromptText(nom);

		txtPrenom = new TextField();
		txtPrenom.setPromptText(prenom);

		txtDepartement = new TextField();
		txtDepartement.setPromptText(departement);

		txtFormation = new TextField();
		txtFormation.setPromptText(formation);

		txtAnnee = new TextField();
		txtAnnee.setPromptText(annee);
        Button btnModifier = new Button("Modifier");
        
        Stagiaire stagiaireCible = listeStagiaires.getTable().getSelectionModel().getSelectedItem();
        
        if (stagiaireCible != null) {
            txtNom.setText(stagiaireCible.getNom());
            txtPrenom.setText(stagiaireCible.getPrenom());
            txtDepartement.setText(stagiaireCible.getDepartement());
            txtFormation.setText(stagiaireCible.getFormation());
            txtAnnee.setText(String.valueOf(stagiaireCible.getAnneeRentree()));
        }

        btnModifier.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				//stagiaireCible = listeStagiaires.getTable().getSelectionModel().getSelectedItem();
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
                	Annuaire annuaire = new Annuaire();
                	annuaire.supprimerStagiaire(stagiaireCible);
                	
                	annuaire.ajouterStagiaire(new Stagiaire(
                		    txtNom.getText(),
                		    txtPrenom.getText(),
                		    txtDepartement.getText(),
                		    txtFormation.getText(),
                		    Integer.parseInt(txtAnnee.getText())
                		));
                	
                	
                	try {
						listeStagiaires.genererListe(annuaire);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
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
