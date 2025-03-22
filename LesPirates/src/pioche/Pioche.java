package pioche;

import java.security.SecureRandom;
import java.util.Random;

import cartes.Carte;

public class Pioche {
	private Carte[] tabCartes;
	private int nbCartes = 0;
	private int tailleMax;
	private Random random;

	public Pioche(int tailleMax) {
		this.tailleMax = tailleMax;
		this.tabCartes = new Carte[tailleMax];
		try {
			random = SecureRandom.getInstanceStrong();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Carte[] getPioche() {
		return tabCartes;
	}
	

	public int getNbCartes() {
		return nbCartes;
	}

	public String[] piocheToString() {
		String[] retour = new String[nbCartes];
		for (int i = 0; i < nbCartes; i++) {
			retour[i] = "" + tabCartes[i].getType().getNom();
		}
		return retour;
	}

	public Carte getCarteAt(int index) {
		Carte carteRetour = null;
		if (index >= 0 && index < nbCartes) {
			carteRetour = tabCartes[index];
		}
		return carteRetour;
	}

	public void setCarteAt(Carte carte, int index) {
		if (index >= 0 && index < nbCartes) {
			tabCartes[index] = carte;
		}
	}

	public boolean ajouterCarteSansEcrasement(Carte carte) {
		boolean isAdded = false;
		if(nbCartes<tailleMax) {
			tabCartes[nbCartes] = carte;
			nbCartes++;
			isAdded = true;
		}
		return isAdded;
		
	}
	public void ajouterCarteAvecEcrasement(Carte carte) {
		tabCartes[nbCartes] = carte;
		nbCartes = (nbCartes + 1) % (tailleMax + 1);
		
	}

	public Carte retirerCarte(int index) {
		Carte carte = null;
		if (nbCartes > 0) {
			carte = tabCartes[index];
			for (int i = index; i < nbCartes - 1; i++) {
				tabCartes[i] = tabCartes[i + 1];
			}
			tabCartes[nbCartes - 1] = null;
			nbCartes--;
		}
		return carte;
	}

	public void remplirPioche(Carte carte, int occurence) {
		for (int i = 0; i < occurence; i++) {
			ajouterCarteSansEcrasement(carte);
		}
	}

	public Carte piocherCarte() {
		Carte cartePiochee = null;
		if (nbCartes > 0) {
			int index = random.nextInt(nbCartes);
			cartePiochee = tabCartes[index];
			for (int i = index; i < nbCartes - 1; i++) {
				tabCartes[i] = tabCartes[i + 1];
			}
			tabCartes[nbCartes - 1] = null;
			nbCartes--;
		}
		return cartePiochee;
	}

}
