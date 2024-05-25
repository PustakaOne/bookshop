package id.ac.ui.cs.pustakaone.bookshop.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import id.ac.ui.cs.pustakaone.bookshop.model.Book;
import id.ac.ui.cs.pustakaone.bookshop.model.BookCart;
import id.ac.ui.cs.pustakaone.bookshop.model.Cart;
import id.ac.ui.cs.pustakaone.bookshop.repository.BookCartRepository;
import id.ac.ui.cs.pustakaone.bookshop.repository.BookRepository;
import id.ac.ui.cs.pustakaone.bookshop.repository.CartRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import jakarta.persistence.EntityNotFoundException;

public class CartServiceImplTest {

    @Mock
    private CartRepository cartRepository;

    @InjectMocks
    private CartServiceImpl cartService;

    @Mock
    private BookCartRepository bookCartRepository;

    @Mock
    private BookRepository bookRepository;

    @Mock
    private PaymentService paymentService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testGetCartByUserId_CartExists() {
        Long userId = 1L;
        Cart existingCart = new Cart(userId);
        when(cartRepository.findByUserIdAndPaymentSuccessIsFalse(userId)).thenReturn(existingCart);

        Cart cart = cartService.getCartByUserId(userId);

        assertNotNull(cart);
        assertEquals(userId, cart.getUserId());
    }

    @Test
    void testGetCartByUserId_CartDoesNotExist() {
        Long userId = 1L;
        when(cartRepository.findByUserIdAndPaymentSuccessIsFalse(userId)).thenReturn(null);

        Cart cart = cartService.getCartByUserId(userId);

        assertNotNull(cart);
        assertEquals(userId, cart.getUserId());
        verify(cartRepository).save(cart);
    }

    @Test
    void testPayCart() {
        Long userId = 1L;
        Cart cart = cartService.getCartByUserId(userId);
        when(cartRepository.findByUserIdAndPaymentSuccessIsFalse(userId)).thenReturn(cart);

        cartService.payCart(userId);

        verify(paymentService).processPayment(cart);
    }


    @Test
    void testCheckoutCart() {
        Long userId = 1L;
        Cart cart = new Cart(userId);
        when(cartRepository.findByUserIdAndPaymentSuccessIsFalse(userId)).thenReturn(cart);

        cartService.checkoutCart(userId);

        assertEquals("processed", cart.getStatus());
        verify(cartRepository).save(cart);
    }

    @Test
    void testCancelPay() {
        Long userId = 1L;
        Cart cart = new Cart(userId);
        when(cartRepository.findByUserIdAndPaymentSuccessIsFalse(userId)).thenReturn(cart);

        cartService.cancelPay(userId);

        assertEquals("belum", cart.getStatus());
        verify(cartRepository).save(cart);
    }

    @Test
    void testGetCarts() {
        List<Cart> cartList = new ArrayList<>();
        cartList.add(new Cart(1L));
        cartList.add(new Cart(2L));
        cartList.add(new Cart(3L));

        when(cartRepository.findAll(Sort.by(Sort.Direction.ASC, "id"))).thenReturn(cartList);

        ResponseEntity<List<Cart>> response = cartService.getCarts();

        assertEquals(ResponseEntity.ok(cartList), response);
    }

    @Test
    void testFinishPayment_CartExists() {
        Long cartId = 1L;
        Cart cart = new Cart();
        cart.setId(cartId);
        cart.setStatus("pending");

        when(cartRepository.findById(cartId)).thenReturn(Optional.of(cart));

        ResponseEntity<?> response = cartService.finishPayment(cartId);

        assertEquals(ResponseEntity.ok(cart), response);
        assertEquals("selesai", cart.getStatus());
        assertNotNull(cart.getPaidAt());
        verify(cartRepository).save(cart);
    }

    @Test
    void testFinishPayment_CartDoesNotExist() {
        Long cartId = 1L;

        when(cartRepository.findById(cartId)).thenReturn(Optional.empty());

        ResponseEntity<?> response = cartService.finishPayment(cartId);

        assertEquals(ResponseEntity.notFound().build(), response);
        verify(cartRepository, never()).save(any(Cart.class));
    }

    @Test
    void testGetUserPaymentHistory() {
        Long userId = 1L;
        List<Cart> expectedCarts = new ArrayList<>();
        expectedCarts.add(new Cart(userId)); // Assume this cart represents a completed payment.

        when(cartRepository.findByUserIdAndPaymentSuccessIsTrue(userId)).thenReturn(expectedCarts);

        List<Cart> actualCarts = cartService.getUserPaymentHistory(userId);
        assertEquals(1, actualCarts.size());
        assertNotNull(actualCarts);
        assertFalse(actualCarts.isEmpty());
        assertEquals(expectedCarts.size(), actualCarts.size());
        verify(cartRepository).findByUserIdAndPaymentSuccessIsTrue(userId);
    }

    @Test
    void testDeleteBookFromCart() {
        Long userId = 1L;
        Book book = new Book();
        Cart cart = new Cart(userId);
        List<BookCart> bookCarts = new ArrayList<>();
        BookCart bookCart = new BookCart(book, cart, 1);
        bookCarts.add(bookCart);
        cart.setBookCarts(bookCarts);

        doReturn(Optional.of(bookCart)).when(bookCartRepository).findById(bookCart.getId());
        when(cartRepository.findByUserIdAndPaymentSuccessIsFalse(userId)).thenReturn(cart);

        Book deletedBook = cartService.deleteBookFromCart(userId, bookCart.getId());

        assertEquals(bookCart.getBook(), deletedBook);
        verify(bookCartRepository).delete(bookCart);
        verify(cartRepository).save(cart);
    }

    @Test
    void testDeleteBookFromCart_NotFoundBookCart() {
        Long userId = 1L;
        Long bookCartId = 1L;
        when(bookCartRepository.findById(bookCartId)).thenReturn(Optional.empty());

        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class, () ->
                cartService.deleteBookFromCart(userId, bookCartId));

        assertEquals("Bookcart not found! 1", exception.getMessage());
    }

    @Test
    void testDeleteBookFromCart_NotFoundCart() {
        Long userId = 1L;
        Long bookCartId = 1L;
        BookCart bookCart = new BookCart();

        when(bookCartRepository.findById(bookCartId)).thenReturn(Optional.of(bookCart));
        when(cartRepository.findByUserIdAndPaymentSuccessIsFalse(userId)).thenReturn(null);

        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class, () ->
                cartService.deleteBookFromCart(userId, bookCartId));

        assertEquals("Bookcart not found! 2", exception.getMessage());
    }

    @Test
    void testDeleteBookFromCart_BookCartNotInCart() {
        Long userId = 1L;
        Long bookCartId = 1L;
        BookCart bookCart = new BookCart();
        Cart cart = new Cart(userId);
        List<BookCart> bookCarts = new ArrayList<>();

        when(bookCartRepository.findById(bookCartId)).thenReturn(Optional.of(bookCart));
        when(cartRepository.findByUserIdAndPaymentSuccessIsFalse(userId)).thenReturn(cart);

        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class, () ->
                cartService.deleteBookFromCart(userId, bookCartId));

        assertEquals("Bookcart not found! 3", exception.getMessage());
    }
}
