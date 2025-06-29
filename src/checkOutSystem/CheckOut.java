package checkOutSystem;

import java.util.HashMap;
import java.util.Map;

public class CheckOut {

	private Map<Character, SpecialOffer> pricingRules = new HashMap<>();
	private Map<Character, Integer> itemCounts = new HashMap<>();

	public CheckOut(Map<Character, SpecialOffer> pricingRules) {
		this.pricingRules = pricingRules;
	}

	public void scan(char item) {

		pricingRules.computeIfAbsent(item, key -> {
			throw new IllegalArgumentException("Invalid item: " + key);
		});
		itemCounts.put(item, itemCounts.getOrDefault(item, 0) + 1);
	}

	public int calculateTotal() {
		int total = 0;
		for (Map.Entry<Character, Integer> entry : itemCounts.entrySet()) {
			char item = entry.getKey();
			int count = entry.getValue();

			SpecialOffer offers = pricingRules.get(item);
			int unitPrice = offers.getUnitPrice();
			int specialPrice = offers.getSpecialPrice();
			int specialQuantity = offers.getSpecialQuantity();

			if (specialQuantity > 0) {
				int specialOffersCount = count / specialQuantity;
				int remainingItems = count % specialQuantity;
				total += (specialOffersCount * specialPrice) + (remainingItems * unitPrice);
			} else {
				total += count * unitPrice;
			}
		}
		return total;
	}
}