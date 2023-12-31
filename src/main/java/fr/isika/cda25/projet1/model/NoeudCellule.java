package fr.isika.cda25.projet1.model;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.List;

import javafx.collections.ObservableList;

public class NoeudCellule {
	public static final int TAILLE_NOEUD_OCTET = Stagiaire.TAILLE_STAGIAIRE_OCTET
			+ 12; /*
					 * Taille d'un stagiaire+12 octets pour les 3 int (indexDoublon,
					 * indexFilsDroit,indexFilsGauche)
					 */

	private Stagiaire cle;
	private int indexNoeud;
	private int indexFilsGauche;
	private int indexFilsDroit;
	private int indexDoublon;

	public NoeudCellule(Stagiaire cle, int index) {
		super();
		this.cle = cle;
		this.indexNoeud = index;
		this.indexFilsGauche = -1;
		this.indexFilsDroit = -1;
		this.indexDoublon = -1;
	}

	public NoeudCellule(Stagiaire cle, int indexNoeud, int indexFilsDroit, int indexFilsGauche, int indexDoublon) {
		super();
		this.cle = cle;
		this.indexNoeud = indexNoeud;
		this.indexFilsDroit = indexFilsDroit;
		this.indexFilsGauche = indexFilsGauche;
		this.indexDoublon = indexDoublon;
	}

	public Stagiaire getCle() {
		return cle;
	}

	public void setCle(Stagiaire cle) {
		this.cle = cle;
	}

	public int getIndexNoeud() {
		return indexNoeud;
	}

	public void setIndexNoeud(int indexNoeud) {
		this.indexNoeud = indexNoeud;
	}

	public int getIndexFilsDroit() {
		return indexFilsDroit;
	}

	public void setIndexFilsDroit(int indexFilsDroit) {
		this.indexFilsDroit = indexFilsDroit;
	}

	public int getIndexFilsGauche() {
		return indexFilsGauche;
	}

	public void setIndexFilsGauche(int indexFilsGauche) {
		this.indexFilsGauche = indexFilsGauche;
	}

	public int getIndexDoublon() {
		return indexDoublon;
	}

	public void setIndexDoublon(int indexDoublon) {
		this.indexDoublon = indexDoublon;
	}

