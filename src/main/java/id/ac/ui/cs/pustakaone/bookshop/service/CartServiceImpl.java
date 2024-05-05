package id.ac.ui.cs.pustakaone.bookshop.service;

import id.ac.ui.cs.pustakaone.bookshop.model.Book;
import id.ac.ui.cs.pustakaone.bookshop.model.BookCart;
import id.ac.ui.cs.pustakaone.bookshop.model.Cart;
import id.ac.ui.cs.pustakaone.bookshop.repository.BookCartRepository;
import id.ac.ui.cs.pustakaone.bookshop.repository.CartRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {
    @Autowired
    CartRepository cartRepository;

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

        bookCarts.remove(bookCart);

        bookCartRepository.delete(bookCart.get());

        return bookCart.get().getBook();
    }
}
