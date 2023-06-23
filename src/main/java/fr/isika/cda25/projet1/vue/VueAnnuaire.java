package fr.isika.cda25.projet1.vue;

import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

import java.io.IOException;
import java.util.List;

import fr.isika.cda25.projet1.model.Annuaire;
import fr.isika.cda25.projet1.model.NoeudCellule;
import fr.isika.cda25.projet1.model.Stagiaire;
import javafx.collections.transformation.FilteredList;
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
		Tooltip tooltipAjouter = new Tooltip(" Ajouter un stagiaire ");
		buttonAjouter.setTooltip(tooltipAjouter);

		buttonAjouter.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				Stage stageAjouter = new Stage();
				VueAjouter vueAjouter = new VueAjouter(stageAnnuaire, listeStagiaires);
				Scene sceneAjouter = new Scene(vueAjouter, 300, 200);

				stageAjouter.setScene(sceneAjouter);
				stageAjouter.setTitle("Ajout d'un stagiaire");
				stageAjouter.show();

			}
		});

		buttonModifier = new Button("✎");
		buttonModifier.setStyle("-fx-font-size: 12px; -fx-font-weight: bold;");
		hboxbarreOption.getChildren().add(buttonModifier);
		buttonModifier.setDisable(true);
		
		Tooltip tooltipModifier = new Tooltip(" Modifier un stagiaire ");
		buttonModifier.setTooltip(tooltipModifier);

		buttonSupprimer = new Button("➖");
		buttonSupprimer.setStyle("-fx-font-size: 12px; -fx-font-weight: bold; -fx-background-color: #FF0000;");
		hboxbarreOption.getChildren().add(buttonSupprimer);
		buttonSupprimer.setDisable(true);
		Tooltip tooltipSupprimer = new Tooltip(" Supprimer un stagiaire ");
		buttonSupprimer.setTooltip(tooltipSupprimer);
		

		buttonAdmin = new Button("Connexion");
		hboxbarreOption.getChildren().add(buttonAdmin);

		buttonAdmin.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				Stage stageConnexion = new Stage();
				VueConnexion vueConnexion = new VueConnexion(stageAnnuaire, stageConnexion);
				Scene sceneConnexion = new Scene(vueConnexion, 300, 200);

				stageConnexion.setScene(sceneConnexion);
				stageConnexion.setTitle("Connexion");
				stageConnexion.show();

			}
		});

		buttonRechercher.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// Créé une liste filtrée à partir de l'ObservableList

				try {
					Annuaire annuaire = new Annuaire();
					listeStagiaires.genererListe(annuaire);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				FilteredList<Stagiaire> filteredList = new FilteredList<>(listeStagiaires.getTable().getItems());

				// Effet entonoir

//					filteredList.setPredicate(
//							stagiaire ->(stagiaire.getNom().toLowerCase().contains(txtNom.getText().toLowerCase())
//									|| stagiaire.getPrenom().toLowerCase().contains(txtPrenom.getText().toLowerCase())));

				if (!txtNom.getText().isEmpty()) {
					filteredList = new FilteredList<>(filteredList.filtered(
							stagiaire -> stagiaire.getNom().toLowerCase().contains(txtNom.getText().toLowerCase())));
				}

				if (!txtPrenom.getText().isEmpty()) {
					filteredList = new FilteredList<>(filteredList.filtered(stagiaire -> stagiaire.getPrenom()
							.toLowerCase().contains(txtPrenom.getText().toLowerCase())));
				}

				if (!txtDepartement.getText().isEmpty()) {
					filteredList = new FilteredList<>(filteredList.filtered(stagiaire -> stagiaire.getDepartement()
							.toLowerCase().contains(txtDepartement.getText().toLowerCase())));
				}

				if (!txtFormation.getText().isEmpty()) {
					filteredList = new FilteredList<>(filteredList.filtered(stagiaire -> stagiaire.getFormation()
							.toLowerCase().contains(txtFormation.getText().toLowerCase())));
				}

				if (!txtAnnee.getText().isEmpty()) {
					filteredList = new FilteredList<>(filteredList.filtered(
							stagiaire -> String.valueOf(stagiaire.getAnneeRentree()).contains(txtAnnee.getText())));
				}

				listeStagiaires.afficherListeFiltree(filteredList);

			}
		});

	}

	public Button getButtonRechercher() {
		return buttonRechercher;
	}
}
