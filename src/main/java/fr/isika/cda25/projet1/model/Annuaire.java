package fr.isika.cda25.projet1.model;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Annuaire {

	// attributs
	private static String ADDRESSE_FICHIER_BINAIRE = "src/main/java/fr/isika/cda25/projet1/files/stagiaires.bin";

	private NoeudCellule racine;
	private RandomAccessFile raf;
	// constructeurs

	public Annuaire() {
		this.racine = new NoeudCellule(null, 0);
		this.racine = this.chercherNoeud(0);
		this.raf = null;
	}

	public Annuaire(String adresseRaf) {
		setADDRESSE_FICHIER_BINAIRE(adresseRaf);
		this.racine = new NoeudCellule(null, 0);
		this.racine = this.chercherNoeud(0);
		this.raf = null;
	}

	// méthodes spécifiques

	public void ajouterStagiaire(Stagiaire nouveauStagiaire) {
		// Chercher le nœud racine
		this.racine = this.chercherNoeud(0);
		try {
			// Ouvrir le fichier en mode lecture/écriture
			raf = new RandomAccessFile(ADDRESSE_FICHIER_BINAIRE, "rw");

			// Appeler la méthode d'ajout de nœud
			racine.ajouterNoeud(raf, nouveauStagiaire, racine);

			// Fermer le fichier
			raf.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public NoeudCellule chercherNoeud(int index) {
		try {
			// Ouvrir le fichier en mode lecture/écriture
			raf = new RandomAccessFile(ADDRESSE_FICHIER_BINAIRE, "rw");

			// Appeler la méthode de recherche de nœud par index
			NoeudCellule resultat = racine.chercherNoeudParIndex(raf, index);

			// Fermer le fichier
			raf.close();

			return resultat;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public List<NoeudCellule> recupererTousLesNoeuds() {
		try {
			// Ouvrir le fichier en mode lecture/écriture
			raf = new RandomAccessFile(ADDRESSE_FICHIER_BINAIRE, "rw");

			// Créer une liste pour stocker les nœuds
			List<NoeudCellule> listeNoeuds = new ArrayList<>();

			// Appeler la méthode de récupération de tous les nœuds sur la racine
			racine.recupererTousLesNoeuds(raf, listeNoeuds);

			// Fermer le fichier
			raf.close();

			return listeNoeuds;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public void supprimerStagiaire(Stagiaire stagiaireCible) {
		try {
			// Ouvrir le fichier en mode lecture/écriture
			raf = new RandomAccessFile(ADDRESSE_FICHIER_BINAIRE, "rw");

			// Appeler la méthode récursive de suppression de stagiaire sur la racine
			racine.supprimerStagiaire(raf, stagiaireCible);

			// Fermer le fichier
			raf.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public NoeudCellule getRacine() {
		return racine;
	}

	public void setRacine(NoeudCellule racine) {
		this.racine = racine;
	}

	public RandomAccessFile getRaf() {
		return raf;
	}

	public void setRaf(RandomAccessFile raf) {
		this.raf = raf;
	}

	public static String getADDRESSE_FICHIER_BINAIRE() {
		return ADDRESSE_FICHIER_BINAIRE;
	}

	public static void setADDRESSE_FICHIER_BINAIRE(String aDDRESSE_FICHIER_BINAIRE) {
		ADDRESSE_FICHIER_BINAIRE = aDDRESSE_FICHIER_BINAIRE;
	}

}
