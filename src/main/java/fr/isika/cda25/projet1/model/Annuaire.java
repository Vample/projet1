package fr.isika.cda25.projet1.model;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class Annuaire {

	// attributs

	private NoeudCellule noeudCourant;
	private RandomAccessFile raf;
	// constructeurs

	public Annuaire() {
		this.noeudCourant = null;
	}

	// méthodes spécifiques

	public void ajouterStagiaire(Stagiaire nouveauStagiaire) {

		try {

			raf = new RandomAccessFile("src/main/java/fr/isika/cda25/projet1/files/stagiaires.bin", "rw");

			if (raf.length() == 0) {
				// si le fichier est vide on place le noeud à l'index 0
				noeudCourant = new NoeudCellule(nouveauStagiaire, 0);
			} else { // s'il n'est pas vide on le place à la fin du fichier
				int indexDePlacement;
				indexDePlacement = (int) (raf.length() / Stagiaire.TAILLE_STAGIAIRE_OCTET);
				/*
				 * Ex : si un noeud pèse 100 octets et que notre fichier pèse 100 octets, alors
				 * 100/100 = 1, on place le nouveau noeud à l'index 1 (juste après le racine) si
				 * notre fichier possède 5 noeuds alors 500/100 -> on place le nouveau noeud à
				 * l'index 5 (donc en 6ème position) etc...
				 * 
				 * 
				 */
				this.noeudCourant.setIndexNoeud(indexDePlacement);

				// maintenant on doit déterminer sa position dans l'arbre (= qui sont ses
				// ancêtres)
				// Pour ça on part de la racine :

				//NoeudCellule noeudParent = ???

			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public NoeudCellule getRacine() {

		try {

			if (raf.length() == 0) {
				return null; // S'il n'y a pas de racine (fichier vide)
			} else {
				raf = new RandomAccessFile("src/main/java/fr/isika/cda25/projet1/files/stagiaires.bin", "rw");

				raf.seek(0 * NoeudCellule.TAILLE_NOEUD_OCTET);

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
				System.out.println(anneeRentree);

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
		
		return null; //pour ne pas vexer Eclipse?

	}

//	public void ajouterStagiaireAnnuaire(Stagiaire nouveauStagiaire) {
//		if (racine != null) {
//			racine.ajouterStagiaire(nouveauStagiaire);
//		}
//	}

	// getters and setters

}
