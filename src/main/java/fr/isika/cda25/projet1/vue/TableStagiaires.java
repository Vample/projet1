package fr.isika.cda25.projet1.vue;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Comparator;

import fr.isika.cda25.projet1.model.Annuaire;
import fr.isika.cda25.projet1.model.NoeudCellule;
import fr.isika.cda25.projet1.model.Stagiaire;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class TableStagiaires {
	
	private TableView<Stagiaire> table;

	public TableStagiaires() {
		this.table = new TableView<Stagiaire>();

		// Create column UserName (Data type of String).
		TableColumn<Stagiaire, String> colNom //
				= new TableColumn<Stagiaire, String>("Nom");

		// Create column Email (Data type of String).
		TableColumn<Stagiaire, String> colPrenom//
				= new TableColumn<Stagiaire, String>("Prenom");

		// Create column FullName (Data type of String).
		TableColumn<Stagiaire, String> colDP//
				= new TableColumn<Stagiaire, String>("Departement");

		// Active Column
		TableColumn<Stagiaire, String> colFormation//
				= new TableColumn<Stagiaire, String>("Formation");

		TableColumn<Stagiaire, Integer> colAnnee//
				= new TableColumn<Stagiaire, Integer>("Année");

		// Defines how to fill data for each cell.
		// Get value from property of UserAccount. .
//	      userNameCol.setCellValueFactory(new PropertyValueFactory<>("userName"));
//	      emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));
//	      firstNameCol.setCellValueFactory(new PropertyValueFactory<>("firstName"));
//	      lastNameCol.setCellValueFactory(new PropertyValueFactory<>("lastName"));
//	      activeCol.setCellValueFactory(new PropertyValueFactory<>("active"));

		colNom.setCellValueFactory(new PropertyValueFactory<>("nom"));
		colPrenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
		colDP.setCellValueFactory(new PropertyValueFactory<>("departement"));
		colFormation.setCellValueFactory(new PropertyValueFactory<>("formation"));
		colAnnee.setCellValueFactory(new PropertyValueFactory<>("anneeRentree"));
		
		double largeurRelative = 0.2;
		
		colNom.prefWidthProperty().bind(table.widthProperty().multiply(largeurRelative));
		colPrenom.prefWidthProperty().bind(table.widthProperty().multiply(largeurRelative));
		colDP.prefWidthProperty().bind(table.widthProperty().multiply(largeurRelative));
		colFormation.prefWidthProperty().bind(table.widthProperty().multiply(largeurRelative));
		colAnnee.prefWidthProperty().bind(table.widthProperty().multiply(largeurRelative));

	
		// Display row data
		try {
			table.setItems(genererListe());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		table.sort();

		table.getColumns().addAll(colNom, colPrenom, colDP, colFormation, colAnnee);
	}
	
	public ObservableList<Stagiaire> genererListe() throws IOException {
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

	public TableView<Stagiaire> getTable() {
		return table;
	}

	public void setTable(TableView<Stagiaire> table) {
		this.table = table;
	}

	
	

}
