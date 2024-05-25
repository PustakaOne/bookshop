package id.ac.ui.cs.pustakaone.bookshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import id.ac.ui.cs.pustakaone.bookshop.model.Cart;
import id.ac.ui.cs.pustakaone.bookshop.service.CartServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import id.ac.ui.cs.pustakaone.bookshop.model.Cart;
import id.ac.ui.cs.pustakaone.bookshop.service.CartService;
import id.ac.ui.cs.pustakaone.bookshop.service.BookCartService;

import java.util.HashMap;
import java.util.List;
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/shop/cart")
public class CartController {

    private  CartService cartService;
    private  BookCartService bookCartService;

    @Autowired
    public CartController(CartService cartService, BookCartService bookCartService) {
        this.cartService = cartService;
        this.bookCartService = bookCartService;
    }

    @GetMapping("/get/{userId}")
    public ResponseEntity<Cart> getCartByUserId(@PathVariable Long userId) {
        try {
            Cart cart = cartService.getCartByUserId(userId);
            return ResponseEntity.ok(cart);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/history/{userId}")
    public ResponseEntity<List<Cart>> getPaymentHistory(@PathVariable Long userId){
        try{
            List<Cart> paymentHistory = cartService.getUserPaymentHistory(userId);
            if(paymentHistory.isEmpty()){
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(paymentHistory);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }

    }



    @PostMapping("/checkout/{userId}")
    public ResponseEntity<Void> checkoutCart(@PathVariable Long userId) {
        try {
            cartService.checkoutCart(userId);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping("/cancel/{userId}")
    public ResponseEntity<Void> cancelPayment(@PathVariable Long userId) {
        try {
            cartService.cancelPay(userId);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping("/pay/{userId}")
    public ResponseEntity<Void> payCart(@PathVariable Long userId) {
        try {
            cartService.payCart(userId);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping("/addBook/{userId}/{bookId}/{amount}")
    public ResponseEntity<?> addBookToCart(@PathVariable Long userId, @PathVariable Long bookId, @PathVariable int amount) {
        try {
            var bookCart = bookCartService.addBookToCart(userId, bookId, amount);
            return ResponseEntity.ok(bookCart);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/{userId}")
    public ResponseEntity<Cart> getCart(@PathVariable String userId) {
        try {
            Cart cart = cartService.getCartByUserId(Long.parseLong(userId));
            return ResponseEntity.ok(cart);
        } catch (Exception err) {
            System.out.println(err);
            return ResponseEntity.internalServerError().build();
        }
    }
    @PutMapping("/updateBook/{userId}/{bookCartId}/{newAmount}")
    public ResponseEntity<?> updateBookAmountInCart(@PathVariable Long userId, @PathVariable Long bookCartId, @PathVariable int newAmount) {
        try {
            var bookCart = bookCartService.updateBookAmountInCart(userId, bookCartId, newAmount);
            return ResponseEntity.ok(bookCart);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/getCarts")
    public ResponseEntity<List<Cart>> getCarts() {
        return cartService.getCarts();
    }

    @PostMapping("/finishPayments")
    public ResponseEntity<?> finishPayments(@RequestBody HashMap<String, String> body) {
        Long idCart = Long.valueOf(body.get("idCart"));
        return cartService.finishPayment(idCart);
    }
}

//