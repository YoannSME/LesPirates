package pirate;

import java.security.SecureRandom;
import java.util.Random;

import affichage.Affichage;
import affichage.IAffichage;
import cartes.*;
import jeu.Jeu;
import pioche.Pioche;

public class Pirate {
	private IAffichage affichage = new Affichage();
	private Random random;
	public static final int TAILLE_MAX = 5;
	private int pv = 5;
	private int popularite = 0;
	private String nom;

	private Pioche main = new Pioche(TAILLE_MAX - 1);
	private Pioche zonePopularite = new Pioche(TAILLE_MAX);
	private Pioche zoneAttaque = new Pioche(TAILLE_MAX);

	public Pirate(String nom) {
		this.nom = nom;
		try {
			random = new SecureRandom().getInstanceStrong();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Pioche getMain() {
		return main;
	}

	public Pioche getZonePopularite() {
		return zonePopularite;
	}

	public Pioche getZoneAttaque() {
		return zoneAttaque;
	}

	public String toString() {
		return "Le Pirate " + nom;
	}

	public String getNom() {
		return nom;
	}

	public int getPopularite() {
		return popularite;
	}

	public int getPV() {
		return pv;
	}

	public void piocherCarte(Jeu jeu) {
		Carte cartePiochee = jeu.getPioche().piocherCarte();
		main.ajouterCarte(cartePiochee);
	}

	public Carte choisirCarteAJouer() {
		int choix = affichage.afficherChoisirCarte(main.getNbCartes());
		return main.retirerCarte(choix - 1);
	}

	public void jouerCarte(Pirate adversaire, Carte carteJouee) {
		if (carteJouee instanceof CarteAttaque carteAttaque) {
			attaquerPirate(adversaire, carteAttaque);
		} else if (carteJouee instanceof CarteVole carteVole) {
			volerCarte(adversaire, carteVole.getNbCartesVolables());
		} else if (carteJouee instanceof CarteDenigrement carteDenigrement) {
			denigrerPirate(adversaire, carteDenigrement);
		} else if (carteJouee instanceof CartePileOuFace cartePileOuFace) {
			jouerPileOuFace(adversaire, cartePileOuFace);
		} else {
			subirEffetCarte(carteJouee);
		}

	}

	public void attaquerPirate(Pirate cible, CarteAttaque carte) {
		affichage.afficherAttaquePirate(nom, cible.getNom());
		cible.subirEffetCarte(carte);
	}

	public void volerCarte(Pirate cible, int nbCartesVolees) {
		int[] cartesPrises = affichage.recupererCartesVolables(nbCartesVolees, cible.getMain().piocheToString(),
				cible.getNom());
		int[] cartesEchange = affichage.recupererCartesEchangees(nbCartesVolees, main.piocheToString(), nom,
				cible.getMain().piocheToString(), cartesPrises);

		for (int i = 0; i < nbCartesVolees; i++) {
			if (cartesEchange[i] != 0) {
				Carte carteVolee = cible.getMain().getCarteAt(cartesPrises[i]);
				Carte carteEchange = this.main.getCarteAt(cartesEchange[i] - 1);
				cible.getMain().setCarteAt(carteEchange, cartesPrises[i]);
				this.main.setCarteAt(carteVolee, cartesEchange[i] - 1);
			}
		}

	}

	public void denigrerPirate(Pirate adversaire, CarteDenigrement carteJouee) {
		adversaire.perdrePopularite(carteJouee.getPointsPopularite());
		affichage.afficherEffetCarteDenigrement(nom, adversaire.getNom(), carteJouee.getPointsPopularite(),
				adversaire.getPopularite());
	}

	public void jouerPileOuFace(Pirate adversaire, CartePileOuFace carteJouee) {
		affichage.afficherEffetCartePileOuFace(nom);
		if (random.nextInt(carteJouee.getProbabilite()) == 0) {
			int pvAdverse = adversaire.getPV();
			adversaire.perdreVie(pvAdverse);
			affichage.afficherPerdreVie(adversaire.getNom(), pvAdverse, pvAdverse);
		} else {
			affichage.afficherPerdreVie(nom, pv, pv);
			perdreVie(pv);
		}
	}

	public void subirEffetCarte(Carte carte) {
		if (carte instanceof CartePopularite cartePopularite) {
			zonePopularite.ajouterCarte(cartePopularite);
			gagnerPopularite(cartePopularite.getNbPopularite());
			perdreVie(cartePopularite.getNbDegats());
			affichage.afficherEffetCartePopularite(nom, cartePopularite.getNbPopularite(),
					cartePopularite.getNbDegats(), popularite, pv);
		}
		if (carte instanceof CarteAttaque carteAttaque) {
			zoneAttaque.ajouterCarte(carteAttaque);
			perdreVie(carteAttaque.getNbDegats());
			affichage.afficherPerdreVie(nom, carteAttaque.getNbDegats(), pv);
		}
		if (carte instanceof CarteRegeneration carteRegeneration) {
			gagnerVie(carteRegeneration.getPvRecuperees());
			affichage.afficherEffetCarteRegeneration(nom, carteRegeneration.getPvRecuperees());
		}
	}

	public void perdreVie(int vie) {
		pv -= vie;
		if (pv < 0)
			pv = 0;
	}

	public void gagnerVie(int vie) {
		pv += vie;
	}

	public void gagnerPopularite(int pointsPopularite) {
		popularite += pointsPopularite;
	}

	public void perdrePopularite(int pointsPopularite) {
		popularite -= pointsPopularite;

	}

	public boolean estMort() {
		return pv < 1;
	}

	public boolean estAssezPopulaire() {
		return popularite >= 5;
	}

}