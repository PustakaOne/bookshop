package id.ac.ui.cs.pustakaone.bookshop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import id.ac.ui.cs.pustakaone.bookshop.model.BookCart;
import id.ac.ui.cs.pustakaone.bookshop.model.Book;
import id.ac.ui.cs.pustakaone.bookshop.model.Cart;
import id.ac.ui.cs.pustakaone.bookshop.repository.BookCartRepository;
import id.ac.ui.cs.pustakaone.bookshop.repository.CartRepository;
import id.ac.ui.cs.pustakaone.bookshop.repository.BookRepository;

@Service
public class BookCartServiceImpl implements BookCartService {

    @Autowired
    private BookCartRepository bookCartRepository;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private BookRepository bookRepository;

    @Override
    public BookCart addBookToCart(Long cartId, Long bookId, int amount) {

        Cart cart = cartRepository.findById(cartId).orElseThrow(() -> new RuntimeException("Cart not found"));
        Book book = bookRepository.findById(bookId).orElseThrow(() -> new RuntimeException("Book not found"));
        if (amount > book.getStock()) {
            throw new IllegalArgumentException("Amount exceeds stock available");
        }
        BookCart bookCart = new BookCart(book, cart, amount);

        cart.getBookCarts().add(bookCart);
        bookCartRepository.save(bookCart);
        cartRepository.save(cart);

        return bookCart;
    }

    @Override
    public BookCart updateBookAmountInCart(Long bookCartId, int newAmount) {
        BookCart bookCart = bookCartRepository.findById(bookCartId).orElseThrow(() -> new RuntimeException("BookCart not found"));
        if (newAmount < 1 || newAmount > bookCart.getBook().getStock()) {
            throw new IllegalArgumentException("Invalid amount");
        }
        bookCart.setAmount(newAmount);
        bookCartRepository.save(bookCart);
        return bookCart;
    }

}
