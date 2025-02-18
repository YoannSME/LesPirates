package cartes;

public class CartePopularite extends Carte {
	private int pointsPopularite;
	private int nbDegats;
	public CartePopularite(TypeCarte typeCarte,int nbDegats, int pointsPopularite,String description) {
		super(typeCarte,description);
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
