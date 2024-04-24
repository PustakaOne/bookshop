package id.ac.ui.cs.pustakaone.bookshop.model;

import java.util.Date;

public interface BookBuilder {
    public void reset();
    public void buildBookId(String id);
    public void buildTitle(String title);
    public void buildAuthor(String author);
    public void buildPublisher(String publisher);
    public void buildDescription(String description);
    public void buildPrice(int price);
    public void buildStock(int stock);
    public void buildReleaseDate(Date releaseDate);
    public void buildIsbn(String isbn);
    public void buildCoverUrl(String coverUrl);
    public void buildCategory(String category);
    public void buildPages(int pages);
    public void buildLang(String lang);
    public Book getBook();
}
