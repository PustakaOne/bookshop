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
    public void testGetHello() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(content().string("Hello bookshop!"));
    }

    @Test
    public void testAddBookToCart() throws Exception {
        mockMvc.perform(post("/shop/cart/add"))
                .andExpect(status().isOk())
                .andExpect(content().string("Successfully added book to cart"));
    }

    @Test
    public void testUpdateBookQuantity() throws Exception {
        mockMvc.perform(post("/shop/cart/update"))
                .andExpect(status().isOk())
                .andExpect(content().string("Successfully updated book quantity in cart"));
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
        mockMvc.perform(get("/shop/cart"))
                .andExpect(status().isOk())
                .andExpect(content().string("Success get cart"));
    }

    @Test
    public void testDeleteBookFromCart() throws Exception {
        mockMvc.perform(delete("/shop/cart/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("Success delete book from cart"));
    }
}
