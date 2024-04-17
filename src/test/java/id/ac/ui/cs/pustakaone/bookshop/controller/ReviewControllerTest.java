package id.ac.ui.cs.pustakaone.bookshop.controller;

import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
public class ReviewControllerTest {
    @InjectMocks
    private ReviewController reviewController;
    private MockMvc mockMvc;

    @Test
    void testCreateReview() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(reviewController).build();

        mockMvc.perform(get("/review/1/add"))
                .andExpect(status().isOk()).andExpect(content().string("Create Review for Book 1"));
    }

    @Test
    void testGetReview() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(reviewController).build();

        mockMvc.perform(get("/review/1"))
                .andExpect(status().isOk()).andExpect(content().string("Reviews for Book 1"));
    }

    @Test
    void testUpdateReview() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(reviewController).build();

        mockMvc.perform(get("/review/1/1/update"))
                .andExpect(status().isOk()).andExpect(content().string("Update reviewId 1 in bookId 1"));
    }

    @Test
    void testDeleteReview() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(reviewController).build();

        mockMvc.perform(get("/review/1/1/delete"))
                .andExpect(status().isOk()).andExpect(content().string("Delete review Id 1 in BookId 1"));
    }

    @Test
    void testDeleteAllReview() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(reviewController).build();

        mockMvc.perform(get("/review/1/delete"))
                .andExpect(status().isOk()).andExpect(content().string("Delete all review for book id 1"));
    }
}