package id.ac.ui.cs.pustakaone.bookshop.service;

import id.ac.ui.cs.pustakaone.bookshop.model.Book;
import id.ac.ui.cs.pustakaone.bookshop.model.Cart;
import org.springframework.stereotype.Service;

@Service
public interface CartService {
    Cart getCartByUserId(Long userId);
    Book deleteBookFromCart(Long userId, Long bookCartId);
}
