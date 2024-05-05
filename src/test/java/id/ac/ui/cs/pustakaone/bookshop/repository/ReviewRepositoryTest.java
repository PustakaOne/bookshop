package id.ac.ui.cs.pustakaone.bookshop.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import id.ac.ui.cs.pustakaone.bookshop.model.Review;
import id.ac.ui.cs.pustakaone.bookshop.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import id.ac.ui.cs.pustakaone.bookshop.model.Book;
import java.util.List;
import java.util.Optional;

@DataJpaTest
public class ReviewRepositoryTest {
    @Autowired
    private ReviewRepository reviewRepository;

    private Book book;
    private Review review;
    private User user;

    @BeforeEach
    public void setUp() {
        book = Book.builder()
                .title("Book")
                .author("Test")
                .price(200000)
                .build();
        user = new User("Tes", "tes", "0899", "pass123");
        review = Review.builder()
                .bookId(book.getBookId())
                .userId(user.getId())
                .content("Sebuah review")
                .rating(5)
                .build();
    }

    @Test
    void testCreateReview() {
        Review createdReview = reviewRepository.save(review);
        assertNotNull(createdReview);
        assertEquals(createdReview.getUserId(), user.getId());
        assertEquals(createdReview.getContent(), review.getContent());
        assertEquals(createdReview.getRating(), review.getRating());
        assertEquals(createdReview.getBookId(), book.getBookId());
        assertEquals(createdReview.getUserId(), user.getId());
    }

    @Test
    void testFindReviewById() {
        reviewRepository.save(review);
        Optional<Review> savedReview = reviewRepository.findById(review.getReviewId());
        assertNotNull(savedReview);
        assertEquals(savedReview.get().getBookId(), book.getBookId());
        assertEquals(savedReview.get().getUserId(), user.getId());
        assertEquals(savedReview.get().getContent(), review.getContent());
        assertEquals(savedReview.get().getRating(), review.getRating());
    }

    @Test
    void testGetAllBookReview() {
        Review review2 = Review.builder()
                .bookId(book.getBookId())
                .userId(user.getId())
                .content("Sebuah review")
                .rating(5)
                .build();
        reviewRepository.save(review);
        reviewRepository.save(review2);
        List<Review> allReviews = reviewRepository.findByBookId(book.getBookId());
        assertNotNull(allReviews);
        assertEquals(2, allReviews.size());
    }

    @Test
    void testDeleteReview() {
        reviewRepository.save(review);
        List<Review> allReviews = reviewRepository.findAll();
        assertNotNull(allReviews);
        assertEquals(1, allReviews.size());
        reviewRepository.delete(review);
        allReviews = reviewRepository.findAll();
        assertEquals(0, allReviews.size());
    }

    @Test
    void testUpdateReview() {
        reviewRepository.save(review);
        Review updatedReview = Review.builder()
                .reviewId(review.getReviewId())
                .bookId(book.getBookId())
                .userId(user.getId())
                .content("Sebuah review updated")
                .rating(4)
                .build();
        reviewRepository.save(updatedReview);
        Review savedReview = reviewRepository.findById(updatedReview.getReviewId()).get();
        assertEquals(updatedReview.getReviewId(), savedReview.getReviewId());
        assertEquals(updatedReview.getBookId(), savedReview.getBookId());
        assertEquals(updatedReview.getUserId(), savedReview.getUserId());
        assertEquals(updatedReview.getContent(), review.getContent());
        assertEquals(updatedReview.getRating(), review.getRating());
    }
}
