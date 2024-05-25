package id.ac.ui.cs.pustakaone.bookshop.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BookCartTest {
    private Book book;
    private Cart cart;

    @BeforeEach
    void setup() {
        Long userId = 1L;
        this.book = new Book();
        this.book.setStock(10);
        this.cart = new Cart(1L);
    }

    @Test
    public void createNewBookCart() {
        BookCart bookCart = new BookCart(book, cart, 1);
        assertNotNull(bookCart);
        assertEquals(1, bookCart.getAmount());
        assertEquals(book, bookCart.getBook());
        assertEquals(cart, bookCart.getCart());
    }

    @Test
    public void testSetId() {
        BookCart bookCart = new BookCart(book, cart, 1);
        bookCart.setId(100L);
        assertEquals(100L, bookCart.getId());
    }

    @Test
    public void testSetBook() {
        BookCart bookCart = new BookCart(book, cart, 1);
        Book newBook = new Book();
        bookCart.setBook(newBook);
        assertEquals(newBook, bookCart.getBook());
    }

    @Test
    public void testSetCart() {
        BookCart bookCart = new BookCart(book, cart, 1);
        Cart newCart = new Cart(2L);
        bookCart.setCart(newCart);
        assertEquals(newCart, bookCart.getCart());
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
