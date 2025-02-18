package cartes;

public class CarteRegeneration extends Carte {
	private int pvRecuperees;
	public CarteRegeneration(TypeCarte typeCarte, String description,int pvRecuperees) {
		super(typeCarte,description);
		this.pvRecuperees = pvRecuperees;
	}
	
	public int getPvRecuperees() {
		return pvRecuperees;
	}

}
