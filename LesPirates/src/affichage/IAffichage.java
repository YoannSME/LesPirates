package affichage;

import cartes.TypeCarte;

public interface IAffichage {

	void afficherMain(String[] main);

	int afficherChoisirCarte(int nbCartes);

	void detailCarte(TypeCarte typeCarte, String texte);

	void afficherPiocherCarte(int taille_max, String nomPirate, int nbCartes, TypeCarte typeCarte);

	void afficherAttaquePirate(String attaquant, String victime);

	void afficherPerdreVie(String nomPirate, int degats, int pv);

	void afficherEffetCartePopularite(String nomPirate, int nbPopulariteCarte, int nbDegats, int nbPopularitePirate,
			int pv);

	void afficherEffetCarteRegeneration(String nomPirate, int pvRecuperees);

	void afficherGagnerPartie(String nomPirate, int pv, int popularite);

	void afficherDebutTour(String nomPirate);

	void afficherFinTour(String nomPirate, int pvPirate, int popularitePirate);

	int[] recupererCartesVolables(int nbCartesVolees, String[] mainVictime, String nomVictime);

	int[] recupererCartesEchangees(int nbCartesVolees, String[] mainAttaquant, String nomAttaquant,
			String[] mainVictime, int[] indicesCartesVolables);

}