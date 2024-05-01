package id.ac.ui.cs.pustakaone.bookshop.service;

import id.ac.ui.cs.pustakaone.bookshop.model.Cart;

public interface CartService {
    Cart createCart(String userId);
    void checkoutCart(Long cartId);
    void cancelPay(Long cartId);
    void payCart(Long cartId);
}
