package id.ac.ui.cs.pustakaone.bookshop.controller;

import id.ac.ui.cs.pustakaone.bookshop.model.Review;
import id.ac.ui.cs.pustakaone.bookshop.service.ReviewService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
public class ReviewControllerTest {
    @Mock
    private ReviewService reviewService;

    @InjectMocks
    private ReviewController reviewController;

    private MockMvc mockMvc;
    private Review review;
    private Review review2;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(reviewController).build();
        review = Review.builder()
                .reviewId(1L)
                .bookId(1L)
                .userId(1L)
                .content("Great book!")
                .rating(5)
                .build();
        review2 = Review.builder()
                .reviewId(2L)
                .bookId(1L)
                .userId(2L)
                .content("Review 2 content")
                .rating(4)
                .build();
        List<Review> reviewList = new ArrayList<>();
        reviewList.add(review);
        reviewList.add(review2);
    }

    @Test
    void testCreateReview() throws Exception {
        when(reviewService.createReview(any(Review.class))).thenReturn(review);

        mockMvc.perform(post("/review/1/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"bookId\":1,\"userId\":1,\"content\":\"Great book!\",\"rating\":5}"))
                .andExpect(status().isOk());
    }

    @Test
    void testGetReview() throws Exception {
        List<Review> reviewList = Collections.singletonList(review);
        when(reviewService.getAllBookReview(1L)).thenReturn(reviewList);

        mockMvc.perform(get("/review/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].content").value("Great book!"));
    }

    @Test
    void testUpdateReview() throws Exception {
        when(reviewService.updateReview(any(Review.class))).thenReturn(review);

        mockMvc.perform(put("/review/1/1/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"reviewId\":1,\"bookId\":1,\"userId\":1,\"content\":\"Updated review!\",\"rating\":4}"))
                .andExpect(status().isOk());
    }

    @Test
    void testDeleteReview() throws Exception {
        doNothing().when(reviewService).deleteReview(1L);

        mockMvc.perform(delete("/review/1/delete"))
                .andExpect(status().isOk());
    }

    @Test
    void testDeleteAllReviews() throws Exception {
        List<Review> reviewList = Collections.emptyList();
        when(reviewService.getAllBookReview(1L)).thenReturn(reviewList);

        mockMvc.perform(delete("/review/1/delete"))
                .andExpect(status().isOk());
    }
}
