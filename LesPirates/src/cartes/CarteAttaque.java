package cartes;

import pirate.Pirate;

public class CarteAttaque extends Carte {
	private int nbDegats;

	public CarteAttaque(TypeCarte typeCarte, int nbDegats, String description) {
		super(typeCarte, description);
		this.nbDegats = nbDegats;
	}

	public int getNbDegats() {
		return nbDegats;
	}

	@Override
	public void effetCarte(Pirate attaquant, Pirate victime) {
		affichage.afficherAttaquePirate(attaquant.getNom(), victime.getNom());
		victime.perdreVie(nbDegats);
		affichage.afficherPerdreVie(victime.getNom(), nbDegats, victime.getPV());
		victime.getZoneAttaque().ajouterCarte(this);
	}

}
