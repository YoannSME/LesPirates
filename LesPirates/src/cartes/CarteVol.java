package cartes;

import pirate.Pirate;

public class CarteVol extends Carte {
	private int nbCartesVolables;

	public CarteVol(TypeCarte typeCarte, int nbCartesVolables, String description) {
		super(typeCarte, description);
		this.nbCartesVolables = nbCartesVolables;
	}

	public int getNbCartesVolables() {
		return nbCartesVolables;
	}

	@Override
	public void effetCarte(Pirate attaquant, Pirate victime) {
		int[] cartesPrises = affichage.recupererCartesVolables(nbCartesVolables, victime.getMain().piocheToString(),
				victime.getNom());
		int[] cartesEchange = affichage.recupererCartesEchangees(nbCartesVolables, attaquant.getMain().piocheToString(), attaquant.getNom(),
				victime.getMain().piocheToString(), cartesPrises);
		for (int i = 0; i < nbCartesVolables; i++) {
			if (cartesEchange[i] != 0) {
				Carte carteVolee = victime.getMain().getCarteAt(cartesPrises[i]);
				Carte carteEchange = attaquant.getMain().getCarteAt(cartesEchange[i] - 1);
				victime.getMain().setCarteAt(carteEchange, cartesPrises[i]);
				attaquant.getMain().setCarteAt(carteVolee, cartesEchange[i] - 1);
			}
		}
		
	}

}
