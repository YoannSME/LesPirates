package affichage;

import pirate.Pirate;

public class Affichage {

	
	public void afficherMain(Pirate pirate) {
		System.out.println("Main du Pirate : "+pirate.getNom());
		for(int i = 0;i<pirate.getNbCartes();i++) {
			System.out.println("1 - "+pirate.getMain()[i].getType());
		}
		
	}

}
