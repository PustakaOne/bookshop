package id.ac.ui.cs.pustakaone.bookshop.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateUpdateBookDTO {
    private String title;
    private String author;
    private String description;
    private Integer price;
    private Integer stock;
    private Date releaseDate;
    private String coverUrl;
    private String publisher;
    private String isbn;
    private Integer pages;
    private String lang;
    private String category;
}
