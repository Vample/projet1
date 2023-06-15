package fr.isika.cda25.projet1.program;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.RandomAccessFile;

import fr.isika.cda25.projet1.model.Annuaire;
import fr.isika.cda25.projet1.model.Noeud;
import fr.isika.cda25.projet1.model.Stagiaire;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 * JavaFX App
 */
public class MainCreerFichierBinaire extends Application {

	@Override
	public void start(Stage stage) {

		Annuaire annuaire = new Annuaire();

		try {
			FileReader fr = new FileReader("src/main/java/fr/isika/cda25/projet1/files/STAGIAIRES.DON"); // Flux
																											// fichier
																											// vers
																											// programme
			// BufferedReader : utilise le flux du FileReader et optimise ses méthodes
			BufferedReader br = new BufferedReader(fr);

			//String contenu = "";

			while (br.ready()) { // tant que j'ai une ligne dans le fichier
				// contenu += br.readLine() + "\n";

				String stagNom = br.readLine();
//				System.out.println(stagNom);
				String stagPrenom = br.readLine();
//				System.out.println(stagPrenom);
				String stagDpt = br.readLine();
//				if (stagDpt.compareTo("")==0) {
//					stagDpt = "//";
//				}
//				System.out.println(stagDpt);
				String stagSess = br.readLine();
//				System.out.println(stagSess);
				int stagAnnee = Integer.parseInt(br.readLine());
//				System.out.println(stagAnnee);
				br.readLine(); // drop le *

				if (annuaire.getRacine() == null) {
					annuaire.setRacine(new Noeud(new Stagiaire(stagNom, stagPrenom, stagDpt, stagSess, stagAnnee)));
					// System.out.println(annuaire.getRacine());
				} else {
					annuaire.ajouterStagiaireAnnuaire(new Stagiaire(stagNom, stagPrenom, stagDpt, stagSess, stagAnnee));
				}

			}
			// System.out.println(contenu);
			br.close();

			fr.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}



		System.out.println(annuaire.getRacine().nombredeNoeuds());
		System.out.println(annuaire.getRacine().hauteur() + "\n");

//		System.out.println("Recherche normale :\n"+annuaire.getRacine().rechercherStagiaire("Kévin").getCle());
//		System.out.println("Recherche avancée :");
		//annuaire.getRacine().rechercherStagiairePrenom("adil");
		System.out.println("Departement : "+annuaire.getRacine().rechercherStagiaire("adil").getCle().getDepartement()+"<<<");

		// sauvegarder un arbre binaire sous forme d'un fichier binaire

		try {
			RandomAccessFile raf = new RandomAccessFile("src/main/java/fr/isika/cda25/projet1/files/stagiaires.bin",
					"rw");
			sauvegardeArbre(annuaire.getRacine(), raf);
			System.out.println(
					"Il y a " + raf.length() / Stagiaire.TAILLE_STAGIAIRE_OCTET + " stagiaires dans le fichier");
			rechercherStagiaire(raf);
			raf.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		stage.show();

	}

	public void sauvegardeArbre(Noeud noeud, RandomAccessFile raf) {
		enregistrerStagiaire(noeud.getCle(), raf); // Enregistrer le Stagiaire(clé) du noeud courant

		if (noeud.getFilsGauche() != null) { // Si le noeud courant a un filsGauche alors
			sauvegardeArbre(noeud.getFilsGauche(), raf); // sauvegardeArbre() du filsGauche
		}
		if (noeud.getFilsDroit() != null) { // Si le noeud courant a un filsDroit alors
			sauvegardeArbre(noeud.getFilsDroit(), raf); // sauvegardeArbre() du filsDroit
		}

	}

	public void enregistrerStagiaire(Stagiaire stagiaire, RandomAccessFile raf) {
		try {

//			Ecrire le nom du stagiaire
			raf.writeChars(stagiaire.getNomLong());
//			Ecrire le prénom du stagiaire
			raf.writeChars(stagiaire.getPrenomLong());
//			Ecrire le departement
			raf.writeChars(stagiaire.getDepartementLong());
//			Ecrire la session de formation
			raf.writeChars(stagiaire.getFormationLong());
//			Ecrire l'année de rentrée
			raf.writeInt(stagiaire.getAnneeRentree());

//				raf.writeChars();
//				raf.writeInt();

			// System.out.println(raf.getFilePointer());

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void rechercherStagiaire(RandomAccessFile raf) {

		// je veux lire la troisième tarte, je met le curseur à 2tartes, prêt à lire la
		// 3e

		try {
			raf.seek(99* Stagiaire.TAILLE_STAGIAIRE_OCTET);
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

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Tarte tarte3 = new Tarte(nbPart, parfum.trim()); //trim enlève les espaces
		// (ceux qu'on ajoute dans getParfumLong())

	}

	public static void main(String[] args) {
		launch();
	}

}