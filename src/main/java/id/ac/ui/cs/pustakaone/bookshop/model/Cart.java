package id.ac.ui.cs.pustakaone.bookshop.model;

import jakarta.persistence.*;
import lombok.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;


@Entity
@Table(name = "Cart")
@NoArgsConstructor
@Setter
@Getter
public class Cart {
    public Cart(String id) {
        this.id = id;
    }

    @Id
    @Column(name="id", nullable = false)
    private String id;

    @Column(name="totalPrice", nullable = false)
    private int totalPrice;

    @Column(name = "isPaymentSuccess", nullable=false)
    private boolean isPaymentSuccess;


    @OneToMany(mappedBy = "cart")
    private Set<BookCart> bookCarts;

    public int getTotalHarga() {
        return totalPrice;
    }

    public void setPaymentStatus(boolean status) {
        this.isPaymentSuccess = status;
    }

    public boolean getPaymentStatus() {
        return isPaymentSuccess;
    }

    public void addBookCart(BookCart bookCart) {
        this.bookCarts.add(bookCart);
    }
}