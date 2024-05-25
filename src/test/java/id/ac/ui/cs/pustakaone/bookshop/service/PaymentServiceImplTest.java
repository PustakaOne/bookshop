package id.ac.ui.cs.pustakaone.bookshop.service;

import id.ac.ui.cs.pustakaone.bookshop.model.Book;
import id.ac.ui.cs.pustakaone.bookshop.model.BookCart;
import id.ac.ui.cs.pustakaone.bookshop.model.Cart;
import id.ac.ui.cs.pustakaone.bookshop.repository.BookRepository;
import id.ac.ui.cs.pustakaone.bookshop.repository.CartRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PaymentServiceImplTest {

    @Mock
    private BookRepository bookRepository;

    @Mock
    private CartRepository cartRepository;

    @InjectMocks
    private PaymentServiceImpl paymentService;

    private Cart cart;
    private List<BookCart> bookCarts;
    private Book book;
    private BookCart bookCart;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        book = new Book();
        book.setStock(10);

        bookCart = new BookCart();
        bookCart.setBook(book);
        bookCart.setAmount(2);

        bookCarts = new ArrayList<>();
        bookCarts.add(bookCart);

        cart = new Cart();
        cart.setBookCarts(bookCarts);
    }

    @Test
    void processPayment_ShouldUpdateBookStockAndCartStatus() {
        paymentService.processPayment(cart);

        assertEquals(8, book.getStock());
        assertEquals("menunggu pengiriman", cart.getStatus());
        assertTrue(cart.isPaymentSuccess());
        assertNotNull(cart.getPaidAt());

        verify(bookRepository, times(1)).save(book);
        verify(cartRepository, times(1)).save(cart);
    }
}
