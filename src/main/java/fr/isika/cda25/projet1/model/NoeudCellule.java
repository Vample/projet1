package fr.isika.cda25.projet1.model;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

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
			ecrireNoeud(raf, noeudCourant, nouveauStagiaire); // on écrit (cas
																// terminaison)

		} else {

			// cas doublon

			// si le noeudCourant contient un stagiaire portant le même nom que le
			// nouveauStagiaire
			if (noeudCourant.getCle().compareTo(nouveauStagiaire) == 0) {
				if (noeudCourant.getIndexDoublon() != -1) { // mais qu'il a déjà l'index un doublon

					NoeudCellule noeudSuivant = this.chercherNoeud(raf, noeudCourant.indexDoublon); // on recopie le noeud en
																						// question
					ajouterStagiaire(raf, nouveauStagiaire, noeudSuivant); // on lui passe le cas. S'il a lui-même déjà
																			// l'index d'un doublon, il le passera au
																			// suivant, etc.
				} else { // s'il n'a pas encore l'index d'un doublon
					// on lui donne comme indexDoublon le premier index disponible (c.a.d la fin du
					// fichier)
					noeudCourant.setIndexDoublon((int) (raf.length() / NoeudCellule.TAILLE_NOEUD_OCTET));
					ecrireNoeud(raf, noeudCourant,noeudCourant.getCle()); //on le réécrit dans le fichier pour mettre à jour l'info
					// On créé un nouveau noeud à l'index qu'on vient de déclarer
					NoeudCellule nouveauNoeud = new NoeudCellule(null, noeudCourant.getIndexDoublon());
					// On lui passe cette méthode afin d'arriver sur le cas terminaison
					ajouterStagiaire(raf, nouveauStagiaire, nouveauNoeud);
				}
				// cas filsGauche
			} else if (noeudCourant.getCle().compareTo(nouveauStagiaire) > 0) { // si le noeudCourant contient un
																				// stagiaire dont le nom "plus grand"
																				// que celui du nouveauStagiaire

				if (noeudCourant.getIndexFilsGauche() != -1) { // mais qu'il a déjà l'index d'un filsGauche

					NoeudCellule noeudSuivant = this.chercherNoeud(raf, noeudCourant.indexFilsGauche); // on recopie le noeud en
																							// question
					ajouterStagiaire(raf, nouveauStagiaire, noeudSuivant); // on lui passe le cas. S'il a lui-même déjà
																			// l'index d'un filsGauche, il le passera au
																			// suivant, etc.
				} else { // s'il n'a pas encore l'index d'un filsGauche
					// on lui donne comme indexFilsGauche le premier index disponible (c.a.d la fin
					// du
					// fichier)
					noeudCourant.setIndexFilsGauche((int) (raf.length() / NoeudCellule.TAILLE_NOEUD_OCTET));
					ecrireNoeud(raf, noeudCourant,noeudCourant.getCle()); //on le réécrit dans le fichier pour mettre à jour l'info
					// On créé un nouveau noeud à l'index qu'on vient de déclarer
					NoeudCellule nouveauNoeud = new NoeudCellule(null, noeudCourant.getIndexFilsGauche());
					// On lui passe cette méthode afin d'arriver sur le cas terminaison
					ajouterStagiaire(raf, nouveauStagiaire, nouveauNoeud);
				}
				// cas filsDroit
			} else if (noeudCourant.getCle().compareTo(nouveauStagiaire) < 0) { // si le noeudCourant contient un
				// stagiaire dont le nom "plus petit"
				// que celui du nouveauStagiaire

				if (noeudCourant.getIndexFilsDroit() != -1) { // mais qu'il a déjà l'index d'un filsDroit

					NoeudCellule noeudSuivant = this.chercherNoeud(raf, indexFilsDroit); // on recopie le noeud en
					// question
					ajouterStagiaire(raf, nouveauStagiaire, noeudSuivant); // on lui passe le cas. S'il a lui-même déjà
																			// l'index d'un filsDroit, il le passera au
																			// suivant, etc.
				} else { // s'il n'a pas encore l'index d'un filsDroit
						// on lui donne comme indexFilsDroit le premier index disponible (c.a.d la fin du
						// fichier)
					noeudCourant.setIndexFilsDroit((int) (raf.length() / NoeudCellule.TAILLE_NOEUD_OCTET));
					ecrireNoeud(raf, noeudCourant,noeudCourant.getCle()); //on le réécrit dans le fichier pour mettre à jour l'info
							// On créé un nouveau noeud à l'index qu'on vient de déclarer
					NoeudCellule nouveauNoeud = new NoeudCellule(null, noeudCourant.getIndexFilsDroit());
							// On lui passe cette méthode afin d'arriver sur le cas terminaison
					ajouterStagiaire(raf, nouveauStagiaire, nouveauNoeud);
				}
			}

		}
	}

