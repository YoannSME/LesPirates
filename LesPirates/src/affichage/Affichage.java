package affichage;

import java.util.Scanner;

import cartes.TypeCarte;

public class Affichage {
	private Scanner scanner = new Scanner(System.in);

	public void afficherMain(String nomPirate,String main) {
		System.out.println("Main du Pirate : " + nomPirate+"\n");
		System.out.println(main);
		
	}
	
	public int afficherChoisirCarte(int nbCartes) {
		int choix;
		do {
			System.out.print("Choisissez une carte à jouer : entre {" + 1 + " et " + nbCartes + "}"+"\n Choix : ");
			choix = scanner.nextInt();
		} while (choix < 1 || choix > nbCartes);
		return choix;
	}
	
	public void afficherCarte(TypeCarte typeCarte) {
		System.out.println("Carte : "+typeCarte);
	}
	
	public void afficherPiocherCarte(int taille_max,String nomPirate,int nbCartes,TypeCarte typeCarte) {
		if(nbCartes<taille_max) {
			System.out.println("Carte piochée : ");
			afficherCarte(typeCarte);
		}
		else
			System.out.println("La main de "+nomPirate+" est pleine, carte "+typeCarte+" remise en jeu.");
	}
	
	public void afficherAttaquePirate(String attaquant,String victime) {
		System.out.println(attaquant +" attaque "+ victime);
	}
	
	public void afficherPerdreVie(String nomPirate,int degats,int pv) {
		System.out.println("Le Pirate "+nomPirate+" a perdu "+ degats+ "PV, nouveau nombre de PV : "+pv);
		if(pv<=0)
			System.out.println(nomPirate + " Est mort.");
	}
	
	public void afficherEffetCartePopularite(String nomPirate,int nbPopulariteCarte,int nbDegats,int nbPopularitePirate,int pv) {
		System.out.println("Le Pirate "+nomPirate+" a gagne "+nbPopulariteCarte+" point(s) de popularité en échange de "+nbDegats+" PV."
				+ " Nouvelle popularité : "+nbPopularitePirate+",nouveau nombre de pv "+pv);
	}
	
	public void afficherGagnerPartie(String nomPirate,int pv, int popularite) {
		System.out.println("Partie finie, le pirate "+nomPirate+" a gagné grâce à sa populairté de "+popularite+" et ses PV restants de "+pv);
	}
	
	

}
