package cartes;

public class CarteVole extends Carte {
	//Vole N cartes du joueur adverse
	private int nbCartesVolables;
	public CarteVole(TypeCarte typeCarte,int nbCartesVolables,String description) {
		super(typeCarte, description);
		this.nbCartesVolables = nbCartesVolables;
	}

}
