package cartes;

public enum TypePopularite implements TypeCarte {
	AbordageReussi("Abordage Reussi"), DiscoursInspirant("Discours Inspirant"), MainDeFer("Main de fer");

	private final String nomCarte;

	private TypePopularite(String nomCarte) {
		this.nomCarte = nomCarte;
	}

	@Override
	public String getNomCarte() {
		return nomCarte;
	}

}
