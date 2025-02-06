package cartes;

public enum TypePopularite implements TypeCarte {
	AbordageReussi("Abordage Reussi"),
	DiscoursInspirant("Discours Inspirant");
	
	private final String nomCarte;
	private TypePopularite(String nomCarte) {
		this.nomCarte = nomCarte;
	}
	@Override
	public String getNomCarte() {
		return nomCarte;
	}

}
