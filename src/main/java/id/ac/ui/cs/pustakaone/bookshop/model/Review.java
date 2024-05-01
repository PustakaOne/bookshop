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
@Table(name = "Review")
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_id", updatable = false, nullable = false)
    private Long reviewId;

    @Column(name = "book_id")
    private Long bookId;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "content")
    private String content;

    @Column(name = "rating")
    private int rating;

    @Column(name = "created_at")
    private Date createdAt;

    @Column(name = "updated_at")
    private Date updatedAt;
}
