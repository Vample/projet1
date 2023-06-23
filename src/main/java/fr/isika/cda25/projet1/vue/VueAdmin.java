package fr.isika.cda25.projet1.vue;

import java.io.IOException;

import fr.isika.cda25.projet1.model.Annuaire;
import fr.isika.cda25.projet1.model.Stagiaire;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

public class VueAdmin extends VueAnnuaire {
	private Stagiaire stagiaireCible;

	public VueAdmin(Stage stageAnnuaire) {
		super(stageAnnuaire);
		buttonModifier.setDisable(false);
		buttonSupprimer.setDisable(false);
		buttonAdmin.setText("Déconnexion");
		System.out.println(stagiaireCible);

		buttonAdmin.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {

				VueAnnuaire vueAnnuaire = new VueAnnuaire(stageAnnuaire);
				Scene sceneAnnuaire = new Scene(vueAnnuaire);
				//sceneAnnuaire.getRoot().setStyle(“-fx-font-family: ‘serif’“);
				stageAnnuaire.setScene(sceneAnnuaire);
			}
		});

		buttonSupprimer.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				stagiaireCible = listeStagiaires.getTable().getSelectionModel().getSelectedItem();

				if (stagiaireCible != null) {

					Alert alert = new Alert(AlertType.CONFIRMATION);
					alert.setTitle("Confirmation de suppression");
					alert.setHeaderText(null);
					alert.setContentText("Êtes-vous sûr de vouloir retirer ce stagiaire de la liste ?" + "\n" + "Nom : "
							+ stagiaireCible.getNom() + "\n" + "Prénom : " + stagiaireCible.getPrenom() + "\n"
							+ "Code département : " + stagiaireCible.getDepartement() + "\n" + "Formation : "
							+ stagiaireCible.getFormation() + "\n" + "Année d'entrée : "
							+ stagiaireCible.getAnneeRentree());
					ButtonType boutonOui = new ButtonType("Oui");
					ButtonType boutonNon = new ButtonType("Non");
					alert.getButtonTypes().setAll(boutonOui, boutonNon);
					alert.showAndWait();

					if (alert.getResult() == boutonOui) {
						// méthode suppression
						Annuaire annuaire = new Annuaire();
						annuaire.supprimerStagiaire(stagiaireCible);
						try {
							listeStagiaires.genererListe(annuaire);
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

					}

				}
			}
		});

		buttonModifier.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				Stage stageModifier = new Stage();
				VueModifier vueModifier = new VueModifier(stageAnnuaire, stageModifier, listeStagiaires);
				//sceneModifier.getRoot().setStyle(“-fx-font-family: ‘serif’“);
				Scene sceneModifier = new Scene(vueModifier, 600, 400);

				stageModifier.setScene(sceneModifier);
				stageModifier.setTitle("Modification d'un stagiaire");
				stageModifier.show();

			}
		});

	}
}
