package pirate;


import affichage.Affichage;
import affichage.IAffichage;
import cartes.*;
import jeu.Jeu;

public class Pirate {
	IAffichage affichage = new Affichage();

	public static final int TAILLE_MAX = 5;
	private int pv = 5;
	private int popularite = 0;
	private String nom;

	private Carte[] main = new Carte[TAILLE_MAX - 1];
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

	public Carte getCarteAt(int index) {
		assert (index >= 0 && index < nbCartesEnMain);
		return main[index];
	}

	public void setCarteAt(Carte carte, int index) {
		assert (index >= 0 && index < nbCartesEnMain && nbCartesEnMain < TAILLE_MAX - 1);
		main[index] = carte;
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

	public String[] mainToString() {
		String[] deck = new String[nbCartesEnMain];
		for (int i = 0; i < nbCartesEnMain; i++) {
			deck[i] = "" + main[i].getType();
		}

		return deck;
	}

	public boolean ajouterCarte(Carte carte) {
		if (nbCartesEnMain < TAILLE_MAX) {
			main[nbCartesEnMain] = carte;
			nbCartesEnMain++;
			return true;
		}

		return false;
	}

	public void piocherCarte(Jeu jeu) {
		Carte cartePiochee = null;
		cartePiochee = jeu.piocherCarte();
		ajouterCarte(cartePiochee);

	}

	public Carte choisirCarteAJouer() {
		affichage.afficherMain(mainToString());
		int choix = affichage.afficherChoisirCarte(nbCartesEnMain);
		Carte carteChoisie = main[choix - 1];
		affichage.detailCarte(carteChoisie.getType(), carteChoisie.getDescription());
		enleverCarte(choix - 1);
		return carteChoisie;
	}

	public void attaquerPirate(Pirate pirate, CarteAttaque carte) {
		affichage.afficherAttaquePirate(nom, pirate.getNom());
		pirate.subirEffetCarte(carte);
	}

	public void volerCarte(Pirate victime, int nbCartesVolees) {
		int[] cartesPrises = affichage.recupererCartesVolables(nbCartesVolees, victime.mainToString(),
				victime.getNom());
		int[] cartesEchange = affichage.recupererCartesEchangees(nbCartesVolees, mainToString(), nom,
				victime.mainToString(), cartesPrises);

		for (int i = 0; i < nbCartesVolees; i++) {
			if (cartesEchange[i] != 0) {
				Carte carteVolee = victime.getCarteAt(cartesPrises[i]);
				Carte carteEchange = getCarteAt(cartesEchange[i] - 1);
				victime.setCarteAt(carteEchange, cartesPrises[i]);
				setCarteAt(carteVolee, cartesEchange[i] - 1);
			}
		}

	}

	public Carte enleverCarte(int index) {
		assert (nbCartesEnMain > 0);
		Carte carte = main[index];
		for (int i = index; i < nbCartesEnMain - 1; i++) {
			main[i] = main[i + 1];
		}
		main[nbCartesEnMain - 1] = null;
		nbCartesEnMain--;
		return carte;
	}

	private void subirEffetCarte(Carte carte) {
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
		if (carte instanceof CarteRegeneration carteRegeneration) {
			gagnerVie(carteRegeneration.getPvRecuperees());
			affichage.afficherEffetCarteRegeneration(nom, carteRegeneration.getPvRecuperees());
		}
	}

	public void jouerCarte(Pirate adversaire, Carte carteJouee) {

		if (carteJouee instanceof CarteAttaque carteAttaque) {
			attaquerPirate(adversaire, carteAttaque);
		} else if (carteJouee instanceof CarteVole carteVole) {
			volerCarte(adversaire, carteVole.getNbCartesVolables());
		} else {
			subirEffetCarte(carteJouee);
		}

	}

	public void perdreVie(int vie) {
		pv -= vie;
	}

	public void gagnerVie(int vie) {
		pv += vie;
	}

	public void gagnerPopularite(int pointsPopularite) {
		popularite += pointsPopularite;
	}

	public boolean estMort() {
		return pv < 1;
	}

	public boolean estAssezPopulaire() {
		return popularite >= 5;
	}

}