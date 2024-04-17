package id.ac.ui.cs.pustakaone.bookshop.model;

import lombok.Getter;

public class BookCart {

    private String id;
    private Book book;
    private Cart cart;

    @Getter
    private int amount;

    public BookCart(String id, Book book, Cart cart, int amount) {
    }

    public void incrementAmount() {
    }

    public void decrementAmount() {
    }

    public String getBookCartDetail() {
    }

}