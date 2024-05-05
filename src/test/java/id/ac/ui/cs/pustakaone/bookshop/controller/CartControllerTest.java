package id.ac.ui.cs.pustakaone.bookshop.controller;

import id.ac.ui.cs.pustakaone.bookshop.model.Cart;
import id.ac.ui.cs.pustakaone.bookshop.model.BookCart;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.test.web.servlet.MockMvc;

import id.ac.ui.cs.pustakaone.bookshop.service.CartService;
import id.ac.ui.cs.pustakaone.bookshop.service.BookCartService;

import java.util.HashMap;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;



@ExtendWith(MockitoExtension.class)
public class CartControllerTest {

    private MockMvc mockMvc;
    @Mock
    private CartService cartService;

    @Mock
    private BookCartService bookCartService;

    @InjectMocks
    private CartController cartController;

    @BeforeEach
    void setUp() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
    }

    @Test
    void getCartByUserIdTest() {
        Long userId = 1L;
        Cart cart = new Cart();
        when(cartService.getCartByUserId(userId)).thenReturn(cart);

        ResponseEntity<Cart> response = cartController.getCartByUserId(userId);

        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(cart, response.getBody());
    }

    @Test
    void checkoutCartTest() {
        Long userId = 1L;
        doNothing().when(cartService).checkoutCart(userId);

        ResponseEntity<Void> response = cartController.checkoutCart(userId);

        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
    }

    @Test
    void cancelPaymentTest() {
        Long userId = 1L;
        doNothing().when(cartService).cancelPay(userId);

        ResponseEntity<Void> response = cartController.cancelPayment(userId);

        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
    }

    @Test
    void payCartTest() {
        Long userId = 1L;
        doNothing().when(cartService).payCart(userId);

        ResponseEntity<Void> response = cartController.payCart(userId);

        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
    }

    @Test
    void addBookToCartTest() {
        Long userId = 1L;
        Long bookId = 1L;
        int amount = 2;
        BookCart bookCart = new BookCart();
        when(bookCartService.addBookToCart(userId, bookId, amount)).thenReturn(bookCart);

        ResponseEntity<?> response = cartController.addBookToCart(userId, bookId, amount);

        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(bookCart, response.getBody());
    }

    @Test
    void updateBookAmountInCartTest() {
        Long userId = 1L;
        Long bookCartId = 1L;
        int newAmount = 3;
        BookCart bookCart = new BookCart();
        when(bookCartService.updateBookAmountInCart(userId, bookCartId, newAmount)).thenReturn(bookCart);

        ResponseEntity<?> response = cartController.updateBookAmountInCart(userId, bookCartId, newAmount);

        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(bookCart, response.getBody());
    }

    @Test
    void getCartByUserIdTest_NotFound() {
        Long userId = 1L;
        when(cartService.getCartByUserId(userId)).thenThrow(new RuntimeException());

        ResponseEntity<Cart> response = cartController.getCartByUserId(userId);

        assertNotNull(response);
        assertEquals(500, response.getStatusCodeValue());
    }

    @Test
    public void testFinishPayments() {
        Long idCart = 1L;
        HashMap<String, String> body = new HashMap<>();
        body.put("idCart", "1");
        when(cartService.finishPayment(idCart)).thenReturn(ResponseEntity.ok().build());

        ResponseEntity<?> response = cartController.finishPayments(body);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(cartService).finishPayment(idCart);
    }

    @Test
    public void testFinishPayments_BadRequest() {

        HashMap<String, String> body = new HashMap<>();
        Exception exception = assertThrows(NumberFormatException.class, () -> {
            cartController.finishPayments(body);
        });

        assertTrue(exception.getMessage().contains("null"));
        verify(cartService, never()).finishPayment(anyLong());
    }

    @Test
    public void testGetCart() throws Exception {
        Cart cart = new Cart(1L);
        when(cartService.getCartByUserId(1L)).thenReturn(cart);
        mockMvc.perform(get("/shop/cart/" + 1))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.paymentSuccess").value(false));
    }

    @Test
    public void testDeleteBookFromCart() throws Exception {
        mockMvc.perform(delete("/shop/cart/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("Success delete book from cart"));
    }
}
