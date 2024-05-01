package id.ac.ui.cs.pustakaone.bookshop.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/shop/cart")
public class CartController {
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
}
