package id.ac.ui.cs.pustakaone.bookshop.model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class BookCartTest {

    @Test
    public void testIncrementAmount() {
        BookCart bookCart = new BookCart("Id", new Book("bookId"), new Cart("cartId"), 1);
        bookCart.incrementAmount();
        assertEquals(2, bookCart.getAmount());
    }

    @Test
    public void testDecrementAmount() {
        BookCart bookCart = new BookCart("Id", new Book("bookId"), new Cart("cartId"), 2);
        bookCart.decrementAmount();
        assertEquals(1, bookCart.getAmount());
    }

    @Test
    public void testGetBookCartDetail() {
        Book book = new Book("bookId");
        Cart cart = new Cart("cartId");
        BookCart bookCart = new BookCart("Id", book, cart, 1);
        assertEquals("Id", bookCart.getBookCartDetail());
    }
}