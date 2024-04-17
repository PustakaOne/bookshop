package id.ac.ui.cs.pustakaone.bookshop.model;

import lombok.Getter;

public class BookCart {

    private String id;
    private Book book;
    private Cart cart;

    @Getter
    private int amount;

    public BookCart(String id, Book book, Cart cart, int amount) {
        this.id = id;
        this.book = book;
        this.cart = cart;
        this.amount = amount;
    }

    public void incrementAmount() {
        this.amount++;
    }

    public void decrementAmount() {
        this.amount--;
    }

    public String getBookCartDetail() {
        return this.book.getId();
    }

}