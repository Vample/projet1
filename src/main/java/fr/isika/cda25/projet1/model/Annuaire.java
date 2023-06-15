package fr.isika.cda25.projet1.model;

public class Annuaire {

	// attributs

	private Noeud racine;

	// constructeurs

	public Annuaire(Noeud racine) {
		this.racine = racine;
	}

	public Annuaire() {
		this.racine = null;
	}

	// méthodes spécifiques


	public void ajouterStagiaireAnnuaire(Stagiaire nouveauStagiaire) {
		if (racine != null) {
			racine.ajouterStagiaire(nouveauStagiaire);
		}
	}

	// getters and setters

	public Noeud getRacine() {
		return racine;
	}

	public void setRacine(Noeud racine) {
		this.racine = racine;
	}

}
