package id.ac.ui.cs.pustakaone.bookshop.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Book")
public class Book {
    @Id
    @Column(name = "book_id", nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookId;

    @Column(name = "title")
    private String title;

    @Column(name = "author")
    private String author;

    @Column(name = "publisher")
    private String publisher;

    @Column(name = "description")
    private String description;

    @Column(name = "price")
    private int price;

    @Column(name = "stock")
    private int stock;

    @Column(name = "release_date")
    private Date releaseDate;

    @Column(name = "isbn")
    private String isbn;

    @Column(name = "cover_url")
    private String coverUrl;

    @Column(name = "category")
    private String category;

    @Column(name = "pages")
    private int pages;

    @Column(name = "lang")
    private String lang;

    public void decrementStock(int amount) {
        if (this.stock < amount) {
            throw new IllegalStateException("Insufficient stock available");
        }
        this.stock -= amount;
    }

}
