package testFonctionnels;

import jeu.Jeu;

import cartes.*;
public class Test {

	public static void main(String[] args) {
		int TAILLE_AFFICHAGE = 13;
		Jeu jeu = new Jeu();
		
		
		Carte[] cartes = new CartePopularite[5];
		CartePopularite mainDeFer = new CartePopularite(TypeCarte.MainDeFer,1,2,"MAIN DE FER");
		for(int i = 0;i<5;i++) {
			cartes[i] = mainDeFer;
		}
		
		StringBuilder sb = new StringBuilder();
		for(int i = 0;i<5;i++) {
			sb.append(i+" - ");
			sb.append(cartes[i].getType()+"\n");
		}
		
		System.out.println(sb.toString());
		
		System.out.println("*".repeat(TAILLE_AFFICHAGE*2));
		System.out.print("* \t\t\t *\n".repeat(TAILLE_AFFICHAGE));
		System.out.println("*".repeat(TAILLE_AFFICHAGE*2));
		
		
		
		
	}

}
