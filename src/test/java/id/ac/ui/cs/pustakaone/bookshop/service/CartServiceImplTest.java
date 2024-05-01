package id.ac.ui.cs.pustakaone.bookshop.service;

import id.ac.ui.cs.pustakaone.bookshop.model.Book;
import id.ac.ui.cs.pustakaone.bookshop.model.BookCart;
import id.ac.ui.cs.pustakaone.bookshop.model.Cart;
import id.ac.ui.cs.pustakaone.bookshop.repository.BookCartRepository;
import id.ac.ui.cs.pustakaone.bookshop.repository.CartRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class CartServiceImplTest {

    @InjectMocks
    CartServiceImpl cartService;

    @Mock
    CartRepository cartRepository;

    @Mock
    BookCartRepository bookCartRepository;

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
        BookCart bookCart = new BookCart(book, cart, 1);

        doReturn(Optional.of(bookCart)).when(bookCartRepository).findById(bookCart.getId());
        when(cartRepository.findByUserIdAndPaymentSuccessIsFalse(userId)).thenReturn(cart);

        Book deletedBook = cartService.deleteBookFromCart(userId, bookCart.getId());

        assertEquals(bookCart.getBook(), deletedBook);
    }
}
