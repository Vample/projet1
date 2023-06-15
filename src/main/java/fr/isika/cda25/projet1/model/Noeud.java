package fr.isika.cda25.projet1.model;

public class Noeud {

	// attributs

	public Stagiaire cle;
	public Noeud filsGauche;
	public Noeud filsDroit;

	// constructeurs

	public Noeud(Stagiaire cle) {
		this.cle = cle;
	}

	public Noeud(Stagiaire cle, Noeud filsGauche, Noeud filsDroit) {
		super();
		this.cle = cle;
		this.filsGauche = filsGauche;
		this.filsDroit = filsDroit;
	}

	// méthodes spécifiques

	public String toString() {

		String res = "";

		if (filsGauche != null) {
			res += filsGauche.toString(); // G
		}
		res += cle.toString(); // N

		if (filsDroit != null) {
			res += filsDroit.toString(); // D
		}
		return res; // affichage infixe (Gauche-Noeud-Droit)
	}

	public void ajouterStagiaire(Stagiaire nouveauStagiaire) {
		if (this.cle.compareTo(nouveauStagiaire) < 0) { // Nouveau stagiaire plus grand que le stagiaire du noeud (on regarde
													// à droite)
			if (filsDroit == null) {
				filsDroit = new Noeud(nouveauStagiaire); // cas terminaison
			} else {
				filsDroit.ajouterStagiaire(nouveauStagiaire); // cas continu
			}

		} else {
			if (filsGauche == null) {
				filsGauche = new Noeud(nouveauStagiaire); // cas terminaison
			} else {
				filsGauche.ajouterStagiaire(nouveauStagiaire); // cas continu
			}
		}
	}

	// faire la méthode de recherche qui cherche dans l'arbre le noeud contenant un
	// stagiaire ayant le meme prénom que la chaine de caractère passée en paramètre
	// et null si le stagiaire n'est pas dans l'arbre.

//	-faire une méthode récursive qui retourne le nombre de noeud d'un arbre.

//	-faire une méthode qui retourne la hauteur de l'arbre

	public Noeud rechercherStagiaire(String prenomDuStagiaire) {

		// Le Noeud regarde si le stagiaire a le prénom recherché
		// Si oui, il se return lui-même
		// Si non, il demande à ses enfants en commençant par celui de gauche
		// Si pas d'enfants et pas le bon stagiaire, return null

		Noeud resul;

		// cas terminaison 1

		if (this.getCle().getPrenom().compareTo(prenomDuStagiaire) ==0) { // si on trouve le prénom
			return this; // on remonte le noeud qui le contient
		}

		// cas continu

		if (this.filsGauche != null) { // Si le noeud a un fils gauche

			resul = this.filsGauche.rechercherStagiaire(prenomDuStagiaire); // on lui passe la demande

			if (resul != null) { // s'il a trouvé le stagiaire, on le remonte
				return resul;
			}
		}

		if (this.filsDroit != null) { // Si le noeud a un fils droit

			resul = this.filsDroit.rechercherStagiaire(prenomDuStagiaire);
			if (resul != null) { // s'il a trouvé le stagiaire, on le remonte
				return resul;
			}

		}

		// cas terminaison 2
		return null; // si stagiaire non trouvé et pas d'enfant, on return null par défaut
	}

	public int nombredeNoeuds() {
		int nombre = 1;

		// cas continu
		if (this.filsGauche != null) { // Si le noeud a un fils gauche

			nombre += filsGauche.nombredeNoeuds();
		}

		if (this.filsDroit != null) { // Si le noeud a un fils droit

			nombre += filsDroit.nombredeNoeuds();
		}

		// cas terminaison
		return nombre;

	}

	public int hauteur() {
		int hauteurDroite = 0;
		int hauteurGauche = 0;

		if (this.filsGauche != null) { // Si le noeud a un fils gauche

			hauteurGauche += filsGauche.hauteur() + 1; //on incrémente la hauteurGauche puis on passe la demande

		}

		if (this.filsDroit != null) { // Si le noeud a un fils droit
			hauteurDroite += filsDroit.hauteur() + 1; //on incrémente la hauteurDroite puis on passe la demande

		}

		if (hauteurGauche >= hauteurDroite) {
			return hauteurGauche;
		}
		return hauteurDroite;
	}

	// getters and setters

	public Stagiaire getCle() {
		return cle;
	}

	public void setCle(Stagiaire cle) {
		this.cle = cle;
	}

	public Noeud getFilsGauche() {
		return filsGauche;
	}

	public void setFilsGauche(Noeud filsGauche) {
		this.filsGauche = filsGauche;
	}

	public Noeud getFilsDroit() {
		return filsDroit;
	}

	public void setFilsDroit(Noeud filsDroit) {
		this.filsDroit = filsDroit;
	}

}
