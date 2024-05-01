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
    public Cart(Long userId) {
        this.userId = userId;
        this.totalPrice = 0;
        this.paymentSuccess = false;
        this.status = "belum";
        this.bookCarts = new ArrayList<>();
        this.address = "";
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "lastId")
    private Long lastId; //last idcart yang sudah di paid, remember id itu increment

    @Column(name = "userId")
    private Long userId;

    @Column(name = "totalPrice")
    private int totalPrice;

    @Column(name = "paymentSuccess")
    private boolean paymentSuccess;

    @Column(name = "status")
    private String status;

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
    public void addBookCart(BookCart bookcart) {
        this.bookCarts.add(bookcart);
    }

    public void removeBookCart(BookCart bookCart) {
        this.bookCarts.remove(bookCart);
    }
}