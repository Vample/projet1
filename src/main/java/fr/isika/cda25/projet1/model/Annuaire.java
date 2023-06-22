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
	
	public Annuaire (String adresseRaf) {
		setADDRESSE_FICHIER_BINAIRE(adresseRaf);
		this.racine = new NoeudCellule(null, 0);
		this.racine = this.chercherNoeud(0);
		this.raf = null;
	}

	// méthodes spécifiques

	public void ajouterStagiaire(Stagiaire nouveauStagiaire) {
		
		this.racine = this.chercherNoeud(0);
		try {

			raf = new RandomAccessFile(ADDRESSE_FICHIER_BINAIRE, "rw");
			
			racine.ajouterNoeud(raf, nouveauStagiaire, racine);

			raf.close();

		} catch (

		IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public NoeudCellule chercherNoeud(int index) {

		try {
			raf = new RandomAccessFile(ADDRESSE_FICHIER_BINAIRE, "rw");
			NoeudCellule resultat = racine.chercherNoeudParIndex(raf, index);
			raf.close();
			return resultat;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public List<NoeudCellule> recupererTousLesNoeuds() {
		try {
			raf = new RandomAccessFile(ADDRESSE_FICHIER_BINAIRE, "rw");
			List<NoeudCellule> listeNoeuds = new ArrayList<>();
			racine.recupererTousLesNoeuds(raf, listeNoeuds);
			raf.close();

			return listeNoeuds;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public List<NoeudCellule> chercherNoeudsParNom(String nom) {

		try {
			raf = new RandomAccessFile(ADDRESSE_FICHIER_BINAIRE, "rw");
			List<NoeudCellule> listeNoeuds = new ArrayList<>();
			racine.chercherNoeudParNom(raf, nom, listeNoeuds);
			raf.close();

			return listeNoeuds;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}

	public void supprimerStagiaire(Stagiaire stagiaireCible) {

		try {
			raf = new RandomAccessFile(ADDRESSE_FICHIER_BINAIRE, "rw");

			// on identifie d'abord de quel noeud il s'agit
//			List<NoeudCellule> noeudCible = new ArrayList<>(1); // on fait une liste de capacité 1 (nécessaire car nos
//																// méthodes utilisent des listes)
//
//			racine.chercherNoeudParNom(raf, stagiaireCible.getNom(), noeudCible);
//
//			racine.supprimerNoeud(raf, noeudCible.get(0));

			 racine.supprimerStagiaire(raf,stagiaireCible);

			raf.close();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public List<NoeudCellule> rechercheAvancee(String nom, String prenom, String departement, String formation,
			String annee) {

		try {
			raf = new RandomAccessFile(ADDRESSE_FICHIER_BINAIRE, "rw");
			List<NoeudCellule> listeNoeuds = new ArrayList<>();
			racine.rechercheAvancee(raf, nom, prenom, departement, formation, annee, listeNoeuds);
			raf.close();
			return listeNoeuds;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;

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
