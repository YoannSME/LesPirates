package pioche;

import java.security.SecureRandom;
import java.util.Random;

import cartes.Carte;

public class Pioche {
	private Carte[] pioche;
	private int nbCartes = 0;
	private Random random;

	public Pioche(int tailleMax) {
		this.pioche = new Carte[tailleMax];
		try {
			random = new SecureRandom().getInstanceStrong();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Carte[] getPioche() {
		return pioche;
	}

	public int getNbCartes() {
		return nbCartes;
	}

	public String[] piocheToString() {
		String[] retour = new String[nbCartes];
		for (int i = 0; i < nbCartes; i++) {
			retour[i] = "" + pioche[i].getType();
		}
		return retour;
	}

	public Carte getCarteAt(int index) {
		Carte carteRetour = null;
		if (index >= 0 && index < nbCartes) {
			carteRetour = pioche[index];
		}
		return carteRetour;
	}

	public void setCarteAt(Carte carte, int index) {
		if (index >= 0 && index > nbCartes) {
			pioche[index] = carte;
		}
	}

	public void ajouterCarte(Carte carte) {
		pioche[nbCartes] = carte;
		nbCartes = (nbCartes + 1) % (pioche.length + 1);
	}
	
	public Carte retirerCarte(int index) {
		assert (nbCartes > 0);
		Carte carte = pioche[index];
		for (int i = index; i < nbCartes - 1; i++) {
			pioche[i] = pioche[i + 1];
		}
		pioche[nbCartes - 1] = null;
		nbCartes--;
		return carte;
	}

	public void remplirPioche(Carte carte, int occurence) {
		for (int i = 0; i < occurence; i++) {
			ajouterCarte(carte);
		}
	}

	public Carte piocherCarte() {
		Carte cartePiochee = null;
		if (nbCartes > 0) {
			int index = random.nextInt(nbCartes);
			cartePiochee = pioche[index];
			for (int i = index; i < nbCartes - 1; i++) {
				pioche[i] = pioche[i + 1];
			}
			pioche[nbCartes - 1] = null;
			nbCartes--;
		}
		return cartePiochee;
	}

}
