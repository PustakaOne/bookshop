package id.ac.ui.cs.pustakaone.bookshop.controller;

import id.ac.ui.cs.pustakaone.bookshop.model.Cart;
import id.ac.ui.cs.pustakaone.bookshop.service.CartServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/shop/cart")
public class CartController {
    @Autowired
    CartServiceImpl cartService;

    @PostMapping("/add/{bookId}")
    public String addBookToCart(@PathVariable("bookId") int bookId) {
        return "Successfully added Book " + bookId + " to cart";
    }

    @PostMapping("/update/{bookId}")
    public String updateBookQuantity(@PathVariable("bookId") int bookId) {
        return "Successfully updated quantity for Book " + bookId + " in cart";
    }

    @PostMapping("/checkout")
    public String checkoutCart() {
        return "Checkout successful";
    }

    @PostMapping("/pay")
    public String payCart() {
        return "Payment successful";
    }

    @GetMapping("/{userId}")
    public ResponseEntity<Cart> getCart(@PathVariable String userId) {}
}
