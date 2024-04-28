package id.ac.ui.cs.pustakaone.bookshop.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Table(name = "BookCart")
@Entity
public class BookCart {
    public BookCart(Book book, Cart cart, int amount) {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @ManyToOne
    private Book book;

    @ManyToOne
    private Cart cart;

    @Column(name = "amount")
    private int amount;

    public void incrementAmount() {}

    public void decrementAmount() {}
//    private Book book;
//    private Cart cart;
//
//    @Getter
//    private int amount;
//
//    public BookCart(String id, Book book, Cart cart, int amount) {
//        this.id = id;
//        this.book = book;
//        this.cart = cart;
//        this.amount = amount;
//    }
//
//    public void incrementAmount() {
//        this.amount++;
//    }
//
//    public void decrementAmount() {
//        this.amount--;
//    }
//
//    public String getBookCartDetail() {
//        return this.book.getId();
//    }
}