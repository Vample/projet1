package fr.isika.cda25.projet1.program;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import fr.isika.cda25.projet1.model.Annuaire;
import fr.isika.cda25.projet1.model.Noeud;
import fr.isika.cda25.projet1.model.Stagiaire;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;


/**
 * JavaFX App
 */
public class MainCreerFichierBinaire extends Application {

    @Override
    public void start(Stage stage) {
    	
    	Annuaire annuaire= new Annuaire();

		try {
			FileReader fr = new FileReader("src/main/java/fr/isika/cda25/projet1/files/STAGIAIRES.DON"); // Flux
																													// fichier
																													// vers
																													// programme
			// BufferedReader : utilise le flux du FileReader et optimise ses méthodes
			BufferedReader br = new BufferedReader(fr);

			String contenu = "";

			while (br.ready()) { // tant que j'ai une ligne dans le fichier
				//contenu += br.readLine() + "\n";
				
				String stagNom = br.readLine();
//				System.out.println(stagNom);
				String stagPrenom= br.readLine();
//				System.out.println(stagPrenom);
				String stagDpt = br.readLine();
//				System.out.println(stagDpt);
				String stagSess= br.readLine();
//				System.out.println(stagSess);
				int stagAnnee= Integer.parseInt(br.readLine()); 
//				System.out.println(stagAnnee);
				br.readLine(); //drop le *
					
				
				if (annuaire.getRacine()==null) {
					annuaire.setRacine(new Noeud(new Stagiaire(stagNom, stagPrenom, stagDpt, stagSess, stagAnnee)));
					//System.out.println(annuaire.getRacine());
				} else {
					annuaire.ajouterStagiaireAnnuaire(new Stagiaire(stagNom, stagPrenom, stagDpt, stagSess, stagAnnee));
				}
				
			}
			//System.out.println(contenu);
			br.close();

			fr.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		
		
//		monArbre.ajouterStagiaire(new Stagiaire("Fahem", "Imene", "45", "CDA25", 2023));
//		monArbre.ajouterStagiaire(new Stagiaire("Brahmi", "Mohamed", "74", "CDA25", 2023));
//		monArbre.ajouterStagiaire(new Stagiaire("Mebitil", "Ezaiah", "45", "CDA25", 2023));
//		monArbre.ajouterStagiaire(new Stagiaire("Kerbeikian", "Thade", "11", "CDA25", 2023));
//		monArbre.ajouterStagiaire(new Stagiaire("Bouton", "Jean-Baptiste", "12", "CDA25", 2023));
//		monArbre.ajouterStagiaire(new Stagiaire("Alonso", "Miguel", "15", "CDA25", 2023));
		
		
		
		
		
		//System.out.println(annuaire.getRacine().getCle().getNom());
		
		System.out.println(annuaire.getRacine().nombredeNoeuds());
		System.out.println(annuaire.getRacine().hauteur()); 
		
		System.out.println(annuaire.getRacine().rechercherStagiaire("Wafa").getCle());
		
		
		//Créer un arbre binaire à partir de contenu
		

	}


    public static void main(String[] args) {
        launch();
    }

}