package cartes;

public class CarteAttaque extends Carte {
	private int nbDegats;
	public CarteAttaque(TypeAttaque typeCarte, int nbDegats) {
		super(typeCarte);
		this.nbDegats = nbDegats;

	}
	
	public int getNbDegats() {
		return nbDegats;
	}

}
