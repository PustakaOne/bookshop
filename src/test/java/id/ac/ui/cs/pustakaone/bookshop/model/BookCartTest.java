package id.ac.ui.cs.pustakaone.bookshop.model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class BookCartTest {

    @Test
    public void testIncrementAmount() {
        BookCart bookCart = new BookCart("Id", "bookId", "cartId");
        bookCart.incrementAmount();
        assertEquals(2, bookCart.getAmount());
    }

    @Test
    public void testDecrementAmount() {
        BookCart bookCart = new BookCart("Id", "bookId", "cartId");
        bookCart.decrementAmount();
        assertEquals(1, bookCart.getAmount());
    }

    @Test
    public void testGetBookCartDetail() {
        BookCart bookCart = new BookCart("Id", "bookId", "cartId");
        assertEquals("bookId", bookCart.getBookCartDetail());
    }
}