package id.ac.ui.cs.pustakaone.bookshop.model;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

public class Cart {

    private String id;
    @Setter
    private int totalPrice;
    private PaymentState paymentState;
    @Getter
    private List<BookCart> bookCarts;

    public Cart(String id) {
        this.id = id;
        this.bookCarts = new ArrayList<>();
        this.paymentState = new NotPaidState(); // default state
    }

    public int getTotalHarga() {
        return totalPrice;
    }

    public String getPaymentStatus() {
        return paymentState.getStatus();
    }

    public void processPayment() {
        this.paymentState.processPayment(this);
    }

    public void completePayment() {
        this.paymentState.completePayment(this);
    }

    public void addBookCart(BookCart bookCart) {
        this.bookCarts.add(bookCart);
    }

    public void setState(PaymentState state) {
        this.paymentState = state;
    }
}
