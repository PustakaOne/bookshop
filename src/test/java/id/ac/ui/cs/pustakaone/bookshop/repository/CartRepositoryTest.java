package id.ac.ui.cs.pustakaone.bookshop.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import id.ac.ui.cs.pustakaone.bookshop.model.Book;
import id.ac.ui.cs.pustakaone.bookshop.model.BookCart;
import id.ac.ui.cs.pustakaone.bookshop.model.Cart;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY) // This will replace your DataSource with an embedded database
public class CartRepositoryTest {

    @Autowired
    private CartRepository cartRepository;


    @Autowired
    private BookCartRepository bookCartRepository;

    private Cart cart;
    private Book book;
    private BookCart bookCart;

    @BeforeEach
    public void setUp() {
        cart = new Cart(1L);
        cart.setAddress("123 Elm St");
        cart.setStatus("belum");

        book = new Book();
        book.setStock(10);

        bookCart = new BookCart(book, cart, 2);
    }

    @Test
    public void testSaveAndRetrieveCart() {
        cartRepository.save(cart);
        Cart savedCart = cartRepository.findById(cart.getId()).orElse(null);

        assertNotNull(savedCart);
        assertEquals(cart.getUserId(), savedCart.getUserId());
        assertEquals(cart.getAddress(), savedCart.getAddress());
    }

    @Test
    public void testAddAndRemoveBookCart() {
        cartRepository.save(cart);
        bookCartRepository.save(bookCart);
        cart.addBookCart(bookCart);

        cartRepository.save(cart);

        Cart updatedCart = cartRepository.findById(cart.getId()).orElse(null);
        assertNotNull(updatedCart);
        assertTrue(updatedCart.getBookCarts().contains(bookCart));

        updatedCart.removeBookCart(bookCart);
        bookCartRepository.delete(bookCart);
        cartRepository.save(updatedCart);

        updatedCart = cartRepository.findById(cart.getId()).orElse(null);
        assertNotNull(updatedCart);
        assertFalse(updatedCart.getBookCarts().contains(bookCart));
    }

    @Test
    public void testDeleteCart() {
        cartRepository.save(cart);
        cartRepository.delete(cart);
        Cart deletedCart = cartRepository.findById(cart.getId()).orElse(null);

        assertEquals(null, deletedCart);
    }
}
