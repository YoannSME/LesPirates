package cartes;

import pirate.Pirate;

public class CartePopularite extends Carte {
	private int pointsPopularite;
	private int nbDegats;

	public CartePopularite(TypeCarte typeCarte, int nbDegats, int pointsPopularite, String description) {
		super(typeCarte, description);
		this.pointsPopularite = pointsPopularite;
		this.nbDegats = nbDegats;
	}

	public int getNbPopularite() {
		return pointsPopularite;
	}

	public int getNbDegats() {
		return nbDegats;
	}

	@Override
	public void effetCarte(Pirate attaquant, Pirate victime) {
		attaquant.gagnerPopularite(pointsPopularite);
		attaquant.perdrePopularite(nbDegats);
		affichage.afficherEffetCartePopularite(attaquant.getNom(), pointsPopularite, nbDegats,
				attaquant.getPopularite(), attaquant.getPV());
		attaquant.getZonePopularite().ajouterCarte(this);
	}

}
