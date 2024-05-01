package id.ac.ui.cs.pustakaone.bookshop.service;

import id.ac.ui.cs.pustakaone.bookshop.model.Book;
import id.ac.ui.cs.pustakaone.bookshop.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public Book addNewBook(Book book) {
        Book newBook = Book.builder()
                .title(book.getTitle())
                .author(book.getAuthor())
                .description(book.getDescription())
                .price(book.getPrice())
                .stock(book.getStock())
                .releaseDate(book.getReleaseDate())
                .coverUrl(book.getCoverUrl())
                .publisher(book.getPublisher())
                .isbn(book.getIsbn())
                .pages(book.getPages())
                .lang(book.getLang())
                .category(book.getCategory())
                .build();
        return bookRepository.save(newBook);
    }

    @Override
    public Book getBookDetail(long id) {
        return bookRepository.findById(id).get();
    }

    @Override
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public Book updateBook(Book book) {
        Book newBook = Book.builder()
                .bookId(book.getBookId())
                .title(book.getTitle())
                .author(book.getAuthor())
                .description(book.getDescription())
                .price(book.getPrice())
                .stock(book.getStock())
                .releaseDate(book.getReleaseDate())
                .coverUrl(book.getCoverUrl())
                .publisher(book.getPublisher())
                .isbn(book.getIsbn())
                .pages(book.getPages())
                .lang(book.getLang())
                .category(book.getCategory())
                .build();
        return bookRepository.save(newBook);
    }

    @Override
    public void deleteBook(long id) {
        bookRepository.deleteById(id);
    }
}
