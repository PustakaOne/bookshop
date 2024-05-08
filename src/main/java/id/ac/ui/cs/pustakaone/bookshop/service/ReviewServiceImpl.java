package id.ac.ui.cs.pustakaone.bookshop.service;

import id.ac.ui.cs.pustakaone.bookshop.model.Review;
import id.ac.ui.cs.pustakaone.bookshop.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReviewServiceImpl implements ReviewService {
    private final ReviewRepository reviewRepository;

    @Autowired
    public ReviewServiceImpl(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    @Override
    public Review createReview(Review review) {
        Review newReview = Review.builder()
                .bookId(review.getBookId())
                .userId(review.getUserId())
                .content(review.getContent())
                .rating(review.getRating())
                .createdAt(review.getCreatedAt())
                .updatedAt(review.getUpdatedAt())
                .build();
        return reviewRepository.save(newReview);
    }
    
    @Override
    public Optional<Review> findReviewById(long id) {
        return reviewRepository.findById(id);
    }

    @Override
    public List<Review> getAllBookReview(long bookId) {
        return reviewRepository.findByBookId(bookId);
    }

    @Override
    public Review updateReview(Review review) {
        Review newReview = Review.builder()
                .reviewId(review.getReviewId())
                .bookId(review.getBookId())
                .userId(review.getUserId())
                .content(review.getContent())
                .rating(review.getRating())
                .createdAt(review.getCreatedAt())
                .updatedAt(review.getUpdatedAt())
                .build();
        return reviewRepository.save(newReview);
    }

    @Override
    public void deleteReview(long id) {
        reviewRepository.deleteById(id);
    }
}
