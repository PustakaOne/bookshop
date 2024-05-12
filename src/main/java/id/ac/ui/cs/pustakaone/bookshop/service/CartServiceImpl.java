package id.ac.ui.cs.pustakaone.bookshop.service;

import id.ac.ui.cs.pustakaone.bookshop.repository.BookCartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import id.ac.ui.cs.pustakaone.bookshop.model.Cart;
import id.ac.ui.cs.pustakaone.bookshop.repository.CartRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import id.ac.ui.cs.pustakaone.bookshop.model.Book;
import id.ac.ui.cs.pustakaone.bookshop.model.BookCart;
import jakarta.persistence.EntityNotFoundException;

import java.util.List;
import java.util.Optional;


@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartRepository cartRepository;
    @Autowired
    BookCartRepository bookCartRepository;

    @Override
    public Cart getCartByUserId(Long userId) {
        Cart cart = cartRepository.findByUserIdAndPaymentSuccessIsFalse(userId);
        if (cart == null) {
            cart = new Cart(userId);
            cartRepository.save(cart);
        }
        return cart;
    }


    @Override
    public void checkoutCart(Long userId) {

        Cart cart = this.getCartByUserId(userId);
        cart.setStatus("processed");
        cartRepository.save(cart);
    }

    @Override
    public void cancelPay(Long userId) {
        Cart cart = this.getCartByUserId(userId);
        cart.setStatus("belum");
        cartRepository.save(cart);
    }

    @Override
    public void payCart(Long userId) {
        Cart cart = this.getCartByUserId(userId);
        cart.setStatus("menunggu pengiriman");
        cart.setPaymentSuccess(true);
        cart.setPaidAt(new Date());
        cartRepository.save(cart);
    }

    @Override
    public ResponseEntity<List<Cart>> getCarts(){
        List<Cart> carts = cartRepository.findAll();
        return ResponseEntity.ok(carts);
    }

    @Override
    public ResponseEntity<?> finishPayment(Long idCart) {
        Cart cart = this.getCartByUserId(idCart);
        cart.setStatus("selesai");
        cart.setPaidAt(new Date());
        cartRepository.save(cart);
        return ResponseEntity.ok(cart);
    }

    @Override
    public Book deleteBookFromCart(Long userId, Long bookCartId) {
        Optional<BookCart> bookCart = bookCartRepository.findById(bookCartId);

        if (bookCart.isEmpty()) {
            throw new EntityNotFoundException("Bookcart not found!");
        }

        Cart cart = cartRepository.findByUserIdAndPaymentSuccessIsFalse(userId);
        if (cart == null) {
            throw new EntityNotFoundException("Bookcart not found!");
        }

        List<BookCart> bookCarts = cart.getBookCarts();

        if (!bookCarts.contains(bookCart.get())) {
            throw new EntityNotFoundException("Bookcart not found!");
        }

        bookCarts.remove(bookCart);

        bookCartRepository.delete(bookCart.get());

        return bookCart.get().getBook();
    }
}
