package id.ac.ui.cs.pustakaone.bookshop.model;

import java.util.Date;
import java.util.List;

public interface BookBuilder {
    public void reset();
    public void setBookId(String id);
    public void setTitle(String title);
    public void setAuthor(String author);
    public void setPublisher(String publisher);
    public void setDescription(String description);
    public void setPrice(int price);
    public void setStock(int stock);
    public void setReleaseDate(Date releaseDate);
    public void setIsbn(String isbn);
    public void setCoverUrl(String coverUrl);
    public void setCategory(String category);
    public void setPages(int pages);
    public void setLang(String lang);
}
