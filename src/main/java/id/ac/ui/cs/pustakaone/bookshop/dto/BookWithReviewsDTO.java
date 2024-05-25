package id.ac.ui.cs.pustakaone.bookshop.dto;

import id.ac.ui.cs.pustakaone.bookshop.model.Book;
import id.ac.ui.cs.pustakaone.bookshop.model.Review;

import java.util.List;

public class BookWithReviewsDTO {
    private Book book;
    private List<Review> reviews;

    public BookWithReviewsDTO(Book book, List<Review> reviews) {
        this.book = book;
        this.reviews = reviews;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }
}