package id.ac.ui.cs.pustakaone.bookshop.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

public class CartTest {
    String userId;
    @BeforeEach
    void setup() {
        this.userId = "dos9-dwke-we9d-vjdi-oid9";
    }


    @Test
    public void testCreateCart() {
        Cart cart = new Cart(userId);
        assertNotNull(cart);
    }

    @Test
    public void testInitialValue() {
        Cart cart = new Cart(userId);
        assertEquals(userId, cart.getUserId());
        assertEquals(0, cart.getTotalPrice());
        assertFalse(cart.isPaymentSuccess());
        assertEquals("belum",cart.getStatus());
        assertEquals("", cart.getAddress());
    }

    @Test
    public void testSetTotalPrice() {
        Cart cart = new Cart(userId);
        cart.setTotalPrice(500);
        assertEquals(500, cart.getTotalPrice());
    }

    @Test
    public void testSetNegativeTotalPrice() {
        Cart cart = new Cart(userId);
        assertThrows(IllegalArgumentException.class, () -> {
            cart.setTotalPrice(-1);
        });
    }

    @Test
    public void testSetPaymentStatus() {
        Cart cart = new Cart(userId);
        cart.setPaymentSuccess(true);
        assertTrue(cart.isPaymentSuccess());
    }

    @Test
    public void testSetAddresss() {
        Cart cart = new Cart(userId);
        String address = "Jalan Depok";
        cart.setAddress(address);
        assertEquals(address, cart.getAddress());
    }

    @Test
    public void testSetPaidAt() {
        Cart cart = new Cart(userId);
        Date paidAt = new Date();
        cart.setPaidAt(paidAt);
        assertEquals(paidAt, cart.getPaidAt());
    }
}