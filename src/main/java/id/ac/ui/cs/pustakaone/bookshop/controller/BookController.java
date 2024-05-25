package id.ac.ui.cs.pustakaone.bookshop.controller;

import id.ac.ui.cs.pustakaone.bookshop.dto.CreateBookDTO;
import id.ac.ui.cs.pustakaone.bookshop.model.Book;
import id.ac.ui.cs.pustakaone.bookshop.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
public class BookController {
    @Autowired
    private BookService bookService;

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

    @RequestMapping(value = "/book/{id}", method = RequestMethod.GET)
    public ResponseEntity getBookDetail(@PathVariable long id) {
        ResponseEntity responseEntity = null;
        try {
            bookService.getBookDetail(id);
            responseEntity = ResponseEntity.ok().body(bookService.getBookDetail(id));
        } catch (Exception e) {
            responseEntity = ResponseEntity.badRequest().body(HttpStatus.BAD_REQUEST);
        }
        return responseEntity;

    }

    @PostMapping(value = "/book")
    public ResponseEntity createBook(@RequestBody CreateBookDTO createBookDto) {
        return null;
    }
}
