package id.ac.ui.cs.pustakaone.bookshop.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BookCartTest {
    Book book;
    Cart cart;
    @BeforeEach
    void setup() {
        String userId = "d8sd-d9sd-og0w-ds89-cvxk";
        this.book = new Book();
        this.cart = new Cart(userId);
    }

    @Test
    public void createNewBookCart() {
        BookCart bookCart = new BookCart(book, cart, 1);
        assertNotNull(bookCart);
        assertEquals(1, bookCart.getAmount());
    }

    @Test
    public void testGetBookIdAfterCreateBookCart() {
        BookCart bookCart = new BookCart(book, cart, 1);
        assertEquals(book, bookCart.getBook());
    }
    @Test
    public void testIncrementAmount() {
        BookCart bookCart = new BookCart(book, cart, 1);
        assertEquals(1, bookCart.getAmount());
        bookCart.incrementAmount();
        assertEquals(2, bookCart.getAmount());
    }

    @Test
    public void testDecrementAmount() {
        BookCart bookCart = new BookCart(book, cart, 2);
        assertEquals(2, bookCart.getAmount());
        bookCart.decrementAmount();
        assertEquals(1, bookCart.getAmount());
    }

    @Test
    public void testNegativeAmountValue() {
        BookCart bookCart = new BookCart(book, cart, 1);
        assertThrows(IllegalStateException.class, bookCart::decrementAmount);
    }

    @Test
    public void testOverflowAmountValue() {
        BookCart bookCart = new BookCart(book, cart, book.getStock());
        assertThrows(IllegalStateException.class, bookCart::incrementAmount);
    }
}