package fr.isika.cda25.projet1.model;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class Annuaire {

	// attributs

	private NoeudCellule racine;
	private RandomAccessFile raf;
	// constructeurs

	public Annuaire() {
		this.racine = new NoeudCellule(null, 0);
		this.racine = this.chercherNoeud(0);
		this.raf = null;
	}

	// méthodes spécifiques

	public void ajouterStagiaire(Stagiaire nouveauStagiaire) {

		try {

			raf = new RandomAccessFile("src/main/java/fr/isika/cda25/projet1/files/stagiaires.bin", "rw");
			// this.racine = this.chercherNoeud(0);
			racine.ajouterStagiaire(raf, nouveauStagiaire, racine);

			raf.close();

		} catch (

		IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public NoeudCellule chercherNoeud(int index) {

		try {
			raf = new RandomAccessFile("src/main/java/fr/isika/cda25/projet1/files/stagiaires.bin", "rw");
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
		return null; // pour ne pax vexer Eclipse
	}
	
	

	public NoeudCellule chercherStagiaireParNom(String nom) {

		try {
			raf = new RandomAccessFile("src/main/java/fr/isika/cda25/projet1/files/stagiaires.bin", "rw");
			NoeudCellule resultat = racine.chercherNoeudParNom(raf, nom);
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
		return null; // pour ne pax vexer Eclipse

	}

	public NoeudCellule chercherStagiaireParPrenom(String prenom) {

		try {
			raf = new RandomAccessFile("src/main/java/fr/isika/cda25/projet1/files/stagiaires.bin", "rw");
			NoeudCellule resultat = racine.chercherNoeudParPrenom(raf, prenom);
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
		return null; // pour ne pax vexer Eclipse

	}

	public NoeudCellule chercherStagiaireParDepartement(String departement) {

		try {
			raf = new RandomAccessFile("src/main/java/fr/isika/cda25/projet1/files/stagiaires.bin", "rw");
			NoeudCellule resultat = racine.chercherNoeudParDepartement(raf, departement);
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
		return null; // pour ne pax vexer Eclipse

	}

	public NoeudCellule chercherStagiaireParFormation(String formation) {

		try {
			raf = new RandomAccessFile("src/main/java/fr/isika/cda25/projet1/files/stagiaires.bin", "rw");
			NoeudCellule resultat = racine.chercherNoeudParFormation(raf, formation);
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
		return null; // pour ne pax vexer Eclipse

	}

	public NoeudCellule chercherStagiaireParAnnee(int annee) {

		try {
			raf = new RandomAccessFile("src/main/java/fr/isika/cda25/projet1/files/stagiaires.bin", "rw");
			NoeudCellule resultat = racine.chercherNoeudParAnnee(raf, annee);
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
		return null; // pour ne pax vexer Eclipse

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

//	public void ajouterStagiaireAnnuaire(Stagiaire nouveauStagiaire) {
//		if (racine != null) {
//			racine.ajouterStagiaire(nouveauStagiaire);
//		}
//	}

	// getters and setters

}
