package cartes;

public enum TypeCarte {
	AbordageReussi("Abordage Reussi"), 
	DiscoursInspirant("Discours Inspirant"),
	MainDeFer("Main de fer"),
	CoupDeSabre("Coup de Sabre"),
	CarteVole("Carte vole"),
	CarteRegen("Carte regen");
	private String nomCarte;

	private TypeCarte(String nomCarte) {
		this.nomCarte = nomCarte;
	}


}
