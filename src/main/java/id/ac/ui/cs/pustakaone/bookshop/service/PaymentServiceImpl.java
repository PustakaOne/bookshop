package id.ac.ui.cs.pustakaone.bookshop.service;

import id.ac.ui.cs.pustakaone.bookshop.model.Book;
import id.ac.ui.cs.pustakaone.bookshop.model.BookCart;
import id.ac.ui.cs.pustakaone.bookshop.model.Cart;
import id.ac.ui.cs.pustakaone.bookshop.repository.BookRepository;
import id.ac.ui.cs.pustakaone.bookshop.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private CartRepository cartRepository;

    @Override
    public void processPayment(Cart cart) {
        for (BookCart bookCart : cart.getBookCarts()) {
            Book book = bookCart.getBook();
            book.decrementStock(bookCart.getAmount());
            bookRepository.save(book);
        }
        cart.setStatus("menunggu pengiriman");
        cart.setPaymentSuccess(true);
        cart.setPaidAt(new Date());
        cartRepository.save(cart);
    }
}
