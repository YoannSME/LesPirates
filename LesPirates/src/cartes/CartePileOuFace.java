package cartes;


import pirate.Pirate;

public class CartePileOuFace extends Carte {
	private int probabilite;

	public CartePileOuFace(TypeCarte typeCarte, String description, int probabilite) {
		super(typeCarte, description);
		this.probabilite = probabilite;
		
	}

	public int getProbabilite() {
		return probabilite;
	}

	@Override
	public void effetCarte(Pirate attaquant, Pirate victime) {
		affichage.afficherEffetCartePileOuFace(attaquant.getNom());
		if (random.nextInt(probabilite) != 0) {
			int pvAdverse = victime.getPV();
			victime.perdreVie(pvAdverse);
			affichage.afficherPerdreVie(victime.toString(), pvAdverse, 0);
		} else {
			int pvAttaquant = attaquant.getPV();
			attaquant.perdreVie(pvAttaquant);
			affichage.afficherPerdreVie(attaquant.toString(), pvAttaquant, 0);
		}

	}

}
