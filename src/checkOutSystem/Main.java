package checkOutSystem;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {

		Map<Character, SpecialOffer> rules = new HashMap<>();
		rules.put('A', new SpecialOffer(50, 3, 130));
		rules.put('B', new SpecialOffer(30, 2, 45));
		rules.put('C', new SpecialOffer(20, 1, 20));
		rules.put('D', new SpecialOffer(15, 1, 15));

		CheckOut checkout = new CheckOut(rules);

		try (Scanner scanner = new Scanner(System.in)) {

			System.out.println(
					"Please enter your items (for example, 'ABCD' for items A, B, C, and D) or type 'done' to finish):");

			while (true) {
				String input = scanner.nextLine().trim();

				if (input.equalsIgnoreCase("done")) {
					break;
				}

				for (char item : input.toCharArray()) {
					try {
						checkout.scan(item);
					} catch (IllegalArgumentException e) {
						System.err.println(e.getMessage());
					}
				}
			}
		}

		int totalPrice = checkout.calculateTotal();
		System.out.println("Total: " + totalPrice);
	}
}