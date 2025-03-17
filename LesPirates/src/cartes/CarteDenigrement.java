package cartes;

public class CarteDenigrement extends Carte {
	private int pointsPopularite;

	public CarteDenigrement(TypeCarte typeCarte, String description,int pointsPopularite) {
		super(typeCarte, description);
		this.pointsPopularite = pointsPopularite;
	}
	
	public int getPointsPopularite() {
		return pointsPopularite;
	}

}
