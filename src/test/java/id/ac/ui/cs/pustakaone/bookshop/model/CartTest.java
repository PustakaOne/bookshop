package id.ac.ui.cs.pustakaone.bookshop.model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class CartTest {

    @Test
    public void testGetTotalPrice() {
        Cart cart = new Cart("cartId");
        cart.setTotalPrice(500);
        assertEquals(500, cart.getTotalHarga());
    }

    @Test
    public void testSetPaymentStatus() {
        Cart cart = new Cart("cartId");
        cart.setPaymentStatus(true);
        assertTrue(cart.getPaymentStatus());
    }

    @Test
    public void testGetBookCarts() {
        Cart cart = new Cart("cartId");
        BookCart bookCart = new BookCart("bookCartId", "bookId", "cartId");
        cart.addBookCart(bookCart);
        assertEquals(1, cart.getBookCarts().size());
    }
}