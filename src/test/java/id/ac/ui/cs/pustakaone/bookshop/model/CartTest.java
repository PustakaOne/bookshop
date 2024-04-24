package id.ac.ui.cs.pustakaone.bookshop.model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CartTest {

    private Cart cart;

    @BeforeEach
    public void setUp() {
        cart = new Cart("cartId");
    }

    @Test
    public void testGetTotalPrice() {
        cart.setTotalPrice(500);
        assertEquals(500, cart.getTotalHarga(), "Total price should be 500");
    }

    @Test
    public void testInitialState() {
        assertEquals("not paid", cart.getPaymentStatus(), "Initial state should be 'not paid'");
    }

    @Test
    public void testProcessPayment() {
        cart.processPayment();
        assertEquals("processed", cart.getPaymentStatus(), "After processing, status should be 'processed'");
    }

    @Test
    public void testCompletePaymentWithoutProcessing() {
        cart.completePayment();
        assertEquals("not paid", cart.getPaymentStatus(), "Trying to complete payment without processing should fail and remain 'not paid'");
    }

    @Test
    public void testCompletePaymentAfterProcessing() {
        cart.processPayment();
        cart.completePayment();
        assertEquals("paid", cart.getPaymentStatus(), "After processing and completing, status should be 'paid'");
    }

    @Test
    public void testAddBookCart() {
        BookCart bookCart = new BookCart("bookCartId", new Book("bookId"), cart, 1);
        cart.addBookCart(bookCart);
        assertEquals(1, cart.getBookCarts().size(), "Cart should have one book cart");
        assertTrue(cart.getBookCarts().contains(bookCart), "Cart should contain the added book cart");
    }

    @Test
    public void testProcessPaymentOnProcessedState() {
        cart.processPayment();
        cart.processPayment();
        assertEquals("processed", cart.getPaymentStatus(), "Attempting to process payment again should keep status 'processed'");
    }

    @Test
    public void testCompletePaymentOnPaidState() {
        cart.processPayment();
        cart.completePayment();
        cart.completePayment();
        assertEquals("paid", cart.getPaymentStatus(), "Attempting to complete payment again should keep status 'paid'");
    }
}
