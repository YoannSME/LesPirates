package jeu;

import pirate.*;

import java.security.SecureRandom;
import java.util.Random;

import affichage.Affichage;
import affichage.IAffichage;
import cartes.*;
import pioche.Pioche;

public class Jeu {
	private Random random;
	private IAffichage affichage = new Affichage();
	private Pirate pirateJack = new Pirate("Jack le Borgne");
	private Pirate pirateBill = new Pirate("Bill Jambe-de-Bois");

	private Pioche pioche = new Pioche(50);

	public Jeu() {
		try {
			random = new SecureRandom().getInstanceStrong();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public Pioche getPioche() {
		return pioche;
	}

	public void tourDeJeu(Pirate pirateJoueur, Pirate pirateAdverse) {
		pirateJoueur.piocherCarte(this);
		affichage.afficherDebutTour(pirateJoueur.getNom());
		affichage.afficherMain(pirateJoueur.getMain().piocheToString());
		Carte carteAjouer = pirateJoueur.choisirCarteAJouer();
		affichage.afficherDetailCarte(carteAjouer.getType(), carteAjouer.getDescription());
		pirateJoueur.jouerCarte(pirateAdverse, carteAjouer);
		affichage.afficherFinTour(pirateJoueur.getNom(), pirateJoueur.getPV(), pirateJoueur.getPopularite());
		affichage.afficherFinTour(pirateAdverse.getNom(), pirateAdverse.getPV(), pirateAdverse.getPopularite());
	}

	public Pirate choisirPremierJoueur(Pirate pirate1, Pirate pirate2) {
		if (random.nextInt(0, 2) % 2 == 0) {
			return pirate1;
		}
		return pirate2;
	}

	public Pirate pirateGagnant(Pirate pirateCourant, Pirate pirateAdverse) {
		Pirate gagnant = null;
		if (pirateCourant.estAssezPopulaire() || pirateAdverse.estMort())
			gagnant = pirateCourant;
		if (pirateAdverse.estAssezPopulaire() || pirateCourant.estMort())
			gagnant = pirateAdverse;
		return gagnant;
	}

	public void preparerJeu() {
		CartePopularite abordageReussi = new CartePopularite(TypeCarte.AbordageReussi, 0, 2,
				"Au cours d'un abordage, le pirate fait preuve d'une grande bravoure et gagne deux points de popularité");
		CartePopularite discoursInspirant = new CartePopularite(TypeCarte.DiscoursInspirant, 0, 1,
				"Le discours inspirant du pirate a motivé son équipage et lui a fait gagner 1 point de popularité");
		CartePopularite mainDeFer = new CartePopularite(TypeCarte.MainDeFer, 1, 2,
				"En échange de 2 points de popularité, le pirate perd 1PV.");

		CarteAttaque coupDeSabre = new CarteAttaque(TypeCarte.CoupDeSabre, 2,
				"Le pirate attaque son adversaire pour lui infliger 2 points de dégats");
		CarteVole carteVole = new CarteVole(TypeCarte.VoleDeCarte, 2,
				"Le pirate rempli de malice vient voler 2 cartes à son adversaire");
		CarteRegeneration carteRegen = new CarteRegeneration(TypeCarte.RegenerationHP, 2, "Le pirate récupère 2PV");

		pioche.remplirPioche(mainDeFer, 9);
		pioche.remplirPioche(discoursInspirant, 9);
		pioche.remplirPioche(coupDeSabre, 9);
		pioche.remplirPioche(carteRegen, 9);
		pioche.remplirPioche(abordageReussi, 9);
		pioche.remplirPioche(carteVole, 5);

		for (int i = 0; i < 3; i++) {
			pirateBill.piocherCarte(this);
			pirateJack.piocherCarte(this);
		}

	}

	public void lancerJeu() {

		preparerJeu();
		Pirate gagnant = null;
		boolean partieFinie = false;

		Pirate premierPirate = choisirPremierJoueur(pirateJack, pirateBill);
		Pirate deuxiemePirate = (premierPirate == pirateJack) ? pirateBill : pirateJack;

		while (!partieFinie) {
			tourDeJeu(premierPirate, deuxiemePirate);
			gagnant = pirateGagnant(premierPirate, deuxiemePirate);
			if (gagnant != null)
				partieFinie = true;
			else {
				tourDeJeu(deuxiemePirate, premierPirate);
				gagnant = pirateGagnant(deuxiemePirate, premierPirate);
				if (gagnant != null)
					partieFinie = true;
			}

		}
		affichage.afficherGagnerPartie(gagnant.getNom());

	}

	public static void main(String[] args) {
		Jeu jeu = new Jeu();

		jeu.lancerJeu();

	}

}