	public void ajouterNoeud(RandomAccessFile raf, Stagiaire nouveauStagiaire, NoeudCellule noeudCourant)
			throws IOException {

		if (raf.length() == noeudCourant.getIndexNoeud() * NoeudCellule.TAILLE_NOEUD_OCTET) { // si on arrive à un
																								// emplacement vide (la
																								// fin du fichier)
			//
			NoeudCellule nouveauNoeud = new NoeudCellule(nouveauStagiaire, noeudCourant.getIndexNoeud());
			ecrireNoeud(raf, nouveauNoeud, nouveauNoeud.getIndexNoeud()); // on écrit (cas
			// terminaison)

		} else {

			// cas doublon

			// si le noeudCourant contient un stagiaire portant le même nom que le
			// nouveauStagiaire
			if (noeudCourant.getCle().compareTo(nouveauStagiaire) == 0) {
				if (noeudCourant.getIndexDoublon() != -1) { // mais qu'il a déjà l'index un doublon

					NoeudCellule noeudSuivant = this.chercherNoeudParIndex(raf, noeudCourant.getIndexDoublon()); // on
																													// recopie
																													// le
																													// noeud
																													// en
					// question
					ajouterNoeud(raf, nouveauStagiaire, noeudSuivant); // on lui passe le cas. S'il a lui-même déjà
																		// l'index d'un doublon, il le passera au
																		// suivant, etc.
				} else { // s'il n'a pas encore l'index d'un doublon
					// on lui donne comme indexDoublon le premier index disponible (c.a.d la fin du
					// fichier)
					noeudCourant.setIndexDoublon((int) (raf.length() / NoeudCellule.TAILLE_NOEUD_OCTET));
					ecrireNoeud(raf, noeudCourant, noeudCourant.getIndexNoeud()); // on le réécrit dans le fichier pour
																					// mettre à jour l'info
					// On créé un nouveau noeud à l'index qu'on vient de déclarer
					NoeudCellule nouveauNoeud = new NoeudCellule(nouveauStagiaire, noeudCourant.getIndexDoublon());
					// On lui passe cette méthode afin d'arriver sur le cas terminaison
					ajouterNoeud(raf, nouveauStagiaire, nouveauNoeud);
				}
				// cas filsGauche
			} else if (noeudCourant.getCle().compareTo(nouveauStagiaire) > 0) { // si le noeudCourant contient un
																				// stagiaire dont le nom "plus grand"
																				// que celui du nouveauStagiaire

				if (noeudCourant.getIndexFilsGauche() != -1) { // mais qu'il a déjà l'index d'un filsGauche

					NoeudCellule noeudSuivant = this.chercherNoeudParIndex(raf, noeudCourant.getIndexFilsGauche()); // on
																													// recopie
																													// le
																													// noeud
																													// en
					// question
					ajouterNoeud(raf, nouveauStagiaire, noeudSuivant); // on lui passe le cas. S'il a lui-même déjà
																		// l'index d'un filsGauche, il le passera au
																		// suivant, etc.
				} else { // s'il n'a pas encore l'index d'un filsGauche
					// on lui donne comme indexFilsGauche le premier index disponible (c.a.d la fin
					// du
					// fichier)
					noeudCourant.setIndexFilsGauche((int) (raf.length() / NoeudCellule.TAILLE_NOEUD_OCTET));
					ecrireNoeud(raf, noeudCourant, noeudCourant.getIndexNoeud()); // on le réécrit dans le fichier pour
																					// mettre à jour l'info
					// On créé un nouveau noeud à l'index qu'on vient de déclarer
					NoeudCellule nouveauNoeud = new NoeudCellule(nouveauStagiaire, noeudCourant.getIndexFilsGauche());
					// On lui passe cette méthode afin d'arriver sur le cas terminaison
					ajouterNoeud(raf, nouveauStagiaire, nouveauNoeud);
				}
				// cas filsDroit
			} else if (noeudCourant.getCle().compareTo(nouveauStagiaire) < 0) { // si le noeudCourant contient un
				// stagiaire dont le nom "plus petit"
				// que celui du nouveauStagiaire

				if (noeudCourant.getIndexFilsDroit() != -1) { // mais qu'il a déjà l'index d'un filsDroit

					NoeudCellule noeudSuivant = this.chercherNoeudParIndex(raf, noeudCourant.getIndexFilsDroit()); // on
																													// recopie
																													// le
																													// noeud
																													// en
					// question
					ajouterNoeud(raf, nouveauStagiaire, noeudSuivant); // on lui passe le cas. S'il a lui-même déjà
																		// l'index d'un filsDroit, il le passera au
																		// suivant, etc.
				} else { // s'il n'a pas encore l'index d'un filsDroit
							// on lui donne comme indexFilsDroit le premier index disponible (c.a.d la fin
							// du
							// fichier)
					noeudCourant.setIndexFilsDroit((int) (raf.length() / NoeudCellule.TAILLE_NOEUD_OCTET));
					ecrireNoeud(raf, noeudCourant, noeudCourant.getIndexNoeud()); // on le réécrit dans le fichier pour
																					// mettre à jour l'info
					// On créé un nouveau noeud à l'index qu'on vient de déclarer
					NoeudCellule nouveauNoeud = new NoeudCellule(nouveauStagiaire, noeudCourant.getIndexFilsDroit());
					// On lui passe cette méthode afin d'arriver sur le cas terminaison
					ajouterNoeud(raf, nouveauStagiaire, nouveauNoeud);
				}
			}

		}
	}

	@Override
	public String toString() {
		return cle.toString() + "FG : " + indexFilsGauche + "\nFD : " + indexFilsDroit + "\nDoublon : " + indexDoublon;
	}

