package id.ac.ui.cs.pustakaone.bookshop.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class ReviewConcreteBuilderTest {
    @InjectMocks
    private ReviewConcreteBuilder builder;

    @BeforeEach
    public void setUp() {
        builder = new ReviewConcreteBuilder();
    }

    @Test
    public void testReset() {
        // Build a review
        builder.setId("12345");
        builder.setBook(new Book());
        builder.setUser(new User());
        builder.setReview("Sample review");
        builder.setRating(5);
        builder.setCreatedAt(new Date());
        builder.setUpdatedAt(new Date());

        // Reset the builder
        builder.reset();

        // Get the review after reset
        Review review = builder.build();

        // Assert that all attributes are reset to their default values
        assertNull(review.getId());
        assertNull(review.getBook());
        assertNull(review.getUser());
        assertNull(review.getReview());
        assertEquals(0, review.getRating());
        assertNull(review.getCreatedAt());
        assertNull(review.getUpdatedAt());
    }

    @Test
    public void testBuildId() {
        builder.setId("12345");
        Review review = builder.build();
        assertEquals("12345", review.getId());
    }

    @Test
    public void testBuildBook() {
        Book book = new Book();
        builder.setBook(book);
        Review review = builder.build();
        assertEquals(book, review.getBook());
    }

    @Test
    public void testBuildUser() {
        User user = new User();
        builder.setUser(user);
        Review review = builder.build();
        assertEquals(user, review.getUser());
    }

    @Test
    public void testBuildReview() {
        builder.setReview("Sample review");
        Review review = builder.build();
        assertEquals("Sample review", review.getReview());
    }

    @Test
    public void testBuildRating() {
        builder.setRating(5);
        Review review = builder.build();
        assertEquals(5, review.getRating());
    }

    @Test
    public void testBuildCreatedAt() {
        Date createdAt = new Date();
        builder.setCreatedAt(createdAt);
        Review review = builder.build();
        assertEquals(createdAt, review.getCreatedAt());
    }

    @Test
    public void testBuildUpdatedAt() {
        Date updatedAt = new Date();
        builder.setUpdatedAt(updatedAt);
        Review review = builder.build();
        assertEquals(updatedAt, review.getUpdatedAt());
    }
}

