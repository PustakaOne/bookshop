package id.ac.ui.cs.pustakaone.bookshop.model;

import java.util.Date;

public class ReviewConcreteBuilder implements ReviewBuilder {
    private Review review;

    public ReviewConcreteBuilder() {
        this.review = new Review();
    }

    @Override
    public void reset() {
        this.review = new Review();
    }

    @Override
    public void setId(String id) {
        this.review.setId(id);
    }

    @Override
    public void setBook(Book book) {
        this.review.setBook(book);
    }

    @Override
    public void setUser(User user) {
        this.review.setUser(user);
    }

    @Override
    public void setReview(String reviewText) {
        this.review.setReview(reviewText);
    }

    @Override
    public void setRating(int rating) {
        this.review.setRating(rating);
    }

    @Override
    public void setCreatedAt(Date createdAt) {
        this.review.setCreatedAt(createdAt);
    }

    @Override
    public void setUpdatedAt(Date updatedAt) {
        this.review.setUpdatedAt(updatedAt);
    }

    @Override
    public Review build() {
        return this.review;
    }

    @Override
    public Review getReview() {
        return this.review;
    }
}
