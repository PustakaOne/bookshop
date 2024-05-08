package id.ac.ui.cs.pustakaone.bookshop.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import id.ac.ui.cs.pustakaone.bookshop.model.BookCart;
import id.ac.ui.cs.pustakaone.bookshop.service.BookCartService;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class BookCartControllerTest {

    @Mock
    private BookCartService bookCartService;

    @InjectMocks
    private BookCartController bookCartController;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(bookCartController).build();
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
    }

    @Test
    void addBookToCartTest() {
        Long userId = 1L;
        Long bookId = 2L;
        int amount = 3;

        BookCart bookCart = new BookCart();
        when(bookCartService.addBookToCart(userId, bookId, amount)).thenReturn(bookCart);

        ResponseEntity<?> response = bookCartController.addBookToCart(userId, bookId, amount);

        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(bookCart, response.getBody());
    }

    @Test
    void addBookToCartTest_IllegalArgument() {
        Long userId = 1L;
        Long bookId = 2L;
        int amount = 3;

        when(bookCartService.addBookToCart(userId, bookId, amount)).thenThrow(new IllegalArgumentException("Invalid amount"));

        ResponseEntity<?> response = bookCartController.addBookToCart(userId, bookId, amount);

        assertNotNull(response);
        assertEquals(400, response.getStatusCodeValue());
        assertTrue(response.getBody().toString().contains("Invalid amount"));
    }

    @Test
    void updateBookAmountInCartTest() {
        Long userId = 1L;
        Long bookCartId = 2L;
        int newAmount = 5;

        BookCart bookCart = new BookCart();
        when(bookCartService.updateBookAmountInCart(userId, bookCartId, newAmount)).thenReturn(bookCart);

        ResponseEntity<?> response = bookCartController.updateBookAmountInCart(userId, bookCartId, newAmount);

        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(bookCart, response.getBody());
    }

    @Test
    void updateBookAmountInCartTest_IllegalArgument() {
        Long userId = 1L;
        Long bookCartId = 2L;
        int newAmount = 0; // assuming this is an invalid amount

        when(bookCartService.updateBookAmountInCart(userId, bookCartId, newAmount)).thenThrow(new IllegalArgumentException("Amount cannot be zero or negative"));

        ResponseEntity<?> response = bookCartController.updateBookAmountInCart(userId, bookCartId, newAmount);

        assertNotNull(response);
        assertEquals(400, response.getStatusCodeValue());
        assertTrue(response.getBody().toString().contains("Amount cannot be zero or negative"));
    }
}
