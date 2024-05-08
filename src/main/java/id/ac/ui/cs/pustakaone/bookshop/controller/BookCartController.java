package id.ac.ui.cs.pustakaone.bookshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import id.ac.ui.cs.pustakaone.bookshop.model.BookCart;
import id.ac.ui.cs.pustakaone.bookshop.service.BookCartService;

@RestController
@RequestMapping("/shop/bookcart")
public class BookCartController {

    private final BookCartService bookCartService;

    @Autowired
    public BookCartController(BookCartService bookCartService) {
        this.bookCartService = bookCartService;
    }

    @PostMapping("/add/{userId}/{bookId}/{amount}")
    public ResponseEntity<?> addBookToCart(@PathVariable Long userId, @PathVariable Long bookId, @PathVariable int amount) {
        try {
            BookCart bookCart = bookCartService.addBookToCart(userId, bookId, amount);
            return ResponseEntity.ok(bookCart);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PutMapping("/update/{userId}/{bookCartId}/{newAmount}")
    public ResponseEntity<?> updateBookAmountInCart(@PathVariable Long userId, @PathVariable Long bookCartId, @PathVariable int newAmount) {
        try {
            BookCart bookCart = bookCartService.updateBookAmountInCart(userId, bookCartId, newAmount);
            return ResponseEntity.ok(bookCart);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

}
