package cartes;

public class CartePopularite extends Carte {
	private int pointsPopularite;

	public CartePopularite(TypePopularite typeCarte,int nbDegats, int pointsPopularite) {
		super(typeCarte, nbDegats);
		this.pointsPopularite = pointsPopularite;
	}

	public int getNbPopularite() {
		return pointsPopularite;
	}

}
