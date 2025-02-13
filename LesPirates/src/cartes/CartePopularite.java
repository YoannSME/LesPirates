package cartes;

public class CartePopularite extends Carte {
	private int pointsPopularite;
	private int nbDegats;
	public CartePopularite(TypePopularite typeCarte,int nbDegats, int pointsPopularite) {
		super(typeCarte);
		this.pointsPopularite = pointsPopularite;
		this.nbDegats = nbDegats;
	}

	public int getNbPopularite() {
		return pointsPopularite;
	}
	
	public int getNbDegats() {
		return nbDegats;
	}

}
