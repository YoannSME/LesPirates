package jeu;

import pirate.*;

import java.security.SecureRandom;
import java.util.Random;

import affichage.Affichage;
import cartes.*;

public class Jeu {
	private Random random;
	private Affichage affichage = new Affichage();
	private Pirate pirateJack = new Pirate("Jack le Borgne");
	private Pirate pirateBill = new Pirate("Bill Jambe-de-Bois");

	private Carte[] pioche = new Carte[50];
	private int nbCartes = 0;

	public Jeu() {
		try {
			random = new SecureRandom().getInstanceStrong();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void remplirPioche(Carte carte) {
		if (nbCartes < 50) {
			pioche[nbCartes] = carte;
			nbCartes++;
		}
	}

	public Carte piocherCarte() {
		Carte cartePiochee = null;
		if (nbCartes > 0) {
			int indice = random.nextInt(nbCartes);
			cartePiochee = pioche[indice];
		}
		return cartePiochee;
	}

	private void tourDeJeu(Pirate pirate1,Pirate pirate2) {
		Carte cartePiochee = pirate1.piocherCarte(this);;
		pirate1.jouerCarte(pirate2);

	}

	public void lancerJeu() {
		boolean partieFinie = false;
		while (!partieFinie) {
			tourDeJeu(pirateBill,pirateJack);
			partieFinie = pirateBill.aGagne();
			tourDeJeu(pirateJack,pirateBill);
			partieFinie = pirateJack.aGagne();
			}
		if(pirateBill.aGagne()) {
			affichage.afficherGagnerPartie(pirateBill);
		}
		else {
			affichage.afficherGagnerPartie(pirateJack);
		}
		
	}

	public static void main(String[] args) {
		Jeu jeu = new Jeu();
		CartePopularite abordageReussi = new CartePopularite(TypePopularite.AbordageReussi, 0,2);
		CartePopularite discoursInspirant = new CartePopularite(TypePopularite.DiscoursInspirant,0, 1);
		CartePopularite maindefer = new CartePopularite(TypePopularite.MainDeFer,1,2);
		for (int i = 0; i < 25; i++) {
			jeu.remplirPioche(maindefer);
			jeu.remplirPioche(discoursInspirant);
		}

		jeu.lancerJeu();

	}

}
