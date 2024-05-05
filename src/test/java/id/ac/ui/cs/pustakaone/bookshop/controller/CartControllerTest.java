package id.ac.ui.cs.pustakaone.bookshop.controller;

import id.ac.ui.cs.pustakaone.bookshop.model.Cart;
import id.ac.ui.cs.pustakaone.bookshop.service.CartServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
public class CartControllerTest {
    private MockMvc mockMvc;

    @InjectMocks
    private CartController cartController;

    @Mock
    private CartServiceImpl cartService;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(cartController).build();
    }

    @Test
    public void testAddBookToCart() throws Exception {
        int bookId = 1;
        mockMvc.perform(post("/shop/cart/add/" + bookId))
                .andExpect(status().isOk())
                .andExpect(content().string("Successfully added Book " + bookId + " to cart"));
    }

    @Test
    public void testUpdateBookQuantity() throws Exception {
        int bookId = 1;
        mockMvc.perform(post("/shop/cart/update/" + bookId))
                .andExpect(status().isOk())
                .andExpect(content().string("Successfully updated quantity for Book " + bookId + " in cart"));
    }

    @Test
    public void testCheckoutCart() throws Exception {
        mockMvc.perform(post("/shop/cart/checkout"))
                .andExpect(status().isOk())
                .andExpect(content().string("Checkout successful"));
    }

    @Test
    public void testPayCart() throws Exception {
        mockMvc.perform(post("/shop/cart/pay"))
                .andExpect(status().isOk())
                .andExpect(content().string("Payment successful"));
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
