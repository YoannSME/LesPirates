package pirate;

import java.security.SecureRandom;
import java.util.Random;

import cartes.*;
import jeu.Jeu;

public class Pirate {

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

	public String getNom() {
		return nom;
	}

	public int getNbCartes() {
		return nbCartesEnMain;
	}

	public Carte piocherCarte(Jeu jeu) {
		Carte cartePiochee = null;
		if (nbCartesEnMain < TAILLE_MAX) {
			cartePiochee = jeu.piocherCarte();
			if (cartePiochee != null) {
				main[nbCartesEnMain] = cartePiochee;
				nbCartesEnMain++;
			}
		}
		return cartePiochee;

	}

	public void attaquerPirate(Pirate pirate, Carte carte) {
		pirate.ajouterDansZone(carte);

	}

	public void ajouterDansZone(Carte carte) {
		if (carte instanceof CartePopularite cartePopularite && nbCartesPopularite < TAILLE_MAX) {
			zonePopularite[nbCartesPopularite] = cartePopularite;
			nbCartesPopularite++;
			
			gagnerPopularite(cartePopularite.getNbPopularite());
			perdreVie(cartePopularite.getNbDegats());
			System.out.println(nom  + " + "+cartePopularite.getNbPopularite()+" - "+cartePopularite.getNbDegats());
			
		}
		if (carte instanceof CarteAttaque carteAttaque && nbCartesZoneAttaque < TAILLE_MAX) {
			zoneAttaque[nbCartesZoneAttaque] = carteAttaque;
			nbCartesZoneAttaque--;
			perdreVie(carteAttaque.getNbDegats());
		}

	}

	public void perdreVie(int vie) {
		pv -= vie;
	}

	public void gagnerPopularite(int pointsPopularite) {
		popularite += pointsPopularite;
	}

	public boolean aGagne() {
		return pv > 0 && popularite >= 5;
	}

}
