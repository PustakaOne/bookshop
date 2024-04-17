package id.ac.ui.cs.pustakaone.bookshop.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController()
public class CartController {
    @GetMapping("/")
    public String getHello() {
        return "Hello bookshop!";
    }
    @PostMapping("/shop/cart/add")
    public String addBookToCart() {
        return "Successfully added book to cart";
    }

    @PostMapping("/shop/cart/update")
    public String updateBookQuantity() {
        return "Successfully updated book quantity in cart";
    }

    @PostMapping("/shop/cart/checkout")
    public String checkoutCart() {
        return "Checkout successful";
    }

    @PostMapping("/shop/cart/pay")
    public String payCart() {
        return "Payment successful";
    }



}