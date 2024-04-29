package id.ac.ui.cs.pustakaone.bookshop.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@Table(name = "Cart")
@Entity
public class Cart {
    public Cart(String userId) {
        this.userId = userId;
        this.totalPrice = 0;
        this.paymentSuccess = false;
        this.bookCarts = new ArrayList<>();
        this.address = "";
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @Column(name = "userId")
    private String userId;

    @Column(name = "totalPrice")
    private int totalPrice;

    @Column(name = "paymentSuccess")
    private boolean paymentSuccess;

    @OneToMany(mappedBy = "cart")
    private List<BookCart> bookCarts;

    @Column(name = "address")
    private String address;

    @Column(name = "paidAt")
    private Date paidAt;

    public void setTotalPrice(int totalPrice) {
        if (totalPrice < 0) {
            throw new IllegalArgumentException("Total price tidak boleh negatif");
        }
        this.totalPrice = totalPrice;
    }
}