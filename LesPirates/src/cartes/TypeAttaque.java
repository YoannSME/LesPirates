package cartes;

public enum TypeAttaque implements TypeCarte {

	CoupDeSabre("Coup de Sabre");

	private final String nomCarte;

	private TypeAttaque(String nomCarte) {
		this.nomCarte = nomCarte;
	}

	@Override
	public String getNomCarte() {
		return nomCarte;
	}

}
