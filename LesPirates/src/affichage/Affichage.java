package affichage;

import java.util.Random;
import java.util.Scanner;

import cartes.TypeCarte;

public class Affichage {

	public static final int TAILLE_AFFICHAGE = 13;
	private Scanner scanner = new Scanner(System.in);
	private Random random = new Random();

	public void afficherMain( String[] main) {
		System.out.println("=".repeat(40));
		for (int i = 0; i < main.length; i++) {
			System.out.println(i + 1 + " - " + main[i]);
		}
		System.out.println("=".repeat(40));

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
		System.out.println("-- " + attaquant + " attaque " + victime);
	}

	public void afficherPerdreVie(String nomPirate, int degats, int pv) {
		System.out.println("-- Le Pirate " + nomPirate + " a perdu " + degats + "PV.");
		if (pv <= 0)
			System.out.println(nomPirate + " Est mort.");
	}

	public void afficherEffetCartePopularite(String nomPirate, int nbPopulariteCarte, int nbDegats,
			int nbPopularitePirate, int pv) {
		System.out.println("-- Le Pirate " + nomPirate + " a gagne " + nbPopulariteCarte
				+ " point(s) de popularité en échange de " + nbDegats + " PV." + " Nouvelle popularité : "
				+ nbPopularitePirate + ",nouveau nombre de pv " + pv);
	}

	public void afficherEffetCarteRegeneration(String nomPirate, int pvRecuperees) {
		System.out.println("-- Le Pirate " + nomPirate + " a recupere " + pvRecuperees + " pv.");
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

	public int[] recupererCartesVolables(int nbCartesVolees, String[] mainVictime, String nomVictime) {
		int[] indicesCartes = new int[nbCartesVolees];
		System.out.println("\nVoici les " + nbCartesVolees + " cartes que vous pouvez voler : \n");
		for (int i = 0; i < nbCartesVolees; i++) {
			boolean dejaUtilise;
			do {
				indicesCartes[i] = random.nextInt(mainVictime.length);
				dejaUtilise = false;
				for (int j = 0; j < i; j++) {
					if (indicesCartes[i] == indicesCartes[j]) {
						dejaUtilise = true;
					}
				}
			} while (dejaUtilise);
			System.out.println("Carte : " + mainVictime[indicesCartes[i]]);
		}

		return indicesCartes;
	}

	public int[] recupererCartesEchangees(int nbCartesVolees, String[] mainAttaquant, String nomAttaquant,String[] mainVictime,int[] indicesCartesVolables) {

		int[] cartesVolees = new int[nbCartesVolees];

		System.out.println("\nVoici votre main : ");
		afficherMain(mainAttaquant);
		for (int i = 0; i < nbCartesVolees; i++) {
			System.out.println("Echange de carte numéro " + (i + 1) + ".\n");
			boolean dejaUtilise;
			do {
				System.out.println(
						"Quelle carte voulez-vous donner en échange de : ("+mainVictime[indicesCartesVolables[i]]+")\n(tapez 0 pour ne pas voler cette carte)");
				cartesVolees[i] = scanner.nextInt();
				dejaUtilise = false;
				for (int j = 0; j < i; j++) {
					if ((cartesVolees[i] != 0 && cartesVolees[i] == cartesVolees[j])) {
						dejaUtilise = true;
					}
				}
			} while (dejaUtilise || cartesVolees[i] < 0 || cartesVolees[i] > mainAttaquant.length);
		}

		return cartesVolees;

	}

}
