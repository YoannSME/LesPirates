package affichage;

import java.util.Scanner;

import cartes.*;

import pirate.Pirate;

public class Affichage {
	private Scanner scanner = new Scanner(System.in);

	public void afficherMain(Pirate pirate) {
		System.out.println("Main du Pirate : " + pirate.getNom());
		for (int i = 0; i < pirate.getNbCartes(); i++) {
			System.out.println(i+1 + " - " + pirate.getMain()[i].getType());
		}
	}
	
	public int afficherChoisirCarte(Pirate pirate) {
		int choix;
		do {
			System.out.print("Choisissez une carte à jouer : entre {" + 1 + " et " + pirate.getNbCartes() + "}"+"\n Choix : ");
			choix = scanner.nextInt();
		} while (choix < 1 || choix > pirate.getNbCartes());
		return choix;
	}
	
	public void afficherCarte(Carte carte) {
		System.out.println("Carte : "+carte.getType());
	}
	
	public void afficherPiocherCarte(Pirate pirate,Carte carte) {
		if(pirate.getNbCartes()<Pirate.TAILLE_MAX) {
			System.out.println("Carte piochée : ");
			afficherCarte(carte);
		}
		else
			System.out.println("La main de "+pirate.getNom()+" est pleine, carte "+carte.getType()+" défaussée.");
	}
	
	public void afficherAttaquePirate(Pirate attaquant,Pirate victime,CarteAttaque carte) {
		System.out.println(attaquant +" attaque "+ victime);
	}
	
	public void afficherPerdreVie(Pirate pirate,int degats) {
		System.out.println("Le Pirate "+pirate.getNom()+" a perdu "+ degats+ "PV, nouveau nombre de PV : "+pirate.getPV());
	}
	
	public void afficherEffetCartePopularite(Pirate pirate,CartePopularite carte) {
		System.out.println("Le Pirate "+pirate.getNom()+" a gagne "+carte.getNbPopularite()+" point(s) de popularité en échange de "+carte.getNbDegats()+" PV."
				+ " Nouvelle popularité : "+pirate.getPopularite()+",nouveau nombre de pv "+pirate.getPV());
	}
	
	public void afficherGagnerPartie(Pirate pirate) {
		System.out.println("Partie finie, le pirate "+pirate.getNom()+" a gagné grâce à sa populairté de "+pirate.getPopularite()+" et ses PV restants de "+pirate.getPV());
	}
	
	

}
