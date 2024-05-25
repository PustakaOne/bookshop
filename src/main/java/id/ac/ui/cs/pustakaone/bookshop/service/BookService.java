package id.ac.ui.cs.pustakaone.bookshop.service;

import id.ac.ui.cs.pustakaone.bookshop.dto.CreateUpdateBookDTO;
import id.ac.ui.cs.pustakaone.bookshop.model.Book;

import java.util.List;

public interface BookService {
    public Book addNewBook(Book book);

    public Book createBook(CreateUpdateBookDTO createBookDto);
    public Book getBookDetail(long id);
    public List<Book> getAllBooks();
    public Book updateBook(Long bookId, CreateUpdateBookDTO updateBookDto);
    public void deleteBook(long id);
}
