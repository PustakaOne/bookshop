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
        this.book = book;
        this.cart = cart;
        this.amount = amount;
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

    public void incrementAmount() {
        if (this.amount == this.book.getStock()) {
            throw new IllegalStateException("Jumlah barang melebihi stock yang tersedia!");
        }
        this.amount++;
    }

    public void decrementAmount() {
        if (this.amount == 1) {
            throw new IllegalStateException("Jumlah barang tidak boleh 0");
        }
        this.amount--;
    }
}