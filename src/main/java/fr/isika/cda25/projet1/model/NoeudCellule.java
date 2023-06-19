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

	public void ajouterStagiaire(RandomAccessFile raf, Stagiaire nouveauStagiaire, NoeudCellule noeudCourant)
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
					ajouterStagiaire(raf, nouveauStagiaire, noeudSuivant); // on lui passe le cas. S'il a lui-même déjà
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
					ajouterStagiaire(raf, nouveauStagiaire, nouveauNoeud);
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
					ajouterStagiaire(raf, nouveauStagiaire, noeudSuivant); // on lui passe le cas. S'il a lui-même déjà
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
					ajouterStagiaire(raf, nouveauStagiaire, nouveauNoeud);
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
					ajouterStagiaire(raf, nouveauStagiaire, noeudSuivant); // on lui passe le cas. S'il a lui-même déjà
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
					ajouterStagiaire(raf, nouveauStagiaire, nouveauNoeud);
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
//				System.out.println(nom);

				String prenom = "";
				for (int i = 0; i < Stagiaire.TAILLE_NOM_PRENOM; i++) {
					prenom += raf.readChar();
				}
//				System.out.println(prenom);

				String dep = "";
				for (int i = 0; i < Stagiaire.TAILLE_DEPARTEMENT; i++) {
					dep += raf.readChar();
				}
//				System.out.println(dep);

				String formation = "";
				for (int i = 0; i < Stagiaire.TAILLE_FORMATION; i++) {
					formation += raf.readChar();
				}
//				System.out.println(formation);

				int anneeRentree = raf.readInt();