	private void ecrireNoeud(RandomAccessFile raf, NoeudCellule noeud, int index) throws IOException {

		raf.seek(index * NoeudCellule.TAILLE_NOEUD_OCTET);
		// S'il y a un stagiaire dans le fichier, indexNoeud sera = à 1, à 2 s'il y en a
		// 2, etc... (voir NoeudCellule.ajouterStagiaire())

		/*
		 * ENREGISTREMENT INFOS STAGIAIRE
		 */
		Stagiaire stag = noeud.getCle();

//		Ecrire le nom du stagiaire
		raf.writeChars(stag.getNomLong());
//		Ecrire le prénom du stag
		raf.writeChars(stag.getPrenomLong());
//		Ecrire le departement
		raf.writeChars(stag.getDepartementLong());
//		Ecrire la session de formation
		raf.writeChars(stag.getFormationLong());
//		Ecrire l'année de rentrée
		raf.writeInt(stag.getAnneeRentree());

		/*
		 * ENREGISTREMENT INFOS NOEUDCELLULE
		 */

		// Ecrire l'index du fils Gauche
		raf.writeInt(noeud.getIndexFilsGauche());
		// Ecrire l'index du fils Droit
		raf.writeInt(noeud.getIndexFilsDroit());
		// Ecrire l'index du doublon
		raf.writeInt(noeud.getIndexDoublon());

	}

