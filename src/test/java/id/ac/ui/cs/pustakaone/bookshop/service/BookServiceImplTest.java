package id.ac.ui.cs.pustakaone.bookshop.service;

import id.ac.ui.cs.pustakaone.bookshop.model.Book;
import id.ac.ui.cs.pustakaone.bookshop.repository.BookRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class BookServiceImplTest {
    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookServiceImpl bookService;

    @Test
    void testAddNewBook() {
        Book book = new Book();
        bookService.addNewBook(book);
        verify(bookRepository, times(1)).save(any(Book.class));
    }

    @Test
    void testGetBookDetail() {
        long id = 1L;
        Book book = new Book();
        book.setBookId(1L);
        when(bookRepository.findById(id)).thenReturn(Optional.of(book));
        Book result = bookService.getBookDetail(id);
        assertEquals(book, result);
    }

    @Test
    void testGetAllBooks() {
        List<Book> books = new ArrayList<>();
        books.add(new Book());
        books.add(new Book());
        Iterator<Book> iterator = books.iterator();
        when(bookRepository.findAll()).thenReturn(books);

        List<Book> result = bookService.getAllBooks();
        assertEquals(2, result.size());
    }

    @Test
    void testUpdateBook() {
        Book currentBook = new Book();
        currentBook.setBookId(1L);
        Book newBook = new Book();
        newBook.setBookId(1L);

        bookService.updateBook(newBook);
        verify(bookRepository, times(1)).save(any(Book.class));
    }

    @Test
    void testDeleteBook() {
        Book book = new Book();
        book.setBookId(1L);
        bookService.deleteBook(book.getBookId());
        verify(bookRepository, times(1)).deleteById(book.getBookId());
    }
}
