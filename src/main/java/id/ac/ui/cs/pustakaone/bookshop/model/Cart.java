package id.ac.ui.cs.pustakaone.bookshop.model;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

public class Cart {

    private String id;
    @Setter
    private int totalPrice;
    private boolean isPaymentSuccess;
    @Getter
    private List<BookCart> bookCarts;

    public Cart(String id) {
    }

    public int getTotalHarga() {
    }

    public void setPaymentStatus(boolean status) {
    }

    public boolean getPaymentStatus() {
    }

    public void addBookCart(BookCart bookCart) {
    }

}