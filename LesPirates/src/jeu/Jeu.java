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
		if(nbCartes<50) {
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
	
	private void tourDeJeu(Pirate pirate) {
		//TODO Faire la logique de jeu : Permettre au joueur de choisir une carte dans sa main
		Carte cartePiochee = pirate.piocherCarte(this);
		System.out.println("Pirate "+pirate.getNom());
		affichage.afficherMain(pirate);
		pirate.ajouterDansZone(cartePiochee);
	
	}

	
	public void lancerJeu() {
		boolean partieFinie = false;
		while(!partieFinie) {
			tourDeJeu(pirateBill);
			if(pirateBill.aGagne()) {
				partieFinie = true;
			}
			else {
				tourDeJeu(pirateJack);
				if(pirateJack.aGagne()) {
					partieFinie = true;
				}
			}
		}
	}
	
	public static void main(String[] args) {
		Jeu jeu = new Jeu();
		CartePopularite abordageReussi = new CartePopularite(TypePopularite.AbordageReussi,2);
		CartePopularite discoursInspirant = new CartePopularite(TypePopularite.DiscoursInspirant,1);
		for(int i = 0;i<25;i++) {
			jeu.remplirPioche(abordageReussi);
			jeu.remplirPioche(discoursInspirant);
		}
		
		jeu.lancerJeu();
		
		
	}

}
