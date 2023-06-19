package fr.isika.cda25.projet1.vue;

import fr.isika.cda25.projet1.model.Stagiaire;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;

public class VueSuppression2 extends BorderPane {

	private Stagiaire stagiaire;

	public VueSuppression2(Stagiaire stagiaire )
			  {
		VBox infoBox = new VBox();
		this.stagiaire = stagiaire;
		
		VBox root = new VBox();
		root.setAlignment(Pos.CENTER);
		root.setSpacing(20);

		// Box d'information
		Label infoLabel = new Label("Attention, vous vous apprêtez à supprimer le stagiaire suivant:");

		Label nomLabel = new Label(" - Nom : " + stagiaire.getNom());
		Label prenomLabel = new Label(" - Prénom : " + stagiaire.getPrenom());
		Label cpLabel = new Label(" - CP : " + stagiaire.getDepartement());
		Label sessionLabel = new Label(" - Session : " + stagiaire.getFormation());
		Label anneeRentreeLabel = new Label(" - Année de rentrée : " + stagiaire.getAnneeRentree());
		infoBox.getChildren().addAll(infoLabel, nomLabel, prenomLabel, cpLabel,sessionLabel, anneeRentreeLabel);

		// Boutton retour
		Button retourBtn = new Button(" Retour");
		retourBtn.setShape(new Circle(45));
		retourBtn.setStyle("-fx-background-color: lightblue");
		retourBtn.setOnAction(even -> {
			// action à executer lors du clic sur le bouton
		});
		// Boutton suppression
		Button suppressionBtn = new Button("Supprimer le stagiaire");
		suppressionBtn.setStyle("-fx-background-color: blue; -fx-text-fill: white");
		suppressionBtn.setOnAction(even -> {
			// action à executer lors du clic sur le bouton suppression
		});
		root.getChildren().addAll(retourBtn, infoBox, suppressionBtn);
		this.setCenter(root);
		
		//Scene scene = new Scene(root, 400, 300);
		//scene.getRoot().setStyle("-fx-font-family: 'serif'");

		//((Stage)suppressionBtn.getScene().getRoot()).setScene(scene);
		//stage.show();

	}

}
