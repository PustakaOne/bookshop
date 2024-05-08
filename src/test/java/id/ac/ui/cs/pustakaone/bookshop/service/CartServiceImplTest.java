package id.ac.ui.cs.pustakaone.bookshop.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import id.ac.ui.cs.pustakaone.bookshop.model.Book;
import id.ac.ui.cs.pustakaone.bookshop.model.BookCart;
import id.ac.ui.cs.pustakaone.bookshop.model.Cart;
import id.ac.ui.cs.pustakaone.bookshop.repository.BookCartRepository;
import id.ac.ui.cs.pustakaone.bookshop.repository.CartRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class CartServiceImplTest {

    @Mock
    private CartRepository cartRepository;

    @InjectMocks
    private CartServiceImpl cartService;

    @Mock
    BookCartRepository bookCartRepository;

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
        Cart cart = new Cart(userId);
        when(cartRepository.findByUserIdAndPaymentSuccessIsFalse(userId)).thenReturn(cart);

        cartService.payCart(userId);

        assertEquals("menunggu pengiriman", cart.getStatus());
        assertTrue(cart.isPaymentSuccess());
        assertNotNull(cart.getPaidAt());
        verify(cartRepository).save(cart);
    }

    @Test
    public void testGetExistCartByUserId() {
        Long userId = 1L;
        Cart existCart = new Cart(userId);

        when(cartRepository.findByUserIdAndPaymentSuccessIsFalse(userId)).thenReturn(existCart);

        Cart cart = cartService.getCartByUserId(userId);
        assertEquals(existCart, cart);
    }

    @Test
    public void testGetNotExistCartByUserId() {
        Long userId = 1L;

        when(cartRepository.findByUserIdAndPaymentSuccessIsFalse(userId)).thenReturn(null);

        Cart cart = cartService.getCartByUserId(userId);

        assertNotNull(cart);
    }

    @Test
    public void testDeleteBookFromCart() {
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
    }

    @Test
    void testFinishPayment() {
        Long userId = 1L;
        Cart cart = new Cart(userId);
        when(cartRepository.findByUserIdAndPaymentSuccessIsFalse(userId)).thenReturn(cart);

        cartService.finishPayment(userId);

        assertEquals("selesai", cart.getStatus());
        verify(cartRepository).save(cart);
    }

    @Test
    void testGetCarts() {
        Cart existingCart = new Cart(1L);
        Cart existingCart2 = new Cart(2L);
        Cart existingCart3 = new Cart(3L);
        List<Cart> list = new ArrayList<>();
        list.add(existingCart);
        list.add(existingCart2);
        list.add(existingCart3);
        when(cartRepository.findAll()).thenReturn(list);
        ResponseEntity<List<Cart>> result = cartService.getCarts();
        assertEquals(ResponseEntity.ok(list), result);

    }

    @Test
    void testGetUserPaymentHistory() {
        Long userId = 1L;
        List<Cart> expectedCarts = new ArrayList<>();
        expectedCarts.add(new Cart(userId)); // Assume this cart represents a completed payment.

        when(cartRepository.findByUserIdAndPaymentSuccessIsTrue(userId)).thenReturn(expectedCarts);

        List<Cart> actualCarts = cartService.getUserPaymentHistory(userId);
        assertEquals(1,actualCarts.size());
        assertNotNull(actualCarts);
        assertFalse(actualCarts.isEmpty());
        assertEquals(expectedCarts.size(), actualCarts.size());
        verify(cartRepository).findByUserIdAndPaymentSuccessIsTrue(userId);
    }




}
