package cartes;

public class CarteRegeneration extends Carte {
	private int pvRecuperees;

	public CarteRegeneration(TypeCarte typeCarte, int pvRecuperees, String description) {
		super(typeCarte, description);
		this.pvRecuperees = pvRecuperees;
	}

	public int getPvRecuperees() {
		return pvRecuperees;
	}

}
