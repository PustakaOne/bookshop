package id.ac.ui.cs.pustakaone.bookshop.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class Review {
    private String id;
    private Book book;
    private User user;
    private String review;
    private int rating;
    private Date createdAt;
    private Date updatedAt;
}
