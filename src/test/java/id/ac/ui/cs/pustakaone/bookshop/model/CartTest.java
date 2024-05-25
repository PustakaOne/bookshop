package id.ac.ui.cs.pustakaone.bookshop.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

public class CartTest {
    Long userId;

    @BeforeEach
    void setup() {
        this.userId = 1L;
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
        assertEquals("belum", cart.getStatus());
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
    public void testSetAddress() {
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

    @Test
    public void testAddBookCart() {
        Cart cart = new Cart(userId);
        BookCart bookCart = new BookCart();
        cart.addBookCart(bookCart);
        assertTrue(cart.getBookCarts().contains(bookCart));
    }

    @Test
    public void testRemoveBookCart() {
        Cart cart = new Cart(userId);
        BookCart bookCart = new BookCart();
        cart.addBookCart(bookCart);
        cart.removeBookCart(bookCart);
        assertFalse(cart.getBookCarts().contains(bookCart));
    }

    @Test
    public void testSetId() {
        Cart cart = new Cart(userId);
        cart.setId(100L);
        assertEquals(100L, cart.getId());
    }

    @Test
    public void testSetLastId() {
        Cart cart = new Cart(userId);
        cart.setLastId(200L);
        assertEquals(200L, cart.getLastId());
    }

    @Test
    public void testSetUserId() {
        Cart cart = new Cart(userId);
        cart.setUserId(300L);
        assertEquals(300L, cart.getUserId());
    }

    @Test
    public void testConstructor() {
        Cart cart = new Cart();
        assertNotNull(cart);
    }
}
