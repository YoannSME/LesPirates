package cartes;

import java.security.SecureRandom;
import java.util.Random;

import pirate.Pirate;

public class CartePileOuFace extends Carte {
	private int probabilite;
	private Random random;

	public CartePileOuFace(TypeCarte typeCarte, String description, int probabilite) {
		super(typeCarte, description);
		this.probabilite = probabilite;
		try {
			random = new SecureRandom().getInstanceStrong();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public int getProbabilite() {
		return probabilite;
	}

	@Override
	public void effetCarte(Pirate attaquant, Pirate victime) {
		affichage.afficherEffetCartePileOuFace(attaquant.getNom());
		if (random.nextInt(probabilite) == 0) {
			int pvAdverse = victime.getPV();
			victime.perdreVie(pvAdverse);
			affichage.afficherPerdreVie(victime.getNom(), pvAdverse, pvAdverse);
		} else {
			int pvAttaquant = attaquant.getPV();
			affichage.afficherPerdreVie(attaquant.getNom(), pvAttaquant, pvAttaquant);
			attaquant.perdreVie(pvAttaquant);
		}

	}

}
