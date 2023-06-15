package fr.isika.cda25.projet1.model;

public class Stagiaire {

	// attributs

	public static int TAILLE_NOM_PRENOM = 20;
	public static int TAILLE_DEPARTEMENT = 2;
	public static int TAILLE_FORMATION = 10;
	public static int TAILLE_ANNEE = 4;
	public static int TAILLE_STAGIAIRE_OCTET = 2 * ((2 * TAILLE_NOM_PRENOM) + TAILLE_DEPARTEMENT + TAILLE_FORMATION) + TAILLE_ANNEE;

	private String nom;
	private String prenom;
	private String departement;
	private String formation;
	private int anneeRentree;
	private Stagiaire suivant;

	// constructeur

	public Stagiaire(String nom, String prenom, String departement, String formation, int anneeRentree) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.departement = departement;
		this.formation = formation;
		this.anneeRentree = anneeRentree;
		this.suivant = null;
	}

	// getters & setters

	public String getNom() {
		return nom;
	}

	public String getNomLong() {
		String nomLong = this.nom;

		if (this.nom.length() > TAILLE_NOM_PRENOM) {
			nomLong = this.nom.substring(0, TAILLE_NOM_PRENOM); // si la string parfum est + longue que le max on la
																// coupe
		} else {
			for (int i = nom.length(); i < TAILLE_NOM_PRENOM; i++) {
				nomLong += " "; // si la string parfum est - longue que le max on complète avec des *
			}

		}
		return nomLong;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public String getPrenomLong() {
		String prenomLong = this.prenom;
		if (this.prenom.length() > TAILLE_NOM_PRENOM) {
			prenomLong = this.prenom.substring(0, TAILLE_NOM_PRENOM); // si la string parfum est + longue que le max on la
																	// coupe
		} else {
			for (int i = prenom.length(); i < TAILLE_NOM_PRENOM; i++) {
				prenomLong += " "; // si la string parfum est - longue que le max on complète avec des *
			}
		}
		return prenomLong;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getDepartement() {
		return departement;
	}
	
	public String getDepartementLong() {
		String departementLong = this.departement;
		if (this.departement.length() > TAILLE_DEPARTEMENT) {
			departementLong = this.departement.substring(0, TAILLE_DEPARTEMENT); // si la string parfum est + longue que le max on la
																	// coupe
		} else {
			for (int i = this.departement.length(); i < TAILLE_DEPARTEMENT; i++) {
				departementLong += " "; // si la string parfum est - longue que le max on complète avec des *
			}
		}
		return departementLong;
	}

	public void setDepartement(String departement) {
		this.departement = departement;
	}

	public String getFormation() {
		return formation;
	}

	public String getFormationLong() {
		String formationLong = this.formation;
		if (this.formation.length() > TAILLE_FORMATION) {
			formationLong = this.formation.substring(0, TAILLE_FORMATION); // si la string parfum est + longue que le
																			// max on la
			// coupe
		} else {
			for (int i = formation.length(); i < TAILLE_FORMATION; i++) {
				formationLong += " "; // si la string parfum est - longue que le max on complète avec des *
			}
		}
		return formationLong;
	}

	public void setFormation(String formation) {
		this.formation = formation;
	}

	public int getAnneeRentree() {
		return anneeRentree;
	}

	public void setAnneeRentree(int anneeRentree) {
		this.anneeRentree = anneeRentree;
	}

	public Stagiaire getSuivant() {
		return suivant;
	}

	public void setSuivant(Stagiaire suivant) {
		this.suivant = suivant;
	}

	// méthodes spécifiques

	@Override
	public String toString() {
		return nom.toUpperCase() + "\n" + prenom + "\n" + departement + "\n" + formation + "\n" + anneeRentree + "\n\n";
	}

	// NE FAIRE LA COMPARAISON QUE SUR LE NOM
	public int compareTo(Stagiaire stag) {
		return this.getNom().compareTo(stag.getNom());
	}

}
