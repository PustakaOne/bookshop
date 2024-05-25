package id.ac.ui.cs.pustakaone.bookshop.service;

import id.ac.ui.cs.pustakaone.bookshop.dto.CreateBookDTO;
import id.ac.ui.cs.pustakaone.bookshop.model.Book;

import java.util.Date;
import java.util.List;

public interface BookService {
    public Book addNewBook(Book book);

    public Book createBook(CreateBookDTO createBookDto);
    public Book getBookDetail(long id);
    public List<Book> getAllBooks();
    public Book updateBook(Book book);
    public void deleteBook(long id);
}
