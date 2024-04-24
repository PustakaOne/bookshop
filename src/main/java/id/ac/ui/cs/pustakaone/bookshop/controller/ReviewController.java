package id.ac.ui.cs.pustakaone.bookshop.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/review")
public class ReviewController {
    @GetMapping("/{bookId}/add")
    public String createReview(@PathVariable("bookId") int bookId) {
        return "Create Review for Book 1";
    }

    @GetMapping("/{bookId}")
    public String getReview(@PathVariable("bookId") int bookId) {
        return "Reviews for Book 1";
    }

    @GetMapping("/{bookId}/{reviewId}/update")
    public String updateReview(@PathVariable("bookId") int bookId, @PathVariable("reviewId") int reviewId) {
        return "Update reviewId 1 in bookId 1";
    }

    @GetMapping("/{bookId}/{reviewId}/delete")
    public String deleteReview(@PathVariable("bookId") int bookId, @PathVariable("reviewId") int reviewId) {
        return "Delete review Id 1 in BookId 1";
    }

    @GetMapping("/{bookId}/delete")
    public String deleteAllReviews(@PathVariable("bookId") int bookId) {
        return "Delete all review for book id 1";
    }
}
