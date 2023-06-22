//package fr.isika.cda25.projet1.old;
//
//import javafx.application.Application;
//import javafx.stage.Stage;
//
//public class SuppressionController extends Application{
//
//	public class Stagiaire{
//		
//		int index;
//		String nom;
//		String prenom;
//		String departement;
//		String formation;
//		int anneeRentree;
//		
//
//		public Stagiaire(String nom, String prenom, String departement, String formation, int anneeRentree) {
//			super();
//			this.nom = nom;
//			this.prenom = prenom;
//			this.departement = departement;
//			this.formation = formation;
//			this.anneeRentree = anneeRentree;
//		}
//	}
//	public class NoeudCellule{
//		Stagiaire stagiaire;
//		NoeudCellule left;
//		NoeudCellule right;
//		
//		public NoeudCellule(Stagiaire stagiaire) {
//		this.stagiaire = stagiaire;
//		left = null;
//		right = null;
//		}
//	}
//	public class Arbre{
//		
//		NoeudCellule root;
//		
//		public Arbre() {
//			root = null;
//		}
//		
//	//Pour supprimer un stagiaire ,à l'aide de l'arbre, on utilise la méthode "delete" avec l'identifiant index du stagiaire .
//		//Si le stagiaire est trouvé -->message affiché "supprimé avec succés"; 
//		//sinon, "un message d'erreur " sera affiché.
//	public void delete(int index) {
//		root = deleteRecursive(root,index);
//	}
//	
//	private NoeudCellule deleteRecursive(NoeudCellule current, int index) {
//	
//			if( current == null) {
//				System.out.println(" Le stagiaire est introuvable !");
//				return null;
//			}
//	
//			if ( index == current.stagiaire.index) {
//				//found the NoeudCellule to delete
//				
//				//NoeudCellule has no children
//				if( current.left == null && current.right == null) {
//					System.out.println( "Le stagiaire est supprimé avec succés !");
//					return null;
//				}
//				//NoeudCellule has 1 child
//				if (current.right == null) {
//					System.out.println("Le stagiaire est supprimé avec succés !");
//					return current.left;
//				}
//				if (current.left == null) {
//					System.out.println( " Le stagiaire est supprimé avec succés !");
//					return current.right;
//				}
//				//NoeudCellule has 2 children
//				int smallestld = findSmallestld( current.right);
//				current.stagiaire.index = smallestld;
//				current.right = deleteRecursive(current.right, smallestld);
//				System.out.println( " Le stagiaire est supprimé avec succés !");
//				return current;
//			}
//			if (index < current.stagiaire.index) {
//				current.left = deleteRecursive(current.left, index);
//				return current;
//			}
//	
//			current.right = deleteRecursive(current.right, index);
//			return current;
//}
//	
//	private int findSmallestld(NoeudCellule root) {
//		
//		return root.left == null? root.stagiaire.index:findSmallestld(root.left);
//	}
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	@Override
//	public void start(Stage primaryStage) throws Exception {
//		// TODO Auto-generated method stub
//		
//	}}
