package id.ac.ui.cs.pustakaone.bookshop.controller;

import id.ac.ui.cs.pustakaone.bookshop.dto.BookWithReviewsDTO;
import id.ac.ui.cs.pustakaone.bookshop.dto.CreateUpdateBookDTO;
import id.ac.ui.cs.pustakaone.bookshop.model.Book;
import id.ac.ui.cs.pustakaone.bookshop.model.Review;
import id.ac.ui.cs.pustakaone.bookshop.service.BookService;
import jakarta.persistence.EntityNotFoundException;
import id.ac.ui.cs.pustakaone.bookshop.service.ReviewServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.concurrent.Future;

@RestController
public class BookController {
    @Autowired
    private BookService bookService;
    private ReviewServiceImpl reviewService;

    @RequestMapping(value = "/books", method = RequestMethod.GET)
    public ResponseEntity getAllBooks() {
        ResponseEntity responseEntity = null;
        try {
            List<Book> books = bookService.getAllBooks();
            responseEntity = ResponseEntity.ok(books);
        } catch (Exception e) {
            responseEntity = ResponseEntity.badRequest().body(HttpStatus.BAD_REQUEST);
        }
        return responseEntity;
    }

    @RequestMapping(value = "/books/{id}", method = RequestMethod.GET)
    public ResponseEntity getBookDetail(@PathVariable long id) throws InterruptedException {
        ResponseEntity responseEntity = null;
        try {
            Book book = bookService.getBookDetail(id);
            Future<List<Review>> reviewFuture = reviewService.getReviews(id);
            List<Review> reviews = reviewFuture.get();
            BookWithReviewsDTO bookWithReviewsDTO = new BookWithReviewsDTO(book, reviews);
            responseEntity = ResponseEntity.ok().body(bookWithReviewsDTO);
        } catch (InterruptedException e) {
            // Rethrow the InterruptedException
            throw new InterruptedException("Thread was interrupted");
        } catch (Exception e) {
            responseEntity = ResponseEntity.badRequest().body(HttpStatus.BAD_REQUEST);
        }
        return responseEntity;
    }

    @PostMapping(value = "/book")
    public ResponseEntity createBook(@RequestBody CreateUpdateBookDTO createBookDto) {
        try {
            Book newBook = bookService.createBook(createBookDto);
            return ResponseEntity.ok().body(bookService.getBookDetail(newBook.getBookId()));
        } catch(Exception e) {
            return ResponseEntity.badRequest().body(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping(value = "/book/{id}")
    public ResponseEntity updateBookDetail(@PathVariable long id, @RequestBody CreateUpdateBookDTO updateBookDto) {
        try {
            bookService.getBookDetail(id);
            return ResponseEntity.ok().body(bookService.updateBook(id, updateBookDto));
        } catch(EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(HttpStatus.BAD_REQUEST);
        }
    }
}
