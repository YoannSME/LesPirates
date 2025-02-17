package testFonctionnels;

import jeu.Jeu;
import cartes.*;
public class Test {

	public static void main(String[] args) {
		
		Jeu jeu = new Jeu();
		
		
		Carte[] cartes = new CartePopularite[5];
		CartePopularite mainDeFer = new CartePopularite(TypeCarte.MainDeFer,1,2);
		for(int i = 0;i<5;i++) {
			cartes[i] = mainDeFer;
		}
		
		StringBuilder sb = new StringBuilder();
		for(int i = 0;i<5;i++) {
			sb.append(i+" - ");
			sb.append(cartes[i].getType()+"\n");
		}
		
		System.out.println(sb.toString());
		
		
		
		
		
	}

}
