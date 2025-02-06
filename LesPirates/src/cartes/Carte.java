package cartes;

public abstract class Carte {
	private TypeCarte typeCarte;

	protected Carte(TypeCarte typeCarte) {
		this.typeCarte = typeCarte;
	}
	
	public TypeCarte getType() {
		return typeCarte;
	}

}
