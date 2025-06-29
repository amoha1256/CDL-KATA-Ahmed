package checkOutSystemTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import checkOutSystem.*;
public class CheckOutTest {

    private checkOutSystem.CheckOut checkout;

    @BeforeEach
    public void setUp() {
        Map<Character, SpecialOffer> rules = new HashMap<>();
        rules.put('A', new SpecialOffer(50, 3, 130));
        rules.put('B', new SpecialOffer(30, 2, 45));
        rules.put('C', new SpecialOffer(20, 1, 20));
        rules.put('D', new SpecialOffer(15, 1, 15));

        checkout = new CheckOut(rules);
    }

    @Test
    public void testSingleItems() {
        checkout.scan('A');
        checkout.scan('B');
        checkout.scan('C');
        checkout.scan('D');
        assertEquals(50 + 30 + 20 + 15, checkout.calculateTotal());
    }

    @Test
    public void testSpecialOfferA() {
        checkout.scan('A');
        checkout.scan('A');
        checkout.scan('A');
        assertEquals(130, checkout.calculateTotal());
    }

    @Test
    public void testSpecialOfferB() {
        checkout.scan('B');
        checkout.scan('B');
        assertEquals(45, checkout.calculateTotal());
    }

    @Test
    public void testMixedItemsWithOffers() {
        checkout.scan('A');
        checkout.scan('A');
        checkout.scan('A');
        checkout.scan('B');
        checkout.scan('B');
        checkout.scan('C');
        assertEquals(130 + 45 + 20, checkout.calculateTotal());
    }

    @Test
    public void testInvalidItemThrowsException() {
        try {
            checkout.scan('X');
        } catch (IllegalArgumentException e) {
            assertEquals("Invalid item: X", e.getMessage());
        }
    }
}