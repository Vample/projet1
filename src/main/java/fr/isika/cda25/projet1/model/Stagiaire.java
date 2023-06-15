package fr.isika.cda25.projet1.model;

public class Stagiaire {

	// attributs

	private String nom;
	private String prenom;
	private String departement;
	private String formation;
	private int anneeRentree;

	// constructeur

	public Stagiaire(String nom, String prenom, String departement, String formation, int anneeRentree) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.departement = departement;
		this.formation = formation;
		this.anneeRentree = anneeRentree;
	}

	// getters & setters

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getDepartement() {
		return departement;
	}

	public void setDepartement(String departement) {
		this.departement = departement;
	}

	public String getFormation() {
		return formation;
	}

	public void setFormation(String formation) {
		this.formation = formation;
	}

	public int getAnnéeRentrée() {
		return anneeRentree;
	}

	public void setAnnéeRentrée(int annéeRentrée) {
		this.anneeRentree = annéeRentrée;
	}

	// méthodes spécifiques

	@Override
	public String toString() {
		return nom.toUpperCase() + "\n" + prenom + "\n" + departement + "\n" + formation + "\n" + anneeRentree + "\n\n";
	}
	
	//NE FAIRE LA COMPARAISON QUE SUR LE NOM
	public int compareTo(Stagiaire stag) {
		return this.getNom().compareTo(stag.getNom());
	}

}
