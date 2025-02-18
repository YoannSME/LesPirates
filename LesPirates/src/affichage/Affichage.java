package affichage;

import java.util.Scanner;

import cartes.TypeCarte;

public class Affichage {

	public static final int TAILLE_AFFICHAGE = 13;
	private Scanner scanner = new Scanner(System.in);

	public void afficherMain(String main) {
		System.out.println("Main du pirate :\n" + main);

	}

	public int afficherChoisirCarte(int nbCartes) {
		int choix;
		do {
			System.out.print("Choisissez une carte à jouer : entre {" + 1 + " et " + nbCartes + "}" + "\n Choix : ");
			choix = scanner.nextInt();
		} while (choix < 1 || choix > nbCartes);
		return choix;
	}

	public void detailCarte(TypeCarte typeCarte, String texte) {
		int tailleTexte = texte.length();

		System.out.print("\n\t\t\tCarte : " + typeCarte + "\n\t\t");
		System.out.print("*".repeat(tailleTexte + 4) + "\n\t\t");
		System.out.print("* " + texte + " *" + "\n\t\t");
		System.out.println("*".repeat(tailleTexte + 4) + "\n");
	}

	public void afficherPiocherCarte(int taille_max, String nomPirate, int nbCartes, TypeCarte typeCarte) {
		if (nbCartes < taille_max) {
			System.out.println("Carte piochée : " + typeCarte);

		} else
			System.out.println("La main de " + nomPirate + " est pleine, carte " + typeCarte + " remise en jeu.");
	}

	public void afficherAttaquePirate(String attaquant, String victime) {
		System.out.println(attaquant + " attaque " + victime);
	}

	public void afficherPerdreVie(String nomPirate, int degats, int pv) {
		System.out.println("Le Pirate " + nomPirate + " a perdu " + degats + "PV, nouveau nombre de PV : " + pv);
		if (pv <= 0)
			System.out.println(nomPirate + " Est mort.");
	}

	public void afficherEffetCartePopularite(String nomPirate, int nbPopulariteCarte, int nbDegats,
			int nbPopularitePirate, int pv) {
		System.out.println("Le Pirate " + nomPirate + " a gagne " + nbPopulariteCarte
				+ " point(s) de popularité en échange de " + nbDegats + " PV." + " Nouvelle popularité : "
				+ nbPopularitePirate + ",nouveau nombre de pv " + pv);
	}

	public void afficherEffetCarteRegeneration(String nomPirate, int pvRecuperees) {
		System.out.println("Le Pirate " + nomPirate + " a recuperee " + pvRecuperees + " pv.");
	}

	public void afficherGagnerPartie(String nomPirate, int pv, int popularite) {
		System.out.println("Partie finie, le pirate " + nomPirate + " a gagné cet affrontement.");
	}

	public void afficherDebutTour(String nomPirate) {
		System.out.println("_".repeat(150));
		System.out.println("Debut du tour de jeu du Pirate : " + nomPirate);
	}

	public void afficherFinTour(String nomPirate, int pvPirate, int popularitePirate) {
		System.out
				.println("\n" + nomPirate + "\n	PV : " + pvPirate + "\n 	Popularite : " + popularitePirate + ".");
	}

	public int[] afficherVolerCartes(String nomPirate, String mainPirate, int nbCartesVolees, int nbCartesEnMain) {
		int[] indicesCartes = new int[nbCartesVolees];
		afficherMain(mainPirate);
		System.out.println("Vous pouvez voler " + nbCartesVolees + " parmi celles-ci, lesquelles choisissez vous ?");
		for (int i = 0; i < nbCartesVolees; i++) {
			do {
				System.out.println("Carte " + i + 1 + ":");
				indicesCartes[i] = scanner.nextInt() - 1;
			} while (indicesCartes[i] > nbCartesEnMain || indicesCartes[i] > 0);
		}
		
		return indicesCartes;

	}

}