//	public void ajouterStagiaire(RandomAccessFile raf, Stagiaire nouveauStagiaire, NoeudCellule noeudCourant)
//			throws IOException {
//
//		if (raf.length()==noeudCourant.getIndexNoeud()*NoeudCellule.TAILLE_NOEUD_OCTET) { // si on arrive à un emplacement vide
//
//			ecrireNoeud(raf, noeudCourant, nouveauStagiaire, noeudCourant.getIndexNoeud()); //on écrit
//
//		} else {
//
//			// si le noeudCourant contient un doublon du nouveauStagiaire
//			if (noeudCourant.getCle().compareTo(nouveauStagiaire) == 0) {
//				// si le noeudCourant a déjà un doublon
//				if (noeudCourant.getIndexDoublon() != -1) {
//					// on lui passe le flambeau
//					NoeudCellule noeudSuivant = this.chercherNoeud(raf, indexDoublon);
//					ajouterStagiaire(raf, nouveauStagiaire, noeudSuivant);
//				} else {
//					// sinon on l'ajoute et on donne son index à notre noeudCourant
//					noeudCourant.setIndexDoublon((int) (raf.length() / NoeudCellule.TAILLE_NOEUD_OCTET));
//					NoeudCellule nouveauNoeud = new NoeudCellule(nouveauStagiaire, this.indexDoublon);
//					
//					ecrireNoeud(raf, nouveauNoeud, nouveauStagiaire, (int) (raf.length() / NoeudCellule.TAILLE_NOEUD_OCTET)); 
//					ecrireNoeud(raf, noeudCourant,noeudCourant.getCle(),noeudCourant.indexNoeud); //pour l'instant on réécrit entièrement le noeud pour maj son index (à modifier)
//				}
//				// sinon, si notre stagiaire doit aller à gauche de notre noeudCourant
//			} else if (noeudCourant.getCle().compareTo(nouveauStagiaire) > 0) {
//
//				if (noeudCourant.getIndexFilsGauche() != -1) {
//					// on lui passe le flambeau
//
//					NoeudCellule noeudSuivant = new NoeudCellule(nouveauStagiaire, this.indexFilsGauche);
//					ajouterStagiaire(raf, noeudSuivant.getCle(), noeudSuivant);
//
//				} else {
//					// sinon on l'ajoute et on donne son index à notre noeudCourant
//					noeudCourant.setIndexFilsGauche((int) (raf.length() / NoeudCellule.TAILLE_NOEUD_OCTET));
//					NoeudCellule nouveauNoeud = new NoeudCellule(nouveauStagiaire, this.indexFilsGauche);
//					
//					ecrireNoeud(raf, nouveauNoeud, nouveauStagiaire, (int) (raf.length() / NoeudCellule.TAILLE_NOEUD_OCTET));
//					ecrireNoeud(raf, noeudCourant,noeudCourant.getCle(),noeudCourant.indexNoeud); //pour l'instant on réécrit entièrement le noeud pour maj son index (à modifier)
//				}
//				// sinon, si notre stagiaire doit aller à droite de notre noeudCourant
//			} else if (noeudCourant.getCle().compareTo(nouveauStagiaire) < 0) {
//
//				if (noeudCourant.getIndexFilsDroit() != -1) {
//					// on lui passe le flambeau
//
//					NoeudCellule noeudSuivant = new NoeudCellule(nouveauStagiaire, this.indexFilsDroit);
//					ajouterStagiaire(raf, nouveauStagiaire, noeudSuivant);
//
//				} else {
//					// sinon on l'ajoute et on donne son index à notre noeudCourant
//					noeudCourant.setIndexFilsDroit((int) (raf.length() / NoeudCellule.TAILLE_NOEUD_OCTET));
//					NoeudCellule nouveauNoeud = new NoeudCellule(nouveauStagiaire, this.indexFilsDroit);
//					
//					ecrireNoeud(raf, nouveauNoeud, nouveauStagiaire, (int) (raf.length() / NoeudCellule.TAILLE_NOEUD_OCTET));
//					ecrireNoeud(raf,noeudCourant, noeudCourant.getCle(),noeudCourant.indexNoeud); //pour l'instant on réécrit entièrement le noeud pour maj son index (à modifier)
//				}
//
//			}
//
//			/*
//			 * Ex : si un noeud pèse 100 octets et que notre fichier pèse 100 octets, alors
//			 * 100/100 = 1, on place le nouveau noeud à l'index 1 (juste après le racine) si
//			 * notre fichier possède 5 noeuds alors 500/100 -> on place le nouveau noeud à
//			 * l'index 5 (donc en 6ème position) etc...
//			 * 
//			 * 
//			 */
//		}
//	}

	private void ecrireNoeud(RandomAccessFile raf, NoeudCellule noeud, Stagiaire stag) throws IOException {

		raf.seek(noeud.getIndexNoeud() * this.TAILLE_NOEUD_OCTET);
		// S'il y a un stagiaire dans le fichier, indexNoeud sera = à 1, à 2 s'il y en a
		// 2, etc... (voir NoeudCellule.ajouterStagiaire())

		/*
		 * ENREGISTREMENT INFOS STAGIAIRE
		 */

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

	public NoeudCellule chercherNoeud(RandomAccessFile raf, int index) {
		try {

			raf = new RandomAccessFile("src/main/java/fr/isika/cda25/projet1/files/stagiaires.bin", "rw");

			if (raf.length() == index * NoeudCellule.TAILLE_NOEUD_OCTET) {
				return new NoeudCellule(null, index); // S'il n'y a pas de racine (fichier vide)
			} else {
				raf = new RandomAccessFile("src/main/java/fr/isika/cda25/projet1/files/stagiaires.bin", "rw");

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

				return new NoeudCellule(new Stagiaire(nom, prenom, dep, formation, anneeRentree), 0, filsDroit,
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

}
