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

	public Carte getCarte(int index) {
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
		Carte cartePiochee = jeu.piocherCarte();
		ajouterCarte(cartePiochee);

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

	public Carte choisirCarteAJouer() {
		int choix = affichage.afficherChoisirCarte(nbCartesEnMain);
		return enleverCarte(choix - 1);
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

	private void attaquerPirate(Pirate cible, CarteAttaque carte) {
		affichage.afficherAttaquePirate(nom, cible.getNom());
		cible.subirEffetCarte(carte);
	}

	private void volerCarte(Pirate cible, int nbCartesVolees) {
		int[] cartesPrises = affichage.recupererCartesVolables(nbCartesVolees, cible.mainToString(), cible.getNom());
		int[] cartesEchange = affichage.recupererCartesEchangees(nbCartesVolees, mainToString(), nom,
				cible.mainToString(), cartesPrises);

		for (int i = 0; i < nbCartesVolees; i++) {
			if (cartesEchange[i] != 0) {
				Carte carteVolee = cible.getCarte(cartesPrises[i]);
				Carte carteEchange = this.getCarte(cartesEchange[i] - 1);
				cible.setCarteAt(carteEchange, cartesPrises[i]);
				this.setCarteAt(carteVolee, cartesEchange[i] - 1);
			}
		}

	}

	private void subirEffetCarte(Carte carte) {
		if (carte instanceof CartePopularite cartePopularite) {
			zonePopularite[nbCartesPopularite] = cartePopularite;
			nbCartesPopularite = (nbCartesPopularite + 1) % TAILLE_MAX;
			gagnerPopularite(cartePopularite.getNbPopularite());
			perdreVie(cartePopularite.getNbDegats());
			affichage.afficherEffetCartePopularite(nom, cartePopularite.getNbPopularite(),
					cartePopularite.getNbDegats(), popularite, pv);
		}
		if (carte instanceof CarteAttaque carteAttaque) {
			zoneAttaque[nbCartesZoneAttaque] = carteAttaque;
			nbCartesZoneAttaque = (nbCartesZoneAttaque + 1) % TAILLE_MAX;
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
		if(pv<0)
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
		if(popularite<0)
			popularite = 0;
	}

	public boolean estMort() {
		return pv < 1;
	}

	public boolean estAssezPopulaire() {
		return popularite >= 5;
	}

}