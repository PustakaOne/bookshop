package id.ac.ui.cs.pustakaone.bookshop.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "BookCart")
public class BookCart {
    public BookCart(String id, Book book, Cart cart) {
        this.id = id;
        this.book = book;
        this.cart = cart;
        this.amount = 1;
    }

    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "bookId")
    private String bookId;

    @Column(name = "amount")
    private int amount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cartId")
    private Cart cart;

    public void incrementAmount() {
        this.amount++;
    }

    public void decrementAmount() {
        this.amount--;
    }

    public String getBookCartDetail() {
        return this.getId();
    }

}