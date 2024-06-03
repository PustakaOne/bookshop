package id.ac.ui.cs.pustakaone.bookshop.service;

import id.ac.ui.cs.pustakaone.bookshop.model.Review;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.Future;

public interface ReviewService {
    public Review createReview(Review review);
    public Optional<Review> findReviewById(long id);
    public List<Review> getAllBookReview(long bookId);
    public Review updateReview(Review review);
    public void deleteReview(long id);

    Future<List<Review>> getReviews(long id);
}
