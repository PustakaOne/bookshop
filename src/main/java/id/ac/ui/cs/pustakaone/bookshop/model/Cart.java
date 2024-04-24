package id.ac.ui.cs.pustakaone.bookshop.model;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

public class Cart {

    private String id;
    @Setter
    private int totalPrice;
    private String paymentStatus;
    @Getter
    private List<BookCart> bookCarts;

    public Cart(String id) {
        this.id = id;
        this.bookCarts = new ArrayList<>();
        this.paymentStatus = "not paid";
    }

    public int getTotalHarga() {
        return totalPrice;
    }

    public void setPaymentStatus(String status) {
        this.paymentStatus = status;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void addBookCart(BookCart bookCart) {
        this.bookCarts.add(bookCart);
    }

}