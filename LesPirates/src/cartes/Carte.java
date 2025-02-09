package cartes;

public abstract class Carte {
	private TypeCarte typeCarte;
	private int nbDegats;

	protected Carte(TypeCarte typeCarte, int nbDegats) {
		this.typeCarte = typeCarte;
		this.nbDegats = nbDegats;
	}

	public TypeCarte getType() {
		return typeCarte;
	}

	public int getNbDegats() {
		return nbDegats;
	}

}
