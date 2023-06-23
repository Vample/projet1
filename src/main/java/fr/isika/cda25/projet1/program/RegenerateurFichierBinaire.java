package fr.isika.cda25.projet1.program;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.RandomAccessFile;

import fr.isika.cda25.projet1.model.Annuaire;
import fr.isika.cda25.projet1.model.NoeudCellule;
import fr.isika.cda25.projet1.model.Stagiaire;
import fr.isika.cda25.projet1.modelregenerateur.Noeud;


public class RegenerateurFichierBinaire {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		Annuaire annuaire = new Annuaire("src/main/java/fr/isika/cda25/projet1/files/test.bin");

		/* CODE PERMETTANT DE RECREER LE FICHIER BIN A PARTIR DU FICHIER DON */

		// Arbre monArbre = new Arbre();
		//
		// try {
//			FileReader fr = new FileReader("src/main/java/fr/isika/cda25/projet1/files/STAGIAIRES.DON"); // Flux
//																											// fichier
//																											// vers
//																											// programme
//			// BufferedReader : utilise le flux du FileReader et optimise ses méthodes
//			BufferedReader br = new BufferedReader(fr);
		//
//			// String contenu = "";
		//
//			while (br.ready()) { // tant que j'ai une ligne dans le fichier
//				// contenu += br.readLine() + "\n";
		//
//				String stagNom = br.readLine();
////				System.out.println(stagNom);
//				String stagPrenom = br.readLine();
////				System.out.println(stagPrenom);
//				String stagDpt = br.readLine();
////				if (stagDpt.compareTo("")==0) {
////					stagDpt = "//";
////				}
////				System.out.println(stagDpt);
//				String stagSess = br.readLine();
////				System.out.println(stagSess);
//				int stagAnnee = Integer.parseInt(br.readLine());
////				System.out.println(stagAnnee);
//				br.readLine(); // drop le *
		//
//				if (monArbre.getRacine() == null) {
//					monArbre.setRacine(new Noeud(new Stagiaire(stagNom, stagPrenom, stagDpt, stagSess, stagAnnee)));
//					// System.out.println(monArbre.getRacine());
//				} else {
//					monArbre.ajouterStagiaire(new Stagiaire(stagNom, stagPrenom, stagDpt, stagSess, stagAnnee));
//				}
		//
//			}
//			// System.out.println(contenu);
//			br.close();
		//
//			fr.close();
		// } catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
		// }

	}

	public static void afficherStagiaire(RandomAccessFile raf, int index) {

		// je veux lire la troisième tarte, je met le curseur à 2tartes, prêt à lire la
		// 3e

		try {
			raf.seek(index * NoeudCellule.TAILLE_NOEUD_OCTET);
			// int nbPart = raf.readInt();
			String nom = "";
			for (int i = 0; i < Stagiaire.TAILLE_NOM_PRENOM; i++) {
				nom += raf.readChar();
			}
			System.out.println(nom);

			String prenom = "";
			for (int i = 0; i < Stagiaire.TAILLE_NOM_PRENOM; i++) {
				prenom += raf.readChar();
			}
			System.out.println(prenom);

			String dep = "";
			for (int i = 0; i < Stagiaire.TAILLE_DEPARTEMENT; i++) {
				dep += raf.readChar();
			}
			System.out.println(dep);

			String formation = "";
			for (int i = 0; i < Stagiaire.TAILLE_FORMATION; i++) {
				formation += raf.readChar();
			}
			System.out.println(formation);

			int anneeRentree = raf.readInt();
			System.out.println(anneeRentree);

			System.out.println("FG : " + raf.readInt());
			System.out.println("FD : " + raf.readInt());
			System.out.println("Doublon : " + raf.readInt());

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Tarte tarte3 = new Tarte(nbPart, parfum.trim()); //trim enlève les espaces
		// (ceux qu'on ajoute dans getParfumLong())

	}

	public static void sauvegardeArbre(Noeud noeud, Annuaire annuaire) {
		annuaire.ajouterStagiaire(noeud.getCle()); // Enregistrer le Stagiaire(clé) du noeud courant
		System.out.println("Stagiaire " + noeud.getCle().getNom() + " binairisé !");

		if (noeud.getFilsGauche() != null) { // Si le noeud courant a un filsGauche alors
			sauvegardeArbre(noeud.getFilsGauche(), annuaire); // sauvegardeArbre() du filsGauche
		}
		if (noeud.getFilsDroit() != null) { // Si le noeud courant a un filsDroit alors
			sauvegardeArbre(noeud.getFilsDroit(), annuaire); // sauvegardeArbre() du filsDroit
		}

	}
}
