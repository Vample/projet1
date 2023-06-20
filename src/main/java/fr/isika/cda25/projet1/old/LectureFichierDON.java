package fr.isika.cda25.projet1.old;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class LectureFichierDON {

	public static void main(String[] args) {

		try {
			FileReader fr = new FileReader("src/main/java/fr/isika/cda25/projet1/files/STAGIAIRES.DON"); // Flux
																													// fichier
																													// vers
																													// programme
			// BufferedReader : utilise le flux du FileReader et optimise ses méthodes
			BufferedReader br = new BufferedReader(fr);

			String contenu = "";

			while (br.ready()) { // tant que j'ai une ligne dans le fichier
				contenu += br.readLine() + "\n";
			}
			System.out.println(contenu);
			br.close();

			/*
			 * CODE SANS BUFFERED READER -> LABORIEUX String texteFichier = ""; int
			 * unicodeLu = 0;
			 * 
			 * //tant que j'ai un caractère à lire //read() retourne l'uncode du caractère
			 * //elle retourne -1 s'il n'y a rien à lire
			 * 
			 * while ((unicodeLu = fr.read()) != -1) {
			 * 
			 * texteFichier += (char)unicodeLu; }
			 * 
			 * System.out.println(texteFichier);
			 */

			fr.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
