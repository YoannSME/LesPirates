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
	private static final IAffichage affichage = new Affichage();
	private Pirate pirateJack = new Pirate("Jack le Borgne");
	private Pirate pirateBill = new Pirate("Bill Jambe-de-Bois");

	private Pioche pioche = new Pioche(50);

	public Jeu() {
		try {
			random = SecureRandom.getInstanceStrong();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static IAffichage getAffichage() {
		return affichage;
	}

	public Pioche getPioche() {
		return pioche;
	}

	public void initialiserPioche() {
		CartePopularite abordageReussi = new CartePopularite(TypeCarte.ABORDAGE_REUSSI, 0, 2,
				"Au cours d'un abordage, le pirate fait preuve d'une grande bravoure et gagne deux points de popularité");
		CartePopularite discoursInspirant = new CartePopularite(TypeCarte.DISCOURS_INSPIRANT, 0, 1,
				"Le discours inspirant du pirate a motivé son équipage et lui a fait gagner 1 point de popularité");
		CartePopularite mainDeFer = new CartePopularite(TypeCarte.MAIN_DE_FER, 1, 2,
				"En échange de 2 points de popularité, le pirate perd 1PV.");
		CarteAttaque coupDeSabre = new CarteAttaque(TypeCarte.COUP_DE_SABRE, 2,
				"Le pirate attaque son adversaire pour lui infliger 2 points de dégats");
		CarteVol carteVol = new CarteVol(TypeCarte.VOL_DE_CARTE, 2,
				"Le pirate rempli de malice vient voler 2 cartes à son adversaire");
		CarteRegeneration carteRegen = new CarteRegeneration(TypeCarte.REGENERATION_HP, 2, "Le pirate récupère 2PV");
		CarteDenigrement carteDenigrement = new CarteDenigrement(TypeCarte.LANGUE_DE_SERPENT,
				"Le pirate vient dénigrer son adversaire et lui fait perdre 2 points de popularité", 2);
		CartePileOuFace cartePileOuFace = new CartePileOuFace(TypeCarte.PILE_OU_FACE,
				"Le pirate veut en finir avec la partie et décide de remettre son sort au pile ou face... Avec des probabilités truquées",
				3);
		CarteSwitch carteSwitch = new CarteSwitch(TypeCarte.SWITCH,
				"Le pirate échange son destin avec celui de son adversaire.");

		pioche.remplirPioche(mainDeFer, 6);
		pioche.remplirPioche(discoursInspirant, 6);
		pioche.remplirPioche(coupDeSabre, 7);
		pioche.remplirPioche(carteRegen, 7);
		pioche.remplirPioche(abordageReussi, 7);
		pioche.remplirPioche(carteVol, 5);
		pioche.remplirPioche(cartePileOuFace, 3);
		pioche.remplirPioche(carteDenigrement, 7);
		pioche.remplirPioche(carteSwitch, 2);

	}

	public void tourDeJeu(Pirate pirateJoueur, Pirate pirateAdverse) {
		if (pioche.getNbCartes() <= 1) {
			initialiserPioche();
		}
		pirateJoueur.piocherCarte(this);
		affichage.afficherDebutTour(pirateJoueur.getNom());
		affichage.afficherMain(pirateJoueur.getMain().piocheToString());
		Carte carteAjouer = pirateJoueur.choisirCarteAJouer();
		affichage.afficherDetailCarte(carteAjouer.getType(), carteAjouer.getDescription());
		pirateJoueur.jouerCarte(pirateAdverse, carteAjouer);
		affichage.afficherFinTour(pirateJoueur.toString(), pirateJoueur.getPV(), pirateJoueur.getPopularite());
		affichage.afficherFinTour(pirateAdverse.toString(), pirateAdverse.getPV(), pirateAdverse.getPopularite());
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
		else if (pirateAdverse.estAssezPopulaire() || pirateCourant.estMort())
			gagnant = pirateAdverse;
		return gagnant;
	}

	public void preparerJeu() {
		initialiserPioche();

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
				Pirate temp = premierPirate;
				premierPirate = deuxiemePirate;
				deuxiemePirate = temp;
			}

		}
		affichage.afficherGagnerPartie(gagnant.toString());

	}

	public static void main(String[] args) {
		Jeu jeu = new Jeu();

		jeu.lancerJeu();

	}

}
