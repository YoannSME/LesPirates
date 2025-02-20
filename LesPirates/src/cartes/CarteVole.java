package cartes;

public class CarteVole extends Carte {
	private int nbCartesVolables;

	public CarteVole(TypeCarte typeCarte, int nbCartesVolables, String description) {
		super(typeCarte, description);
		this.nbCartesVolables = nbCartesVolables;
	}

	public int getNbCartesVolables() {
		return nbCartesVolables;
	}

}
