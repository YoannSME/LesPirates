package cartes;

public abstract class Carte {
	private TypeCarte typeCarte;
	private String description;
	protected Carte(TypeCarte typeCarte,String description) {
		this.typeCarte = typeCarte;
		this.description = description;
	}

	public TypeCarte getType() {
		return typeCarte;
	}
	
	public String getDescription() {
		return description;
	}

}
