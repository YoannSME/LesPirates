package cartes;

import pioche.Pioche;
import pirate.Pirate;

public class CarteSwitch extends Carte {

	public CarteSwitch(TypeCarte typeCarte, String description) {
		super(typeCarte, description);
	}

	@Override
	public void effetCarte(Pirate attaquant, Pirate victime) {
		int pvAttaquant = attaquant.getPV();
		int populariteAttaquant = attaquant.getPopularite();
		Pioche mainAttaquant = attaquant.getMain();
		Pioche zoneAttaque = attaquant.getZoneAttaque();
		Pioche zonePopularite = attaquant.getZonePopularite();
		
		attaquant.setPv(victime.getPV());
		attaquant.setPopularite(victime.getPopularite());
		attaquant.setMain(victime.getMain());
		attaquant.setZoneAttaque(victime.getZoneAttaque());
		attaquant.setZonePopularite(victime.getZonePopularite());
		
		victime.setPv(pvAttaquant);
		victime.setPopularite(populariteAttaquant);
		victime.setMain(mainAttaquant);
		victime.setZoneAttaque(zoneAttaque);
		victime.setZonePopularite(zonePopularite);
		
		affichage.afficherEffetCarteSwitch(attaquant.toString(),victime.toString());
		
		
		
	}

}
