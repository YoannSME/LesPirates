package pirate;

import java.security.SecureRandom;
import java.util.Random;

import affichage.Affichage;
import cartes.*;
import jeu.Jeu;

public class Pirate {
	Affichage affichage = new Affichage();

	public static final int TAILLE_MAX = 5;
	private int pv = 5;
	private int popularite = 0;
	private String nom;

	private Carte[] main = new Carte[TAILLE_MAX];
	private int nbCartesEnMain = 0;

	private CartePopularite[] zonePopularite = new CartePopularite[TAILLE_MAX];
	private int nbCartesPopularite = 0;

	private Carte[] zoneAttaque = new Carte[TAILLE_MAX];
	private int nbCartesZoneAttaque = 0;

	public Pirate(String nom) {
		this.nom = nom;
	}

	public Carte[] getMain() {
		return main;
	}

	public String toString() {
		return "Le Pirate " + nom;
	}

	public String getNom() {
		return nom;
	}

	public int getNbCartes() {
		return nbCartesEnMain;
	}

	public int getPopularite() {
		return popularite;
	}

	public int getPV() {
		return pv;
	}

	public String mainToString() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < nbCartesEnMain; i++) {
			sb.append(i + 1 + " - ");
			sb.append(main[i].getType() + "\n");
		}

		return sb.toString();
	}

	public void piocherCarte(Jeu jeu) {
		Carte cartePiochee = null;
		if (nbCartesEnMain < TAILLE_MAX) {
			cartePiochee = jeu.piocherCarte();
			if (cartePiochee != null) {
				main[nbCartesEnMain] = cartePiochee;
				nbCartesEnMain++;
			}
		}

	}

	public Carte choisirCarteAJouer() {
		affichage.afficherMain(nom, mainToString());
		int choix = affichage.afficherChoisirCarte(nbCartesEnMain);
		Carte carteChoisie = main[choix - 1];
		affichage.afficherCarte(carteChoisie.getType());
		defausserCarte(choix - 1);
		return carteChoisie;
	}

	public void attaquerPirate(Pirate pirate, CarteAttaque carte) {
		affichage.afficherAttaquePirate(nom, pirate.getNom());
		pirate.subirEffetCarte(carte);

	}

	public void defausserCarte(int index) {

		for (int i = index; i < nbCartesEnMain - 1; i++) {
			main[i] = main[i + 1];
		}
		main[nbCartesEnMain] = null;
		if (nbCartesEnMain > 0)
			nbCartesEnMain--;
	}

	public void subirEffetCarte(Carte carte) {
		if (carte instanceof CartePopularite cartePopularite && nbCartesPopularite < TAILLE_MAX) {
			zonePopularite[nbCartesPopularite] = cartePopularite;
			nbCartesPopularite++;
			gagnerPopularite(cartePopularite.getNbPopularite());
			perdreVie(cartePopularite.getNbDegats());

			affichage.afficherEffetCartePopularite(nom, cartePopularite.getNbPopularite(),
					cartePopularite.getNbDegats(), popularite, pv);
		}
		if (carte instanceof CarteAttaque carteAttaque && nbCartesZoneAttaque < TAILLE_MAX) {
			zoneAttaque[nbCartesZoneAttaque] = carteAttaque;
			nbCartesZoneAttaque++;
			perdreVie(carteAttaque.getNbDegats());

			affichage.afficherPerdreVie(nom, carteAttaque.getNbDegats(), pv);
		}
	}

	public void jouerCarte(Pirate adversaire) {
		Carte carteAjouer = choisirCarteAJouer();
		if (carteAjouer instanceof CarteAttaque carteAttaque) {
			attaquerPirate(adversaire, carteAttaque);
		} else {
			subirEffetCarte(carteAjouer);
		}

	}

	public void perdreVie(int vie) {
		pv -= vie;
	}

	public void gagnerPopularite(int pointsPopularite) {
		popularite += pointsPopularite;
	}

	public boolean aGagne() {
		return (!estMort() && estAssezPopulaire());
	}

	public boolean estMort() {
		return pv < 1;
	}

	public boolean estAssezPopulaire() {
		return popularite >= 5;
	}

}