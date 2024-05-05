package id.ac.ui.cs.pustakaone.bookshop.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import id.ac.ui.cs.pustakaone.bookshop.model.Book;
import id.ac.ui.cs.pustakaone.bookshop.model.BookCart;
import id.ac.ui.cs.pustakaone.bookshop.model.Cart;
import id.ac.ui.cs.pustakaone.bookshop.repository.BookCartRepository;
import id.ac.ui.cs.pustakaone.bookshop.repository.BookRepository;
import id.ac.ui.cs.pustakaone.bookshop.repository.CartRepository;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class BookCartServiceImplTest {

    @Mock
    private BookCartRepository bookCartRepository;
    @Mock
    private CartRepository cartRepository;
    @Mock
    private BookRepository bookRepository;
    @Mock
    private CartServiceImpl cartService;

    @InjectMocks
    private BookCartServiceImpl bookCartService;

    @Test
    void testAddBookToCart() {
        Long userId = 1L;
        Long bookId = 1L;
        int amount = 1;
        Cart cart = new Cart(userId);
        Book book = new Book();
        book.setBookId(bookId);
        book.setStock(5);

        when(cartService.getCartByUserId(userId)).thenReturn(cart);
        when(bookRepository.findById(bookId)).thenReturn(Optional.of(book));

        BookCart bookCart = bookCartService.addBookToCart(userId, bookId, amount);

        assertNotNull(bookCart);
        assertEquals(bookId, bookCart.getBook().getBookId());
        assertEquals(amount, bookCart.getAmount());
        verify(bookCartRepository).save(bookCart);
        verify(cartRepository).save(cart);
    }

    @Test
    void testAddBookToCart_StockExceededException() {
        Long userId = 1L;
        Long bookId = 1L;
        int amount = 6;
        Cart cart = new Cart(userId);
        Book book = new Book();
        book.setBookId(bookId);
        book.setStock(5);

        when(cartService.getCartByUserId(userId)).thenReturn(cart);
        when(bookRepository.findById(bookId)).thenReturn(Optional.of(book));

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            bookCartService.addBookToCart(userId, bookId, amount);
        });

        assertEquals("Amount exceeds stock available", exception.getMessage());
    }

    @Test
    void testUpdateBookAmountInCart() {
        Long userId = 1L;
        Long bookCartId = 1L;
        int newAmount = 2;
        Cart cart = new Cart(userId);
        Book book = new Book();
        book.setStock(3);
        BookCart bookCart = new BookCart(book, cart, 1);

        when(cartService.getCartByUserId(userId)).thenReturn(cart);
        when(bookCartRepository.findById(bookCartId)).thenReturn(Optional.of(bookCart));

        bookCartService.updateBookAmountInCart(userId, bookCartId, newAmount);

        assertEquals(newAmount, bookCart.getAmount());
        verify(cartRepository).save(cart);
        verify(bookCartRepository).save(bookCart);
    }

    @Test
    void testUpdateBookAmountInCart_InvalidAmountException() {
        Long userId = 1L;
        Long bookCartId = 1L;
        int newAmount = 4;
        Cart cart = new Cart(userId);
        Book book = new Book();
        book.setStock(3);
        BookCart bookCart = new BookCart(book, cart, 1);

        when(cartService.getCartByUserId(userId)).thenReturn(cart);
        when(bookCartRepository.findById(bookCartId)).thenReturn(Optional.of(bookCart));

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            bookCartService.updateBookAmountInCart(userId, bookCartId, newAmount);
        });

        assertEquals("Invalid amount", exception.getMessage());
    }
}
