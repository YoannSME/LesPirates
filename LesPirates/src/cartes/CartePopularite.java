package cartes;

import pirate.Pirate;

public class CartePopularite extends Carte {
	private int pointsPopularite;
	
	public CartePopularite(TypePopularite typeCarte,int pointsPopularite) {
		super(typeCarte);
		this.pointsPopularite = pointsPopularite;
	}
	
	public int getNbPopularite() {
		return pointsPopularite;
	}
	
	public void gagnerPopularite(Pirate pirate) {
		pirate.gagnerPopularite(pointsPopularite);
		
	}
	



}
