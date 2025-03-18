package cartes;

import pirate.Pirate;

public class CarteDenigrement extends Carte {
	private int pointsPopularite;

	public CarteDenigrement(TypeCarte typeCarte, String description, int pointsPopularite) {
		super(typeCarte, description);
		this.pointsPopularite = pointsPopularite;
	}

	public int getPointsPopularite() {
		return pointsPopularite;
	}

	@Override
	public void effetCarte(Pirate attaquant, Pirate victime) {
		victime.perdrePopularite(pointsPopularite);
		affichage.afficherEffetCarteDenigrement(attaquant.getNom(), victime.getNom(), pointsPopularite);
	}

}
