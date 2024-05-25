package id.ac.ui.cs.pustakaone.bookshop.service;

import id.ac.ui.cs.pustakaone.bookshop.model.Book;
import id.ac.ui.cs.pustakaone.bookshop.model.Cart;
import org.springframework.http.ResponseEntity;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public interface PaymentService {
    void processPayment(Cart cart);
}
