package cartes;

public enum TypeCarte {
	AbordageReussi("Abordage Reussi"), DiscoursInspirant("Discours Inspirant"), MainDeFer("Main de fer"),CoupDeSabre("Coup de Sabre");
	private String description;

	private TypeCarte(String description) {
		this.description = description;
	}
	public String getDescription() {
		return description;
	}

}
