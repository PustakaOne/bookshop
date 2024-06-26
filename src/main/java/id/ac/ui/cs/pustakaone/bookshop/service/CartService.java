package id.ac.ui.cs.pustakaone.bookshop.service;

import id.ac.ui.cs.pustakaone.bookshop.model.Book;
import id.ac.ui.cs.pustakaone.bookshop.model.Cart;
import org.springframework.http.ResponseEntity;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public interface CartService {
    Cart getCartByUserId(Long userId);
    List<Cart> getUserPaymentHistory(Long userId);
    void checkoutCart(Long cartId);
    void cancelPay(Long cartId);
    void payCart(Long cartId);
    ResponseEntity<List<Cart>> getCarts();
    ResponseEntity<?> finishPayment(Long idCart);
    Book deleteBookFromCart(Long userId, Long bookCartId);
}
