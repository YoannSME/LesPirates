package cartes;

public class CarteVole extends Carte {
	//Vole N cartes du joueur adverse
	private int nbCartesVolables;
	public CarteVole(TypeCarte typeCarte,int nbCartesVolables) {
		super(typeCarte);
		this.nbCartesVolables = nbCartesVolables;
	}

}
