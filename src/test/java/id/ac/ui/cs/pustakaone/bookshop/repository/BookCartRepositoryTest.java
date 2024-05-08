package id.ac.ui.cs.pustakaone.bookshop.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import id.ac.ui.cs.pustakaone.bookshop.model.Book;
import id.ac.ui.cs.pustakaone.bookshop.model.BookCart;
import id.ac.ui.cs.pustakaone.bookshop.model.Cart;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class BookCartRepositoryTest {

    @Autowired
    private BookCartRepository bookCartRepository;

    @Autowired
    private CartRepository cartRepository;

    private Book book;
    private Cart cart;
    private BookCart bookCart;

    @BeforeEach
    public void setUp() {
        book = new Book();
        book.setStock(10);
        cart = new Cart(1L);
        bookCart = new BookCart(book, cart, 2);
    }

    @Test
    public void testSaveAndRetrieveBookCart() {
        cartRepository.save(cart);
        bookCartRepository.save(bookCart);

        BookCart savedBookCart = bookCartRepository.findById(bookCart.getId()).orElse(null);

        assertNotNull(savedBookCart);
        assertEquals(bookCart.getAmount(), savedBookCart.getAmount());
        assertNotNull(savedBookCart.getCart());
    }

    @Test
    public void testUpdateBookCartAmount() {
        cartRepository.save(cart);
        bookCartRepository.save(bookCart);

        bookCart.setAmount(3);
        bookCartRepository.save(bookCart);

        BookCart updatedBookCart = bookCartRepository.findById(bookCart.getId()).orElse(null);

        assertNotNull(updatedBookCart);
        assertEquals(3, updatedBookCart.getAmount());
    }
}
