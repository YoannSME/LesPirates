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

	private void tourDeJeu(Pirate pirateJoueur, Pirate pirateAdverse) {
		affichage.afficherDebutTour(pirateJoueur.getNom());
		pirateJoueur.piocherCarte(this);
		Carte carteAjouer = pirateJoueur.choisirCarteAJouer();
		pirateJoueur.jouerCarte(pirateAdverse, carteAjouer);
		affichage.afficherFinTour(pirateJoueur.getNom(), pirateJoueur.getPV(), pirateJoueur.getPopularite());
		affichage.afficherFinTour(pirateAdverse.getNom(), pirateAdverse.getPV(), pirateAdverse.getPopularite());
	}

	private Pirate choisirPremierJoueur(Pirate pirate1, Pirate pirate2) {
		if (random.nextInt(0, 2) % 2 == 0) {
			return pirate1;
		}
		return pirate2;
	}

	private boolean aGagne(Pirate pirateCourant, Pirate pirateAdverse) {
		return pirateCourant.estAssezPopulaire() || pirateAdverse.estMort();
	}

	public void lancerJeu() {
		Pirate gagnant = null;
		boolean partieFinie = false;
		for (int i = 0; i < 3; i++) {
			pirateBill.piocherCarte(this);
			pirateJack.piocherCarte(this);
		}
		// Arrêter dès qu'un pirate est mort

		Pirate premierPirate = choisirPremierJoueur(pirateJack, pirateBill);
		Pirate deuxiemePirate = (premierPirate == pirateJack) ? pirateBill : pirateJack;

		while (!partieFinie) {
			tourDeJeu(premierPirate, deuxiemePirate);
			if (aGagne(premierPirate, deuxiemePirate)) {
				partieFinie = true;
				gagnant = premierPirate;
			} else {
				tourDeJeu(deuxiemePirate, premierPirate);
				if (aGagne(deuxiemePirate, premierPirate)) {
					partieFinie = true;
					gagnant = deuxiemePirate;
				}

			}
		}
		affichage.afficherGagnerPartie(gagnant.getNom(), gagnant.getPV(), gagnant.getPopularite());

	}

	public static void main(String[] args) {
		Jeu jeu = new Jeu();
		CartePopularite abordageReussi = new CartePopularite(TypeCarte.AbordageReussi, 0, 2,"Au cours d'un abordage, le\r\n"
				+ "pirate fait preuve d'une\r\n"
				+ "grande bravoure et gagne deux\r\n"
				+ "points de popularité");
		CartePopularite discoursInspirant = new CartePopularite(TypeCarte.DiscoursInspirant, 0, 1,"Le discours inspirant du pirate a motivé son équipage et lui a fait gagner 1pt de popularité");
		CartePopularite mainDeFer = new CartePopularite(TypeCarte.MainDeFer, 1, 2,"En échange de 2 points de popularité, le pirate perd 1PV.");

		CarteAttaque coupDeSabre = new CarteAttaque(TypeCarte.CoupDeSabre, 2,"Le pirate attaque son adversaire pour lui infliger 2pts de dégats");
		for (int i = 0; i < 15; i++) {
			jeu.remplirPioche(mainDeFer);
			jeu.remplirPioche(discoursInspirant);
		}
		for (int i = 0; i < 20; i++) {
			jeu.remplirPioche(coupDeSabre);
		}

		jeu.lancerJeu();

	}

}
