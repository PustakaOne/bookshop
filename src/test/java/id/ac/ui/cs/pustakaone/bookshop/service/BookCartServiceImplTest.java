package id.ac.ui.cs.pustakaone.bookshop.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import id.ac.ui.cs.pustakaone.bookshop.model.Book;
import id.ac.ui.cs.pustakaone.bookshop.model.BookCart;
import id.ac.ui.cs.pustakaone.bookshop.model.Cart;
import id.ac.ui.cs.pustakaone.bookshop.repository.BookCartRepository;
import id.ac.ui.cs.pustakaone.bookshop.repository.BookRepository;
import id.ac.ui.cs.pustakaone.bookshop.repository.CartRepository;

import id.ac.ui.cs.pustakaone.bookshop.service.BookCartServiceImpl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Optional;

public class BookCartServiceImplTest {

    @Mock
    private BookCartRepository bookCartRepository;

    @Mock
    private CartRepository cartRepository;

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookCartServiceImpl service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAddBookToCart() {
        Long cartId = 1L;
        Long bookId = 1L;
        int amount = 1;

        Cart cart = new Cart();
        cart.setBookCarts(new ArrayList<>()); // Ensure cart has an empty list of bookCarts
        Book book = new Book();
        book.setStock(10);

        when(cartRepository.findById(cartId)).thenReturn(Optional.of(cart));
        when(bookRepository.findById(bookId)).thenReturn(Optional.of(book));
        when(bookCartRepository.save(any(BookCart.class))).thenAnswer(invocation -> invocation.getArgument(0));

        BookCart result = service.addBookToCart(cartId, bookId, amount);

        assertNotNull(result);
        assertEquals(amount, result.getAmount());
        assertTrue(cart.getBookCarts().contains(result));
        verify(bookCartRepository).save(any(BookCart.class));
        verify(cartRepository).save(cart);
    }

    @Test
    void testUpdateBookAmountInCart() {
        BookCart bookCart = new BookCart();
        bookCart.setId(1L);
        bookCart.setAmount(3);
        Book book = new Book();
        book.setStock(5);
        bookCart.setBook(book);
        when(bookCartRepository.findById(1L)).thenReturn(Optional.of(bookCart));
        when(bookCartRepository.save(any(BookCart.class))).thenReturn(bookCart);

        BookCart updatedCart = service.updateBookAmountInCart(1L, 4);
        assertEquals(4, updatedCart.getAmount());
        verify(bookCartRepository).save(bookCart);
    }

}
