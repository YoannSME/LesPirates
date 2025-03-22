package cartes;

import pirate.Pirate;

public class CarteRegeneration extends Carte {
	private int pvRecuperees;

	public CarteRegeneration(TypeCarte typeCarte, int pvRecuperees, String description) {
		super(typeCarte, description);
		this.pvRecuperees = pvRecuperees;
	}

	public int getPvRecuperees() {
		return pvRecuperees;
	}

	@Override
	public void effetCarte(Pirate attaquant, Pirate victime) {
		attaquant.gagnerVie(pvRecuperees);
		affichage.afficherEffetCarteRegeneration(attaquant.toString(), pvRecuperees);
	}

}
