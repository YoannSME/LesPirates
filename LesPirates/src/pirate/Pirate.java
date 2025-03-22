package pirate;

import affichage.IAffichage;
import cartes.*;
import jeu.Jeu;
import pioche.Pioche;

public class Pirate {
	private IAffichage affichage = Jeu.getAffichage();
	private static final int TAILLE_MAX = 5;
	private int pv = 5;
	private int popularite = 0;
	private String nom;

	private Pioche main = new Pioche(TAILLE_MAX - 1);
	private Pioche zonePopularite = new Pioche(TAILLE_MAX);
	private Pioche zoneAttaque = new Pioche(TAILLE_MAX);

	public Pirate(String nom) {
		this.nom = nom;
	}

	public Pioche getMain() {
		return main;
	}

	public void setMain(Pioche main) {
		this.main = main;
	}

	public Pioche getZonePopularite() {
		return zonePopularite;
	}

	public Pioche getZoneAttaque() {
		return zoneAttaque;
	}

	public void setPv(int pv) {
		this.pv = pv;
	}

	public void setPopularite(int popularite) {
		this.popularite = popularite;
	}

	public void setZonePopularite(Pioche zonePopularite) {
		this.zonePopularite = zonePopularite;

	}

	public void setZoneAttaque(Pioche zoneAttaque) {
		this.zoneAttaque = zoneAttaque;

	}

	public String toString() {
		return "Le Pirate : " + nom;
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
		main.ajouterCarteSansEcrasement(cartePiochee);
	}

	public Carte choisirCarteAJouer() {
		int choix = affichage.afficherChoisirCarte(main.getNbCartes());
		return main.retirerCarte(choix - 1);
	}

	public void jouerCarte(Pirate adversaire, Carte carteJouee) {
		carteJouee.effetCarte(this, adversaire);
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
		return popularite >= 5 && !estMort();
	}

}