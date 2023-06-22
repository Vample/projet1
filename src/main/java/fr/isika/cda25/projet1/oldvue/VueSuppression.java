package fr.isika.cda25.projet1.oldvue;

import fr.isika.cda25.projet1.model.Stagiaire;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class VueSuppression extends BorderPane {

	private Label labelNom;
	private Label labelPrenom;
	private Label labelCP;
	private Label labelSession;
	private Label labelAnneeRentree;
	private Button buttonRetour;
	private Button buttonSupprimer;
	private VBox boxInfos;

	public VueSuppression(Stagiaire stagiaire) {

		// Initilisation des éléments de la vue

		Label infoLabel = new Label("Attention, vous vous apprêtez à supprimer le stagiaire suivant:");

		labelNom = new Label("Nom : " + stagiaire.getNom());
		labelPrenom = new Label("Prenom : " + stagiaire.getPrenom());
		labelCP = new Label("Code Postal : " + stagiaire.getDepartement());
		labelSession = new Label("Session :" + stagiaire.getFormation());
		labelAnneeRentree = new Label("Année de rentrée : " + stagiaire.getAnneeRentree());

		buttonRetour = new Button("Retour");
		buttonSupprimer = new Button("Supprimer le stagiaire");
		boxInfos = new VBox(labelNom, labelPrenom, labelCP, labelSession, labelAnneeRentree, buttonSupprimer);

		// Ajout des evenements
		buttonRetour.setOnAction(event -> {
			// pour retourner à la vue précédente
		});
		buttonSupprimer.setOnAction(event -> {
			// pour supprimer le stagiaire
		});
		// configuration de la vue
		setTop(buttonRetour);

		setCenter(boxInfos);
		setAlignment(boxInfos, Pos.CENTER);
		setMargin(boxInfos, new Insets(20));
		setPadding(new Insets(20));
		setPrefSize(400, 400);
	}

}
