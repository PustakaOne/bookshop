package id.ac.ui.cs.pustakaone.bookshop.service;

import id.ac.ui.cs.pustakaone.bookshop.model.Cart;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CartService {
    Cart getCartByUserId(Long userId);
    void checkoutCart(Long cartId);
    void cancelPay(Long cartId);
    void payCart(Long cartId);
    ResponseEntity<List<Cart>> getCarts();
    ResponseEntity<?> finishPayment(Long idCart);
}
