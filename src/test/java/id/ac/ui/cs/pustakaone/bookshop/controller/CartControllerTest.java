package id.ac.ui.cs.pustakaone.bookshop.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
public class CartControllerTest {
    private MockMvc mockMvc;

    @InjectMocks
    private CartController cartController;

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
}
