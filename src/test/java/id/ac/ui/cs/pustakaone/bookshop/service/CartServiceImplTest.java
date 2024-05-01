package id.ac.ui.cs.pustakaone.bookshop.service;

import id.ac.ui.cs.pustakaone.bookshop.model.Cart;
import id.ac.ui.cs.pustakaone.bookshop.repository.CartRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class CartServiceImplTest {

    @InjectMocks
    CartServiceImpl cartService;

    @Mock
    CartRepository cartRepository;

    @Test
    public void testGetExistCartByUserId() {
        String userId = "d9wa-ewq0-daswa-ds92-vjd9";
        Cart existCart = new Cart(userId);

        when(cartRepository.findByUserIdAndPaymentSuccessIsFalse(userId)).thenReturn(existCart);

        Cart cart = cartService.getCartByUserId(userId);
        assertEquals(existCart, cart);
    }

    @Test
    public void testGetNotExistCartByUserId() {
        String userId = "d9wa-ewq0-daswa-ds92-vjd9";

        when(cartRepository.findByUserIdAndPaymentSuccessIsFalse(userId)).thenReturn(null);

        Cart cart = cartService.getCartByUserId(userId);

        assertNotNull(cart);
    }
}
