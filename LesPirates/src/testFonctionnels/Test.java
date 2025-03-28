package testFonctionnels;

import jeu.Jeu;

import java.util.Random;

import affichage.Affichage;
import affichage.IAffichage;
import cartes.*;

public class Test {
	IAffichage affichage = new Affichage();
	Jeu jeu = new Jeu();

	public void testEchangerCartes() {
		String[] mainA = new String[] { "MainDeFer", "CoupDeSabre", "abra", "DiscoursInspirant" };
		String[] mainB = new String[] { "AAAA", "BBBB", "CCC", "DDDD" };
		int[] a = affichage.recupererCartesVolables(2, mainA, "DEFENSE");
		int[] b = affichage.recupererCartesEchangees(2, mainB, "ATTAQUE", mainA, a);

		for (int i = 0; i < 2; i++) {
			System.out.println("A PRENDRE : " + a[i]);
		}
		for (int i = 0; i < 2; i++) {
			System.out.println("A ECHANGE : " + b[i]);
		}

		for (int i = 0; i < 2; i++) {
			if (b[i] != 0) {
				String carteA = mainA[a[i]];
				String carteB = mainB[b[i] - 1];

				mainA[a[i]] = carteB;
				mainB[b[i] - 1] = carteA;
			}
		}

		affichage.afficherMain(mainA);
		affichage.afficherMain(mainB);
	}

	public void testRemplirPioche() {
		CarteRegeneration carteRegen = new CarteRegeneration(TypeCarte.REGENERATION_HP, 2, "Le pirate récupère 2PV");
		jeu.getPioche().remplirPioche(carteRegen, 50);
		System.out.println(jeu.getPioche().getNbCartes());
		System.out.println(jeu.getPioche().getNbCartes() == 50);
	}

	public void testViderPioche() {
		CarteRegeneration carteRegen = new CarteRegeneration(TypeCarte.REGENERATION_HP, 2, "Le pirate récupère 2PV");
		jeu = new Jeu();
		jeu.getPioche().remplirPioche(carteRegen, 20);

		for (int i = 0; i < 20; i++) {
			System.out.println(jeu.getPioche().piocherCarte() != null);
		}

		assert (jeu.getPioche().piocherCarte() == null);

	}

	public static void main(String[] args) {

		Test test = new Test();

		test.testEchangerCartes();
		//test.testRemplirPioche();
		//test.testViderPioche();
		//Random random = new Random();
		//System.out.println(random.nextInt(3));

	}

}
