package pirate;

import java.security.SecureRandom;
import java.util.Random;

import cartes.*;
import jeu.Jeu;

public class Pirate {
	private Random random;

	private int pv = 5;
	private int popularite = 0;
	private String nom;

	private Carte[] main = new Carte[5];
	private int nbCartesEnMain = 0;

	private CartePopularite[] zonePopularite =new CartePopularite[5];
	private int nbCartesPopularite = 0;
	
	
	public Pirate(String nom) {
		this.nom = nom;
		try {
			random = new SecureRandom().getInstanceStrong();
		} catch (Exception e) {
			e.printStackTrace();
		}
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
		if (nbCartesEnMain < 5) {
			cartePiochee = jeu.piocherCarte();
			if (cartePiochee != null) {
				main[nbCartesEnMain] = cartePiochee;
				nbCartesEnMain++;
			}
		}
		return cartePiochee;

	}

	public void ajouterDansZone(Carte carte) {
		if(carte instanceof CartePopularite) {
			CartePopularite cartePopularite = (CartePopularite)carte;
			zonePopularite[nbCartesPopularite] = cartePopularite;
			nbCartesPopularite++;
			gagnerPopularite(cartePopularite.getNbPopularite());
			System.out.println(nom + " popularite : "+popularite);
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
