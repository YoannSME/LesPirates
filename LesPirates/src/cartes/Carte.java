package cartes;

import java.security.SecureRandom;
import java.util.Random;

import affichage.IAffichage;
import jeu.Jeu;
import pirate.Pirate;

public abstract class Carte {
	protected static final IAffichage affichage = Jeu.getAffichage();
	protected TypeCarte typeCarte;
	protected String description;
	protected Random random;

	protected Carte(TypeCarte typeCarte, String description) {
		this.typeCarte = typeCarte;
		this.description = description;
		try {
			random = SecureRandom.getInstanceStrong();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public TypeCarte getType() {
		return typeCarte;
	}

	public String getDescription() {
		return description;
	}

	public abstract void effetCarte(Pirate attaquant, Pirate victime);

}
