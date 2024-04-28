package id.ac.ui.cs.pustakaone.bookshop.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@Table(name = "Cart")
@Entity
public class Cart {
    public Cart(String userId) {

    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
//    @Setter
//    private int totalPrice;
//    private boolean isPaymentSuccess;
//    @Getter
//    private List<BookCart> bookCarts;
//
//    public Cart(String id) {
//        this.id = id;
//        this.bookCarts = new ArrayList<>();
//    }
//
//    public int getTotalHarga() {
//        return totalPrice;
//    }
//
//    public void setPaymentStatus(boolean status) {
//        this.isPaymentSuccess = status;
//    }
//
//    public boolean getPaymentStatus() {
//        return isPaymentSuccess;
//    }
//
//    public void addBookCart(BookCart bookCart) {
//        this.bookCarts.add(bookCart);
//    }

}