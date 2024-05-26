package id.ac.ui.cs.pustakaone.bookshop.controller;

import id.ac.ui.cs.pustakaone.bookshop.model.Book;
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
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.test.web.servlet.MockMvc;

import id.ac.ui.cs.pustakaone.bookshop.service.CartService;
import id.ac.ui.cs.pustakaone.bookshop.service.BookCartService;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

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
        mockMvc = MockMvcBuilders.standaloneSetup(cartController).build();
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
    void getCartByUserIdTest_NotFound() {
        Long userId = 1L;
        when(cartService.getCartByUserId(userId)).thenThrow(new RuntimeException());

        ResponseEntity<Cart> response = cartController.getCartByUserId(userId);

        assertNotNull(response);
        assertEquals(500, response.getStatusCodeValue());
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
    void checkoutCartTest_Exception() {
        Long userId = 1L;
        doThrow(new RuntimeException()).when(cartService).checkoutCart(userId);

        ResponseEntity<Void> response = cartController.checkoutCart(userId);

        assertNotNull(response);
        assertEquals(500, response.getStatusCodeValue());
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
    void cancelPaymentTest_Exception() {
        Long userId = 1L;
        doThrow(new RuntimeException()).when(cartService).cancelPay(userId);

        ResponseEntity<Void> response = cartController.cancelPayment(userId);

        assertNotNull(response);
        assertEquals(500, response.getStatusCodeValue());
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
    void payCartTest_Exception() {
        Long userId = 1L;
        doThrow(new RuntimeException()).when(cartService).payCart(userId);

        ResponseEntity<Void> response = cartController.payCart(userId);

        assertNotNull(response);
        assertEquals(500, response.getStatusCodeValue());
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
    void addBookToCartTest_IllegalArgumentException() {
        Long userId = 1L;
        Long bookId = 1L;
        int amount = 2;
        when(bookCartService.addBookToCart(userId, bookId, amount)).thenThrow(new IllegalArgumentException("Invalid amount"));

        ResponseEntity<?> response = cartController.addBookToCart(userId, bookId, amount);

        assertNotNull(response);
        assertEquals(400, response.getStatusCodeValue());
        assertEquals("Invalid amount", response.getBody());
    }

    @Test
    void addBookToCartTest_Exception() {
        Long userId = 1L;
        Long bookId = 1L;
        int amount = 2;
        when(bookCartService.addBookToCart(userId, bookId, amount)).thenThrow(new RuntimeException());

        ResponseEntity<?> response = cartController.addBookToCart(userId, bookId, amount);

        assertNotNull(response);
        assertEquals(500, response.getStatusCodeValue());
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
    void updateBookAmountInCartTest_IllegalArgumentException() {
        Long userId = 1L;
        Long bookCartId = 1L;
        int newAmount = 3;
        when(bookCartService.updateBookAmountInCart(userId, bookCartId, newAmount)).thenThrow(new IllegalArgumentException("Invalid amount"));

        ResponseEntity<?> response = cartController.updateBookAmountInCart(userId, bookCartId, newAmount);

        assertNotNull(response);
        assertEquals(400, response.getStatusCodeValue());
        assertEquals("Invalid amount", response.getBody());
    }

    @Test
    void updateBookAmountInCartTest_Exception() {
        Long userId = 1L;
        Long bookCartId = 1L;
        int newAmount = 3;
        when(bookCartService.updateBookAmountInCart(userId, bookCartId, newAmount)).thenThrow(new RuntimeException());

        ResponseEntity<?> response = cartController.updateBookAmountInCart(userId, bookCartId, newAmount);

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
    public void testGetCart_Exception() throws Exception {
        when(cartService.getCartByUserId(1L)).thenThrow(new RuntimeException());
        mockMvc.perform(get("/shop/cart/" + 1))
                .andExpect(status().isInternalServerError());
    }

    @Test
    public void testGetPaymentHistory() {
        Long userId = 1L;
        List<Cart> paymentHistory = Arrays.asList(new Cart(), new Cart());
        when(cartService.getUserPaymentHistory(userId)).thenReturn(paymentHistory);

        ResponseEntity<List<Cart>> response = cartController.getPaymentHistory(userId);

        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(paymentHistory, response.getBody());
    }

    @Test
    public void testGetPaymentHistory_NoContent() {
        Long userId = 1L;
        List<Cart> paymentHistory = Arrays.asList();
        when(cartService.getUserPaymentHistory(userId)).thenReturn(paymentHistory);

        ResponseEntity<List<Cart>> response = cartController.getPaymentHistory(userId);

        assertNotNull(response);
        assertEquals(204, response.getStatusCodeValue());
    }

    @Test
    public void testGetPaymentHistory_Exception() {
        Long userId = 1L;
        when(cartService.getUserPaymentHistory(userId)).thenThrow(new RuntimeException());

        ResponseEntity<List<Cart>> response = cartController.getPaymentHistory(userId);

        assertNotNull(response);
        assertEquals(500, response.getStatusCodeValue());
    }

    @Test
    public void testGetCarts() {
        List<Cart> carts = Arrays.asList(new Cart(1L), new Cart(2L));
        when(cartService.getCarts()).thenReturn(ResponseEntity.ok(carts));

        ResponseEntity<List<Cart>> response = cartController.getCarts();

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(carts, response.getBody());
    }

    @Test
    public void testDeleteBookCart() throws Exception {
        // Arrange
        Book book = new Book(); // Create a mock Book object
        when(cartService.deleteBookFromCart(anyLong(), anyLong())).thenReturn(book);

        // Act
        ResponseEntity<Book> response = cartController.deleteBookCart("1", "1");

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}
