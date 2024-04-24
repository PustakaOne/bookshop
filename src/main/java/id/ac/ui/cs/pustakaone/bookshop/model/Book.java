package id.ac.ui.cs.pustakaone.bookshop.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class Book {
    private String id;
    private String title;
    private String author;
    private String publisher;
    private String description;
    private int stock;
    private Date releaseDate;
    private String isbn;
    private String coverUrl;
    private String category;
    private int pages;
    private String lang;
}