//				System.out.println(anneeRentree);

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

		return null; // pour ne pas vexer Eclipse?

	}

	public void chercherNoeudParAnnee(RandomAccessFile raf, String annee, List<NoeudCellule> listeNoeuds) {
		// TODO Auto-generated method stub

	}

	public void chercherNoeudParFormation(RandomAccessFile raf, String formation, List<NoeudCellule> listeNoeuds) {
		// TODO Auto-generated method stub

	}

	public void chercherNoeudParDepartement(RandomAccessFile raf, String departement, List<NoeudCellule> listeNoeuds) {
		// TODO Auto-generated method stub

	}

	public void chercherNoeudParPrenom(RandomAccessFile raf, String prenom, List<NoeudCellule> listeNoeuds) {

		// Pour chaque noeud (en suivant l'ordre infixe) on regarde si le prenom
		// correspond. Si oui, on ajoute le noeud à la liste.

		if (this.getIndexFilsGauche() != -1) {
			this.chercherNoeudParIndex(raf, this.indexFilsGauche).chercherNoeudParPrenom(raf, prenom, listeNoeuds);
		}

		if (this.getIndexDoublon() != -1) {
			this.chercherNoeudParIndex(raf, this.indexDoublon).chercherNoeudParPrenom(raf, prenom, listeNoeuds);
		}

		if (this.getCle().getPrenom().trim().compareTo(prenom) == 0) {
			listeNoeuds.add(this);
		}

		if (this.getIndexFilsDroit() != -1) {
			this.chercherNoeudParIndex(raf, this.indexFilsDroit).chercherNoeudParPrenom(raf, prenom, listeNoeuds);
		}

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

	public void rechercheAvancee(RandomAccessFile raf, String nom, String prenom, String departement, String formation,
			String annee, List<NoeudCellule> listeNoeuds) {
		
		
		
		

		if (nom.compareTo("") != 0) { // si le nom n'est pas vide
			this.chercherNoeudParNom(raf, nom, listeNoeuds);
		}

		if (prenom.compareTo("") != 0) { // si le prenom n'est pas vide
			this.chercherNoeudParPrenom(raf, prenom, listeNoeuds);
		}

		if (departement.compareTo("") != 0) { // si le departement n'est pas vide
			this.chercherNoeudParDepartement(raf, departement, listeNoeuds);
		}

		if (formation.compareTo("") != 0) { // si la formation n'est pas vide
			this.chercherNoeudParFormation(raf, formation, listeNoeuds);
		}

		if (annee.compareTo("") != 0) { // si l'année n'est pas vide
			this.chercherNoeudParAnnee(raf, annee, listeNoeuds);
		}

		if (listeNoeuds.isEmpty()) { // si aucun champs de recherhe n'est remplis, on considère qu'il s'agit d'un
										// retour à l'affichagr par défaut
			this.recupererTousLesNoeuds(raf, listeNoeuds);
		}

	}

	public void recupererTousLesNoeuds(RandomAccessFile raf, List<NoeudCellule> listeNoeuds) {
		// TODO Auto-generated method stub

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
}

//public void ajouterStagiaire(RandomAccessFile raf, Stagiaire nouveauStagiaire, NoeudCellule noeudCourant)
//throws IOException {
//
//if (raf.length()==noeudCourant.getIndexNoeud()*NoeudCellule.TAILLE_NOEUD_OCTET) { // si on arrive à un emplacement vide
//
//ecrireNoeud(raf, noeudCourant, nouveauStagiaire, noeudCourant.getIndexNoeud()); //on écrit
//
//} else {
//
//// si le noeudCourant contient un doublon du nouveauStagiaire
//if (noeudCourant.getCle().compareTo(nouveauStagiaire) == 0) {
//	// si le noeudCourant a déjà un doublon
//	if (noeudCourant.getIndexDoublon() != -1) {
//		// on lui passe le flambeau
//		NoeudCellule noeudSuivant = this.chercherNoeud(raf, indexDoublon);
//		ajouterStagiaire(raf, nouveauStagiaire, noeudSuivant);
//	} else {
//		// sinon on l'ajoute et on donne son index à notre noeudCourant
//		noeudCourant.setIndexDoublon((int) (raf.length() / NoeudCellule.TAILLE_NOEUD_OCTET));
//		NoeudCellule nouveauNoeud = new NoeudCellule(nouveauStagiaire, this.indexDoublon);
//		
//		ecrireNoeud(raf, nouveauNoeud, nouveauStagiaire, (int) (raf.length() / NoeudCellule.TAILLE_NOEUD_OCTET)); 
//		ecrireNoeud(raf, noeudCourant,noeudCourant.getCle(),noeudCourant.indexNoeud); //pour l'instant on réécrit entièrement le noeud pour maj son index (à modifier)
//	}
//	// sinon, si notre stagiaire doit aller à gauche de notre noeudCourant
//} else if (noeudCourant.getCle().compareTo(nouveauStagiaire) > 0) {
//
//	if (noeudCourant.getIndexFilsGauche() != -1) {
//		// on lui passe le flambeau
//
//		NoeudCellule noeudSuivant = new NoeudCellule(nouveauStagiaire, this.indexFilsGauche);
//		ajouterStagiaire(raf, noeudSuivant.getCle(), noeudSuivant);
//
//	} else {
//		// sinon on l'ajoute et on donne son index à notre noeudCourant
//		noeudCourant.setIndexFilsGauche((int) (raf.length() / NoeudCellule.TAILLE_NOEUD_OCTET));
//		NoeudCellule nouveauNoeud = new NoeudCellule(nouveauStagiaire, this.indexFilsGauche);
//		
//		ecrireNoeud(raf, nouveauNoeud, nouveauStagiaire, (int) (raf.length() / NoeudCellule.TAILLE_NOEUD_OCTET));
//		ecrireNoeud(raf, noeudCourant,noeudCourant.getCle(),noeudCourant.indexNoeud); //pour l'instant on réécrit entièrement le noeud pour maj son index (à modifier)
//	}
//	// sinon, si notre stagiaire doit aller à droite de notre noeudCourant
//} else if (noeudCourant.getCle().compareTo(nouveauStagiaire) < 0) {
//
//	if (noeudCourant.getIndexFilsDroit() != -1) {
//		// on lui passe le flambeau
//
//		NoeudCellule noeudSuivant = new NoeudCellule(nouveauStagiaire, this.indexFilsDroit);
//		ajouterStagiaire(raf, nouveauStagiaire, noeudSuivant);
//
//	} else {
//		// sinon on l'ajoute et on donne son index à notre noeudCourant
//		noeudCourant.setIndexFilsDroit((int) (raf.length() / NoeudCellule.TAILLE_NOEUD_OCTET));
//		NoeudCellule nouveauNoeud = new NoeudCellule(nouveauStagiaire, this.indexFilsDroit);
//		
//		ecrireNoeud(raf, nouveauNoeud, nouveauStagiaire, (int) (raf.length() / NoeudCellule.TAILLE_NOEUD_OCTET));
//		ecrireNoeud(raf,noeudCourant, noeudCourant.getCle(),noeudCourant.indexNoeud); //pour l'instant on réécrit entièrement le noeud pour maj son index (à modifier)
//	}
//
//}
//
///*
// * Ex : si un noeud pèse 100 octets et que notre fichier pèse 100 octets, alors
// * 100/100 = 1, on place le nouveau noeud à l'index 1 (juste après le racine) si
// * notre fichier possède 5 noeuds alors 500/100 -> on place le nouveau noeud à
// * l'index 5 (donc en 6ème position) etc...
// * 
// * 
// */
//}
//}
