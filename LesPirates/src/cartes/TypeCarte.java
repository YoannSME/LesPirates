package cartes;

public enum TypeCarte {
	AbordageReussi("Abordage Reussi"), DiscoursInspirant("Discours Inspirant"), MainDeFer("Main de fer"),CoupDeSabre("Coup de Sabre");
	private final String nomCarte;

	private TypeCarte(String nomCarte) {
		this.nomCarte = nomCarte;
	}

	
	public String getNomCarte() {
		return nomCarte;
	}


}
