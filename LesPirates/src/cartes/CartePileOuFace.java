package cartes;

public class CartePileOuFace extends Carte {
	private int probabilite;

	public CartePileOuFace(TypeCarte typeCarte, String description, int probabilite) {
		super(typeCarte, description);
		this.probabilite = probabilite;
	}
	
	public int getProbabilite() {
		return probabilite;
	}

}
