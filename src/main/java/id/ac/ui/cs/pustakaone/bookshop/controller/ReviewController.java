package id.ac.ui.cs.pustakaone.bookshop.controller;

import id.ac.ui.cs.pustakaone.bookshop.model.Review;
import id.ac.ui.cs.pustakaone.bookshop.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Iterator;
import java.util.List;

@RestController
public class ReviewController {
    @Autowired
    private ReviewService reviewService;

    @RequestMapping(value = "/review/{bookId}/add", method = RequestMethod.POST)
    public ResponseEntity createReview(@PathVariable("bookId") long bookId, @RequestBody Review review) {
        ResponseEntity responseEntity = null;

        try {
            reviewService.createReview(review);
            responseEntity = ResponseEntity.ok().build();
        } catch (Exception e) {
            System.out.println("Error in add book!");
            responseEntity = ResponseEntity.badRequest().body(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return responseEntity;
    }


    @RequestMapping(value = "/review/{bookId}", method = RequestMethod.GET)
    public ResponseEntity getReview(@PathVariable("bookId") long bookId) {
        ResponseEntity responseEntity = null;

        try {
            List<Review> result = reviewService.getAllBookReview(bookId);
            responseEntity = ResponseEntity.ok().body(result);
        } catch (Exception e) {
            System.out.println("Error in get review!");
            responseEntity = ResponseEntity.badRequest().body(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return responseEntity;
    }

    @RequestMapping(value = "/review/{bookId}/{reviewId}/update", method = RequestMethod.PUT)
    public ResponseEntity updateReview(@PathVariable("bookId") long bookId, @PathVariable("reviewId") long reviewId, @RequestBody Review review) {
        ResponseEntity responseEntity = null;

        try {
            reviewService.updateReview(review);
            responseEntity = ResponseEntity.ok().build();
        } catch (Exception e) {
            System.out.println("Error in add book!");
            responseEntity = ResponseEntity.badRequest().body(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return responseEntity;
    }

    @RequestMapping(value = "/review/{reviewId}/delete", method = RequestMethod.DELETE)
    public ResponseEntity deleteReview(@PathVariable("reviewId") long reviewId) {
        ResponseEntity responseEntity = null;

        try {
            reviewService.deleteReview(reviewId);
            responseEntity = ResponseEntity.ok().build();
        } catch (Exception e) {
            System.out.println("Error in delete book!");
            responseEntity = ResponseEntity.badRequest().body(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return responseEntity;
    }

    @RequestMapping(value = "/review/{bookId}/delete", method = RequestMethod.DELETE)
    public ResponseEntity deleteAllReviews(@PathVariable("bookId") long bookId) {
        Review review = null;
        List<Review> reviews = reviewService.getAllBookReview(bookId);
        ResponseEntity responseEntity = null;
        Iterator<Review> iterator = reviews.iterator();

        try {
            while (iterator.hasNext()) {
                review = iterator.next();
                reviewService.deleteReview(review.getReviewId());
            }
            responseEntity = ResponseEntity.ok().build();
        } catch (Exception e) {
            responseEntity = ResponseEntity.badRequest().body(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return  responseEntity;
    }
}
