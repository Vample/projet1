package fr.isika.cda25.projet1.vue;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Comparator;
import java.util.List;

import fr.isika.cda25.projet1.model.Annuaire;
import fr.isika.cda25.projet1.model.NoeudCellule;
import fr.isika.cda25.projet1.model.Stagiaire;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class TableStagiaires {

	private TableView<Stagiaire> table;
	private Annuaire annuaire;

	public TableStagiaires()  {
		this.annuaire = new Annuaire();

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
			genererListe(annuaire);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		table.getColumns().addAll(colNom, colPrenom, colDP, colFormation, colAnnee);
	}

	public void genererListe(Annuaire annuaire) throws IOException {


		this.afficherStagiaires(annuaire.recupererTousLesNoeuds()); 


	}
	
	public void afficherStagiaires(List<NoeudCellule> list) {
		
		//récupère la liste de stagiaires, en fait une obsertabeList
		ObservableList<Stagiaire> resul = FXCollections.observableArrayList();
		
		for (NoeudCellule stagiaireDansNoeud : list) {
			resul.add(stagiaireDansNoeud.getCle());
		}
		
			
		this.table.setItems(resul); 
		
		

	}
	
	public void afficherListeFiltree(FilteredList<Stagiaire> listeFiltree) {
		this.table.setItems(listeFiltree);
	}

	public TableView<Stagiaire> getTable() {
		return table;
	}

	public void setTable(TableView<Stagiaire> table) {
		this.table = table;
	}

}
