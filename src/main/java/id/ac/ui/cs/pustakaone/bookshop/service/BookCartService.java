package id.ac.ui.cs.pustakaone.bookshop.service;

import id.ac.ui.cs.pustakaone.bookshop.model.BookCart;

public interface BookCartService {
    BookCart addBookToCart(Long userId, Long bookId, int amount);
    BookCart updateBookAmountInCart(Long userId,Long bookCartId, int newAmount);
}
