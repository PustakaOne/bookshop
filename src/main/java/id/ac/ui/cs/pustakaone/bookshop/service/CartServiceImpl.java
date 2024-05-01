package id.ac.ui.cs.pustakaone.bookshop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import id.ac.ui.cs.pustakaone.bookshop.model.Cart;
import id.ac.ui.cs.pustakaone.bookshop.repository.CartRepository;

import java.util.Date;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartRepository cartRepository;

    @Override
    public Cart createCart(String userId) {
        Cart cart = new Cart(userId);
        cartRepository.save(cart);
        return cart;
    }

    @Override
    public void checkoutCart(Long cartId) {
        Cart cart = cartRepository.findById(cartId).orElseThrow(() -> new RuntimeException("Cart not found"));
        cart.setStatus("processed");
        cartRepository.save(cart);
    }

    @Override
    public void cancelPay(Long cartId) {
        Cart cart = cartRepository.findById(cartId).orElseThrow(() -> new RuntimeException("Cart not found"));
        cart.setStatus("belum");
        cartRepository.save(cart);
    }

    @Override
    public void payCart(Long cartId) {
        Cart cart = cartRepository.findById(cartId).orElseThrow(() -> new RuntimeException("Cart not found"));
        cart.setStatus("menunggu pengiriman");
        cart.setPaymentSuccess(true);
        cart.setPaidAt(new Date());
//        cart.setLastId(cart.getLastId() + 1);
        cartRepository.save(cart);
    }
}
