package cartes;

public enum TypeSpecial implements TypeCarte {
	ChangementDeck("Changement de Deck");

	;

	private String nomCarte;

	private TypeSpecial(String nomCarte) {
		this.nomCarte = nomCarte;
	}

	@Override
	public String getNomCarte() {

		return nomCarte;
	}

}
