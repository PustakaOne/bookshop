package id.ac.ui.cs.pustakaone.bookshop.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import id.ac.ui.cs.pustakaone.bookshop.model.Cart;
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

//    @Test
//    public void testCreateCart() {
//        String userId = "user1";
//        Cart cart = new Cart(userId);
//        when(cartRepository.save(any(Cart.class))).thenAnswer(invocation -> invocation.getArgument(0));
//
//        Cart createdCart = cartService.createCart(userId);
//
//        assertNotNull(createdCart);
//        assertEquals(userId, createdCart.getUserId());
//        verify(cartRepository).save(any(Cart.class));
//    }
//
//    @Test
//    public void testCheckoutCart() {
//        Long cartId = 1L;
//        Cart cart = new Cart();
//        cart.setId(cartId);
//        cart.setStatus("belum");
//
//        when(cartRepository.findById(cartId)).thenReturn(Optional.of(cart));
//        when(cartRepository.save(cart)).thenReturn(cart);
//
//        cartService.checkoutCart(cartId);
//
//        assertEquals("processed", cart.getStatus());
//        verify(cartRepository).save(cart);
//    }
//
//    @Test
//    public void testCancelPay() {
//        Long cartId = 2L;
//        Cart cart = new Cart();
//        cart.setId(cartId);
//        cart.setStatus("processed");
//
//        when(cartRepository.findById(cartId)).thenReturn(Optional.of(cart));
//        when(cartRepository.save(cart)).thenReturn(cart);
//
//        cartService.cancelPay(cartId);
//
//        assertEquals("belum", cart.getStatus());
//        verify(cartRepository).save(cart);
//    }

//    @Test
//    public void testPayCart() {
//        Long cartId = 3L;
//        Cart cart = new Cart();
//        cart.setId(cartId);
//        cart.setStatus("processed");
//
//        when(cartRepository.findById(cartId)).thenReturn(Optional.of(cart));
//        when(cartRepository.save(cart)).thenReturn(cart);
//
//        cartService.payCart(cartId);
//
//        assertEquals("menunggu pengiriman", cart.getStatus());
//        assertTrue(cart.isPaymentSuccess());
//        assertNotNull(cart.getPaidAt());
//        verify(cartRepository).save(cart);
//    }
//
//    @Test
//    public void testCartNotFound() {
//        Long cartId = 4L;
//        when(cartRepository.findById(cartId)).thenReturn(Optional.empty());
//
//        Exception exception = assertThrows(RuntimeException.class, () -> {
//            cartService.checkoutCart(cartId);
//        });
//
//        assertEquals("Cart not found", exception.getMessage());
//    }
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

}
