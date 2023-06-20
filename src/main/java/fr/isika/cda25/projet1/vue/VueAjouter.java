package fr.isika.cda25.projet1.vue;

import fr.isika.cda25.projet1.model.Annuaire;
import fr.isika.cda25.projet1.model.Stagiaire;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class VueAjouter extends GridPane {
	
	 private TextField txtNom; 

    public VueAjouter(Stage stageAnnuaire) {
    	
    	String nom = "Nom";
    	String prenom = "Prénom";
    	String departement = "Département";
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

        Button btnAjouter = new Button("Ajouter");

        btnAjouter.setOnAction(new EventHandler<ActionEvent>() {
    		
         	
        	//instance de la classe annuaire
        	//on récupére les valeurs entrées dans les champs de texte
    			@Override
    			public void handle(ActionEvent event) {
    				// TODO Auto-generated method stub
    				//récupération des valeurs de champs de texte
    				String nom = txtNom.getText();
    				String prenom = txtPrenom.getText();
    				String departement = txtDepartement.getText();
    				String formation = txtFormation.getText();
    				//int annee = txtAnnee.getText();
    				
    				int annee = Integer.parseInt(txtAnnee.getText());
    				
    				//créer nouveau stagiaire avec les valeurs récupérer
    				Stagiaire nouveauStagiaire = new Stagiaire();
    				nouveauStagiaire.setNom(nom.toUpperCase());
    				nouveauStagiaire.setPrenom(prenom);
    				nouveauStagiaire.setDepartement(departement);
    				nouveauStagiaire.setFormation(formation);
    				nouveauStagiaire.setAnneeRentree(annee);
    				//String annee = txtAnnee.setText();
    				
    				//nouvel annuaire
    				//nouveau stagiaire à partir des txtfield
    				//appel la méthode ajouterStagiaire de l’annuaire
    				//ajouter nouveau stagiaire a l’annuaire
    				//créer une nouvelle instance de la classe annuaire
        			Annuaire monAnnuaire = new Annuaire();
    				monAnnuaire.ajouterStagiaire(nouveauStagiaire);
    			
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

        add(btnAjouter, 0, 2);
    }
}
