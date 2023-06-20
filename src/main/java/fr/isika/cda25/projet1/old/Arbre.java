package fr.isika.cda25.projet1.old;

import fr.isika.cda25.projet1.model.Stagiaire;

public class Arbre {

	// attributs

	private Noeud racine;

	// constructeurs

	public Arbre(Noeud racine) {
		this.racine = racine;
	}

	public Arbre() {
		this.racine = null;
	}

	// méthodes spécifiques


	public void ajouterStagiaire(Stagiaire nouveauStagiaire) {
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
