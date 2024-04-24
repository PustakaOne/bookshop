package id.ac.ui.cs.pustakaone.bookshop.model;

import java.util.Date;

public class BookConcreteBuilder implements BookBuilder {
    private Book book;

    public BookConcreteBuilder() {
        this.book = new Book();
    }

    @Override
    public void reset() {
        this.book = new Book();
    }

    @Override
    public void buildBookId(String id) {
        this.book.setId(id);
    }

    @Override
    public void buildTitle(String title) {
        this.book.setTitle(title);
    }

    @Override
    public void buildAuthor(String author) {
        this.book.setAuthor(author);
    }

    @Override
    public void buildPublisher(String publisher) {
        this.book.setPublisher(publisher);
    }

    @Override
    public void buildDescription(String description) {
        this.book.setDescription(description);
    }

    @Override
    public void buildPrice(int price) {
        this.book.setPrice(price);
    }

    @Override
    public void buildStock(int stock) {
        this.book.setStock(stock);
    }

    @Override
    public void buildReleaseDate(Date releaseDate) {
        this.book.setReleaseDate(releaseDate);
    }

    @Override
    public void buildIsbn(String isbn) {
        this.book.setIsbn(isbn);
    }

    @Override
    public void buildCoverUrl(String coverUrl) {
        this.book.setCoverUrl(coverUrl);
    }

    @Override
    public void buildCategory(String category) {
        this.book.setCategory(category);
    }

    @Override
    public void buildPages(int pages) {
        this.book.setPages(pages);
    }

    @Override
    public void buildLang(String lang) {
        this.book.setLang(lang);
    }

    @Override
    public Book getBook() {
        return this.book;
    }
}

