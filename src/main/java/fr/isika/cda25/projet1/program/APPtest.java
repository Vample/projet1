package fr.isika.cda25.projet1.program;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Comparator;

import fr.isika.cda25.projet1.model.Annuaire;
import fr.isika.cda25.projet1.model.NoeudCellule;
import fr.isika.cda25.projet1.model.Stagiaire;
import fr.isika.cda25.projet1.oldvue.TableStagiaires;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class APPtest extends Application {
	@Override
	public void start(Stage stage) {

		
		TableStagiaires listeStagiaires = new TableStagiaires();
		
		StackPane root = new StackPane();
		root.setPadding(new Insets(5));
		root.getChildren().add(listeStagiaires.getTable());

		stage.setTitle("TableView (o7planning.org)");

		Scene scene = new Scene(root, 450, 300);
		stage.setScene(scene);
		stage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}

	public ObservableList<Stagiaire> getListe() throws IOException {
		ObservableList<Stagiaire> resul = FXCollections.observableArrayList();
		
		
	RandomAccessFile raf = new RandomAccessFile("src/main/java/fr/isika/cda25/projet1/files/stagiaires.bin", "rw");
		
		Annuaire annuaire = new Annuaire();
		
		for (int i = 0; i < raf.length()/NoeudCellule.TAILLE_NOEUD_OCTET; i++) {
			resul.add(annuaire.chercherNoeud(i).getCle());
		}
		
		annuaire.getRaf().close();
		

//		resul.add(new Stagiaire("Dechaumet", "Lucas", "75", "CDA25", 2023));
//		resul.add(new Stagiaire("Fahem", "Imene", "75", "CDA25", 2023));
//		resul.add(new Stagiaire("Brahmi", "Mohamed","75","CDA25",2023));
//		resul.add(new Stagiaire("Mebitil", "Ezaiah", "75", "CDA25", 2023));
//		resul.add(new Stagiaire("Kerbeikian", "Thade", "75", "CDA25", 2023));
//		resul.add(new Stagiaire("Bouton", "Jean-Baptiste", "75", "CDA25", 2023));
//		resul.add(new Stagiaire("Alonso", "Miguel", "75", "CDA25", 2023));
		
		Comparator<Stagiaire> comparateur = Comparator.comparing(Stagiaire::getNom);
		FXCollections.sort(resul, comparateur);

		return resul;

	}

}
