package id.ac.ui.cs.pustakaone.bookshop.controller;

import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("/shop")
public class CartController {
    @GetMapping("/")
    public String getHello() {
        return "Hello bookshop!";
    }
    @PostMapping("/cart/add")
    public String addBookToCart() {
        return "Successfully added book to cart";
    }

    @PostMapping("/cart/update")
    public String updateBookQuantity() {
        return "Successfully updated book quantity in cart";
    }

    @PostMapping("/cart/checkout")
    public String checkoutCart() {
        return "Checkout successful";
    }

    @PostMapping("/cart/pay")
    public String payCart() {
        return "Payment successful";
    }

    @GetMapping("/cart")
    public String getCart() {return "Get Cart!";}
}