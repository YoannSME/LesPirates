package cartes;

public class CarteAttaque extends Carte {
	private int nbDegats;
	public CarteAttaque(TypeCarte typeCarte, int nbDegats,String description) {
		super(typeCarte, description);
		this.nbDegats = nbDegats;

	}
	
	public int getNbDegats() {
		return nbDegats;
	}

}
