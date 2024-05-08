package id.ac.ui.cs.pustakaone.bookshop.service;

import id.ac.ui.cs.pustakaone.bookshop.model.Book;
import id.ac.ui.cs.pustakaone.bookshop.model.Review;
import id.ac.ui.cs.pustakaone.bookshop.repository.BookRepository;
import id.ac.ui.cs.pustakaone.bookshop.repository.ReviewRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class ReviewServiceImplTest {
    @Mock
    private ReviewRepository reviewRepository;

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private ReviewServiceImpl reviewService;

    @Test
    void testCreateReview() {
        Review review = new Review();
        reviewService.createReview(review);
        verify(reviewRepository, times(1)).save(any(Review.class));
    }

    @Test
    void testFindReviewById() {
        Review review = new Review();
        review.setReviewId(1L);
        when(reviewRepository.findById(review.getReviewId())).thenReturn(Optional.of(review));
        Optional<Review> result = reviewService.findReviewById(review.getReviewId());
        assertEquals(review.getReviewId(), result.get().getReviewId());
    }

    @Test
    void testGetAllBookReview() {
        Book book = new Book();
        book = Book.builder()
                .bookId(1L)
                .title("Title")
                .build();
        bookRepository.save(book);

        Review review = new Review();
        review = Review.builder()
                .reviewId(1L)
                .bookId(book.getBookId())
                .rating(5)
                .build();
        reviewService.createReview(review);

        List<Review> reviews = Collections.singletonList(review);

        when(reviewRepository.findByBookId(1L)).thenReturn(reviews);

        List<Review> foundReviews = reviewService.getAllBookReview(1L);

        assertEquals(1, foundReviews.size());
        assertEquals(5, foundReviews.get(0).getRating());

        verify(reviewRepository, times(1)).findByBookId(1L);
    }

    @Test
    void testUpdateReview() {
        Review review = new Review();
        review = Review.builder()
                .reviewId(1L)
                .rating(1)
                .build();
        Review newReview = new Review();
        newReview = Review.builder()
                .reviewId(1L)
                .rating(2)
                .build();
        reviewRepository.save(review);

        reviewService.updateReview(newReview);
        verify(reviewRepository, times(2)).save(any(Review.class));
    }

    @Test
    void testDeleteReview() {
        Review review = new Review();
        review = Review.builder()
                .reviewId(1L)
                .rating(5)
                .build();
        reviewRepository.save(review);

        reviewService.deleteReview(review.getReviewId());
        verify(reviewRepository, times(1)).deleteById(review.getReviewId());
        Optional<Review> results = reviewService.findReviewById(1L);
        assertTrue(results.isEmpty());
    }
}
