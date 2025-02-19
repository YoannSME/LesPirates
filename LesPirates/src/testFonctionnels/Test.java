package testFonctionnels;

import jeu.Jeu;
import affichage.Affichage;
import cartes.*;

public class Test {
	public Affichage affichage = new Affichage();

	public void testEchangerCartes() {
		String[] mainA = new String[] { "MainDeFer", "CoupDeSabre", "abra", "DiscoursInspirant" };
		String[] mainB = new String[] { "AAAA", "BBBB", "CCC", "DDDD" };
		int[] a = affichage.recupererCartesVolables(2, mainA,"DEFENSE");
		int[] b = affichage.recupererCartesEchangees(2, mainB, "ATTAQUE",mainA,a);

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

	public static void main(String[] args) {

		Test test = new Test();
		test.testEchangerCartes();
		
		

	}

}
