package cartes;

public enum TypeCarte {
	ABORDAGE_REUSSI("Abordage Reussi"), DISCOURS_INSPIRANT("Discours Inspirant"), MAIN_DE_FER("Main de Fer"),
	COUP_DE_SABRE("Coup de Sabre"), VOL_DE_CARTE("Vol de cartes"), REGENERATION_HP("Regeneration HP"),
	LANGUE_DE_SERPENT("Langue de Serpent"), PILE_OU_FACE("Pile ou Face"),SWITCH("Carte Switch");

	private final String nom;

	private TypeCarte(String nom) {
		this.nom = nom;
	}

	public String getNom() {
		return nom;
	}

}
