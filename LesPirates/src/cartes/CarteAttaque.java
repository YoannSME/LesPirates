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
		affichage.afficherAttaquePirate(attaquant.toString(), victime.getNom());
		victime.perdreVie(nbDegats);
		affichage.afficherPerdreVie(victime.toString(), nbDegats, victime.getPV());
		victime.getZoneAttaque().ajouterCarteAvecEcrasement(this);
	}

}
