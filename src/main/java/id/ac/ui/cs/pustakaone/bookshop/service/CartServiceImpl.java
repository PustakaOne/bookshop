package id.ac.ui.cs.pustakaone.bookshop.service;

import id.ac.ui.cs.pustakaone.bookshop.repository.BookCartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import id.ac.ui.cs.pustakaone.bookshop.model.Cart;
import id.ac.ui.cs.pustakaone.bookshop.repository.CartRepository;
import id.ac.ui.cs.pustakaone.bookshop.repository.BookRepository;
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
    @Autowired
    BookRepository bookRepository;
    @Autowired
    PaymentService paymentService;

    @Override
    public Cart getCartByUserId(Long userId) {
        Cart cart = cartRepository.findByUserIdAndPaymentSuccessIsFalse(userId);
        if (cart == null) {
            cart = new Cart(userId);
            cartRepository.save(cart);
        }
        return cart;
    }

    public List<Cart> getUserPaymentHistory(Long userId){
        return cartRepository.findByUserIdAndPaymentSuccessIsTrue(userId);
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
        paymentService.processPayment(cart);
    }



    @Override
    public ResponseEntity<List<Cart>> getCarts(){
        List<Cart> carts = cartRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
        return ResponseEntity.ok(carts);
    }

    @Override
    public ResponseEntity<?> finishPayment(Long idCart) {
        Optional<Cart> optionalCart = cartRepository.findById(idCart);
        if (!optionalCart.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        Cart cart = optionalCart.get();
        cart.setStatus("selesai");
        cart.setPaidAt(new Date());
        cartRepository.save(cart);
        return ResponseEntity.ok(cart);
    }

    @Override
    public Book deleteBookFromCart(Long userId, Long bookCartId) {
        Optional<BookCart> bookCart = bookCartRepository.findById(bookCartId);

        if (bookCart.isEmpty()) {
            throw new EntityNotFoundException("Bookcart not found! 1");
        }

        Cart cart = cartRepository.findByUserIdAndPaymentSuccessIsFalse(userId);
        if (cart == null) {
            throw new EntityNotFoundException("Bookcart not found! 2");
        }

        List<BookCart> bookCarts = cart.getBookCarts();

        if (!bookCarts.contains(bookCart.get())) {
            throw new EntityNotFoundException("Bookcart not found! 3");
        }

        cart.getBookCarts().remove(bookCart);

        bookCartRepository.delete(bookCart.get());

        cartRepository.save(cart);

        return bookCart.get().getBook();
    }
}
