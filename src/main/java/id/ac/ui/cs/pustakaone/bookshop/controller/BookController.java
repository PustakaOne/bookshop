package id.ac.ui.cs.pustakaone.bookshop.controller;

import id.ac.ui.cs.pustakaone.bookshop.model.Book;
import id.ac.ui.cs.pustakaone.bookshop.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class BookController {
    @Autowired
    private BookService bookService;

    @RequestMapping(value = "/books", method = RequestMethod.GET)
    public ResponseEntity<List<Book>> getAllBooks() {
        ResponseEntity<List<Book>> responseEntity = null;
        List<Book> books = bookService.getAllBooks();
        responseEntity = ResponseEntity.ok(books);
        return responseEntity;
    }

    @RequestMapping(value = "/book/{id}", method = RequestMethod.GET)
    public ResponseEntity<Book> getBookDetail(@PathVariable long id) {
        ResponseEntity<Book> responseEntity = null;
        bookService.getBookDetail(id);
        responseEntity = ResponseEntity.ok().build();
        return responseEntity;
    }
}
