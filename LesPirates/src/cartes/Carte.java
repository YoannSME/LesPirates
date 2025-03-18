package cartes;

import affichage.Affichage;
import affichage.IAffichage;
import pirate.Pirate;

public abstract class Carte {
	public static final IAffichage affichage = new Affichage();
	private TypeCarte typeCarte;
	private String description;

	protected Carte(TypeCarte typeCarte, String description) {
		this.typeCarte = typeCarte;
		this.description = description;
	}

	public TypeCarte getType() {
		return typeCarte;
	}

	public String getDescription() {
		return description;
	}
	
	public abstract void effetCarte(Pirate attaquant, Pirate victime);

}
