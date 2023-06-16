package fr.isika.cda25.projet1.model;

public class NoeudCellule {
	public static final int TAILLE_NOEUD_OCTET = Stagiaire.TAILLE_STAGIAIRE_OCTET+12; /*
	Taille d'un stagiaire+12 octets pour les 3 int (indexDoublon, indexFilsDroit,indexFilsGauche)
	*/
	private Stagiaire cle;
	private int indexNoeud;
	private int indexFilsDroit;
	private int indexFilsGauche;
	private int indexDoublon;

	
	
	public NoeudCellule(Stagiaire cle, int index) {
		super();
		this.cle = cle;
		this.indexNoeud = index;
		this.indexFilsDroit = -1;
		this.indexFilsGauche = -1;
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
	
	
	
	

}
