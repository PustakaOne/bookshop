package id.ac.ui.cs.pustakaone.bookshop.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
    private Long id;

    @ManyToOne
    @JoinColumn(name = "book_id",referencedColumnName = "book_id")
    private Book book;

    @ManyToOne
    @JoinColumn(name = "cart_id",referencedColumnName = "id")
    @JsonBackReference
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