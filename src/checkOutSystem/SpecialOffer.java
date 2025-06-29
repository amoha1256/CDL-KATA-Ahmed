package checkOutSystem;

public class SpecialOffer {

	private int unitPrice;
	private int specialQuantity;
	private int specialPrice;

	public SpecialOffer(int unitPrice, int specialQuantity, int specialPrice) {
		this.unitPrice = unitPrice;
		this.specialQuantity = specialQuantity;
		this.specialPrice = specialPrice;
	}

	public int getUnitPrice() {
		return unitPrice;
	}

	public int getSpecialQuantity() {
		return specialQuantity;
	}

	public int getSpecialPrice() {
		return specialPrice;
	}
}
