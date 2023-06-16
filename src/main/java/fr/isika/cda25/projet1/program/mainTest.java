package fr.isika.cda25.projet1.program;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

import fr.isika.cda25.projet1.model.Annuaire;
import fr.isika.cda25.projet1.model.NoeudCellule;
import fr.isika.cda25.projet1.model.Stagiaire;

public class mainTest {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		Annuaire annuaire = new Annuaire();
//		annuaire.ajouterStagiaire(new Stagiaire("Dechaumet", "Lucas","75","CDA25",2023));
//		annuaire.ajouterStagiaire(new Stagiaire("Fahem", "Imene","75","CDA25",2023));
//		annuaire.ajouterStagiaire(new Stagiaire("Brahmi", "Mohamed","75","CDA25",2023));
//		annuaire.ajouterStagiaire(new Stagiaire("Mebitil", "Ezaiah","75","CDA25",2023));
//		annuaire.ajouterStagiaire(new Stagiaire("Kerbeikian", "Thade","75","CDA25",2023));
//		annuaire.ajouterStagiaire(new Stagiaire("Bouton", "Jean-Baptiste","75","CDA25",2023));
//		annuaire.ajouterStagiaire(new Stagiaire("Alonso", "Miguel","75","CDA25",2023));
		
		
		
//		Stagiaire lucas = new Stagiaire("Dechaumet", "Lucas","75","CDA25",2023);
//		
//		System.out.println(lucas.compareTo(new Stagiaire("Brahmi", "Mohamed","75","CDA25",2023)));
		
		
		try {
			annuaire.setRaf(new RandomAccessFile("src/main/java/fr/isika/cda25/projet1/files/stagiaires.bin", "rw"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
		afficherStagiaire(annuaire.getRaf(), 0);
		try {
			annuaire.getRaf().close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public static void afficherStagiaire(RandomAccessFile raf, int index) {

		// je veux lire la troisième tarte, je met le curseur à 2tartes, prêt à lire la
		// 3e

		try {
			raf.seek(index*NoeudCellule.TAILLE_NOEUD_OCTET);
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
			
			System.out.println("FG : "+raf.readInt());
			System.out.println("FD : "+raf.readInt());
			System.out.println("Doublon : "+raf.readInt());

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Tarte tarte3 = new Tarte(nbPart, parfum.trim()); //trim enlève les espaces
		// (ceux qu'on ajoute dans getParfumLong())

	}

}