	public NoeudCellule chercherNoeudParIndex(RandomAccessFile raf, int index) {
		//Lit les informations situées à l'index donné, return un noeudCellule correspondant.
		try {

			if (raf.length() == index * NoeudCellule.TAILLE_NOEUD_OCTET) {
				return new NoeudCellule(null, index); // S'il n'y a pas de racine (fichier vide)
			} else {

				raf.seek(index * NoeudCellule.TAILLE_NOEUD_OCTET); // Sinon on cherche et retourne le noeud à
																	// l'emplacement indiqué

				String nom = "";
				for (int i = 0; i < Stagiaire.TAILLE_NOM_PRENOM; i++) {
					nom += raf.readChar();
				}

				String prenom = "";
				for (int i = 0; i < Stagiaire.TAILLE_NOM_PRENOM; i++) {
					prenom += raf.readChar();
				}

				String dep = "";
				for (int i = 0; i < Stagiaire.TAILLE_DEPARTEMENT; i++) {
					dep += raf.readChar();
				}

				String formation = "";
				for (int i = 0; i < Stagiaire.TAILLE_FORMATION; i++) {
					formation += raf.readChar();
				}

				int anneeRentree = raf.readInt();
				int filsGauche = raf.readInt();
				int filsDroit = raf.readInt();
				int doublon = raf.readInt();

				return new NoeudCellule(new Stagiaire(nom, prenom, dep, formation, anneeRentree), index, filsDroit,
						filsGauche, doublon);
			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null; 

	}


	public void chercherNoeudParNom(RandomAccessFile raf, String nom, List<NoeudCellule> listeNoeuds) {
		// TODO Auto-generated method stub

		if (this.getCle().getNom().trim().compareTo(nom) > 0) { // s'il est plus petit

			// si le NoueudCourant a un fils gauche on lui passe la méthode
			if (this.getIndexFilsGauche() != -1) {
				this.chercherNoeudParIndex(raf, this.indexFilsGauche).chercherNoeudParNom(raf, nom, listeNoeuds);
			}

		}

		// si le nom du Noeudcourant = le nom recherché
		if (this.getCle().getNom().trim().compareTo(nom) == 0) {

			// Si le NoeudCourant a un doublon on lui passe la méthode
			if (this.getIndexDoublon() != -1) {
				this.chercherNoeudParIndex(raf, this.indexDoublon).chercherNoeudParNom(raf, nom, listeNoeuds);
			}
			// on ajoute ce noeud au résultat
			listeNoeuds.add(this);
		}
		// si le nom recherché est plus grand que le nom du NoeudCourant
		if (this.getCle().getNom().trim().compareTo(nom) < 0) {
			// si le NoueudCourant a un fils droit on lui passe la méthode
			if (this.getIndexFilsDroit() != -1) {
				this.chercherNoeudParIndex(raf, this.indexFilsDroit).chercherNoeudParNom(raf, nom, listeNoeuds);
			}

		}

	}

	public void recupererTousLesNoeuds(RandomAccessFile raf, List<NoeudCellule> listeNoeuds) {
		// Récupération en lecture infixe de tous les noeudsCellules, les place dans une liste

		// si le NoueudCourant a un fils gauche on lui passe la méthode
		if (this.getIndexFilsGauche() != -1) {
			this.chercherNoeudParIndex(raf, this.indexFilsGauche).recupererTousLesNoeuds(raf, listeNoeuds);
		}

		// on ajoute le noeud au résultat
		listeNoeuds.add(this);

		// Si le NoeudCourant a un doublon on lui passe la méthode
		if (this.getIndexDoublon() != -1) {
			this.chercherNoeudParIndex(raf, this.indexDoublon).recupererTousLesNoeuds(raf, listeNoeuds);
		}

		// si le NoeudCourant a un fils droit on lui passe la méthode
		if (this.getIndexFilsDroit() != -1) {
			this.chercherNoeudParIndex(raf, this.indexFilsDroit).recupererTousLesNoeuds(raf, listeNoeuds);
		}

	}

	public void supprimerStagiaire(RandomAccessFile raf, Stagiaire stagiaireCible) throws IOException {

		if (this.cle.compareTo(stagiaireCible) == 0) {
			if (this.cle.compareStrings(stagiaireCible) == 0) { // vérification pour les doublons
				effacerNoeud(raf, this);
			} else {
				if (this.getIndexDoublon() != -1)
					chercherNoeudParIndex(raf, this.getIndexDoublon()).supprimerStagiaire(raf, stagiaireCible);
			}
		}

		if (this.cle.getNom().compareTo(stagiaireCible.getNom()) > 0) {
			if (this.getIndexFilsGauche() != -1)
				chercherNoeudParIndex(raf, this.getIndexFilsGauche()).supprimerStagiaire(raf, stagiaireCible);
		}

		if (this.cle.getNom().compareTo(stagiaireCible.getNom()) < 0) {
			if (this.getIndexFilsDroit() != -1)
				chercherNoeudParIndex(raf, this.getIndexFilsDroit()).supprimerStagiaire(raf, stagiaireCible);
		}
	}

	private void effacerNoeud(RandomAccessFile raf, NoeudCellule noeudCible) throws IOException {

		// cas pas d'enfant
		if ((noeudCible.getIndexFilsDroit() == -1) && (noeudCible.getIndexFilsGauche() == -1)
				&& (noeudCible.getIndexDoublon() == -1)) {
			chercherParent(raf, noeudCible).modifierEnfant(raf, noeudCible, -1);
		}

		// cas doublon

		if (noeudCible.getIndexDoublon() != -1) {
			// S'il y a un doublon il prend juste la place du noeud supprimé

			// on écrit à l'emplacement du doublon l'adresse des éventuels fils

			int indexDoublonSuivant = chercherNoeudParIndex(raf, noeudCible.getIndexDoublon()).getIndexDoublon();

			NoeudCellule doublon = chercherNoeudParIndex(raf, noeudCible.getIndexDoublon());
			doublon.setIndexFilsDroit(noeudCible.getIndexFilsDroit());
			doublon.setIndexFilsGauche(noeudCible.getIndexFilsGauche());
			doublon.setIndexDoublon(indexDoublonSuivant);
			ecrireNoeud(raf, doublon, noeudCible.getIndexNoeud());

			if (noeudCible.getIndexNoeud() != 0) { // cas particulier racine

				chercherParent(raf, noeudCible).modifierEnfant(raf, noeudCible, noeudCible.getIndexDoublon());

			}

		} else { // s'il n'y a pas de doublon on fait le traitement binaire normal

			// cas 1 enfant (fils droit)

			if ((noeudCible.getIndexFilsDroit() != -1) && (noeudCible.getIndexFilsGauche() == -1)) {
				chercherParent(raf, noeudCible).modifierEnfant(raf, noeudCible, noeudCible.getIndexFilsDroit());
			}

			// cas 1 enfant (fils gauche)
			if ((noeudCible.getIndexFilsDroit() == -1) && (noeudCible.getIndexFilsGauche() != -1)) {
				chercherParent(raf, noeudCible).modifierEnfant(raf, noeudCible, noeudCible.getIndexFilsGauche());
			}

			// cas 2 enfants (fils gauche ET droit)

			if ((noeudCible.getIndexFilsDroit() != -1) && (noeudCible.getIndexFilsGauche() != -1)
					&& (noeudCible.getIndexDoublon() == -1)) {
				if (noeudCible.getIndexNoeud() == 0) { // cas particulier racine

					// le premier fils gauche est réécrit à la position 0
					ecrireNoeud(raf, noeudCible.chercherNoeudParIndex(raf, noeudCible.getIndexFilsGauche()), 0);

					chercherNoeudParIndex(raf, 0).ajouterEnfantRecursivement(raf, noeudCible.getCle(),
							noeudCible.getIndexFilsDroit());

				} else {

//			Le premier fils gauche prend la place du noeud supprimé
					chercherParent(raf, noeudCible).modifierEnfant(raf, noeudCible, noeudCible.getIndexFilsGauche());
					// le fils droit du noeud supprimé est ajouté après le plus lointain fils droit
					// du nouveau noeud (l'ancien fils gauche)

					chercherNoeudParIndex(raf, noeudCible.getIndexFilsGauche()).ajouterEnfantRecursivement(raf,
							noeudCible.getCle(), noeudCible.getIndexFilsDroit());
				}

			}

		}
	}

	private void ajouterEnfantRecursivement(RandomAccessFile raf, Stagiaire stagiaire, int nouvelIndex)
			throws IOException {
		// on essaye de modifier l'index d'un enfant comme dans "modifierEnfant",
		// sauf que si la place est déjà prise on demande à au fils suivant
		//ne traite pas les doublons

		if (this.cle.compareTo(stagiaire) < 0) {

			if (this.indexFilsDroit == -1) {
				this.setIndexFilsDroit(nouvelIndex);
				ecrireNoeud(raf, this, this.indexNoeud);
			} else {
				chercherNoeudParIndex(raf, this.indexFilsDroit).ajouterEnfantRecursivement(raf, stagiaire, nouvelIndex);
			}
		}

		if (this.cle.compareTo(stagiaire) > 0) {

			if (this.indexFilsGauche == -1) {
				this.setIndexFilsGauche(nouvelIndex);
				ecrireNoeud(raf, this, this.indexNoeud);
			} else {
				chercherNoeudParIndex(raf, this.indexFilsGauche).ajouterEnfantRecursivement(raf, stagiaire,
						nouvelIndex);
			}
		}

	}

	private void modifierEnfant(RandomAccessFile raf, NoeudCellule enfant, int nouvelIndex) throws IOException {
		// si le noeudCellule passé en paramètre est un enfant du noeud appelant la méthode, remplace son index
		// par nouvelIndex

		if (this.indexFilsGauche == enfant.getIndexNoeud()) {
			this.setIndexFilsGauche(nouvelIndex);
			ecrireNoeud(raf, this, this.getIndexNoeud());

		}
		if (this.indexFilsDroit == enfant.getIndexNoeud()) {
			this.setIndexFilsDroit(nouvelIndex);
			ecrireNoeud(raf, this, this.getIndexNoeud());

		}
		if (this.indexDoublon == enfant.getIndexNoeud()) {
			this.setIndexDoublon(nouvelIndex);
			ecrireNoeud(raf, this, this.getIndexNoeud());

		}

	}

	private NoeudCellule chercherParent(RandomAccessFile raf, NoeudCellule enfant) {

		// quand le noeud lance lui-même la méthode, this = enfant, donc on doit d'abord
		// lancer la recherche depuis la racine
		if (this.getCle().compareStrings(enfant.getCle()) == 0) {
			return chercherNoeudParIndex(raf, 0).chercherParent(raf, enfant);
		}

		// cas terminaison
		// si l'un des fils est le noueudEnfant, return this

		if (this.indexFilsGauche == enfant.getIndexNoeud() || this.indexFilsDroit == enfant.getIndexNoeud()
				|| this.indexDoublon == enfant.getIndexNoeud()) {
			return this;
		}

		// cas de base
		// si this a un doublon et que this a le même nom que l'enfant
		// on demande au doublon
		if (this.indexDoublon != -1) {
			if (this.getCle().getNom().compareTo(enfant.getCle().getNom()) == 0) {
				return this.chercherNoeudParIndex(raf, this.indexDoublon).chercherParent(raf, enfant);
			}
		}

		// si this a un fils gauche
		// on demande au this gauche
		if (this.indexFilsGauche != -1) {
			NoeudCellule recherche = this.chercherNoeudParIndex(raf, this.indexFilsGauche).chercherParent(raf, enfant);
			if (recherche != null)
				return recherche;
		}

		// si this a un fils droite
		// on demande au fils droite

		if (this.indexFilsDroit != -1) {
			NoeudCellule recherche = this.chercherNoeudParIndex(raf, this.indexFilsDroit).chercherParent(raf, enfant);
			if (recherche != null)
				return recherche;
		}

		return null;

	}

}
