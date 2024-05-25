package id.ac.ui.cs.pustakaone.bookshop.service;

import id.ac.ui.cs.pustakaone.bookshop.dto.CreateUpdateBookDTO;
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
    public void testUpdateBook() {
        CreateUpdateBookDTO updateBookDto = new CreateUpdateBookDTO();
        updateBookDto.setTitle("Updated Title");
        updateBookDto.setAuthor("Updated Author");

        Book existingBook;
        existingBook = new Book();
        existingBook.setBookId(1L);
        existingBook.setTitle("Original Title");
        existingBook.setAuthor("Original Author");

        when(bookRepository.findById(anyLong())).thenReturn(Optional.of(existingBook));
        when(bookRepository.save(any(Book.class))).thenReturn(existingBook);

        Book result = bookService.updateBook(1L, updateBookDto);

        verify(bookRepository).findById(1L);
        verify(bookRepository).save(existingBook);

        assertEquals("Updated Title", result.getTitle());
        assertEquals("Updated Author", result.getAuthor());
    }

    @Test
    void testDeleteBook() {
        Book book = new Book();
        book.setBookId(1L);
        bookService.deleteBook(book.getBookId());
        verify(bookRepository, times(1)).deleteById(book.getBookId());
    }

    @Test
    public void testCreateBook() {
        CreateUpdateBookDTO createBookDto = new CreateUpdateBookDTO();

        Book savedBook = new Book();

        when(bookRepository.save(any(Book.class))).thenReturn(savedBook);

        Book result = bookService.createBook(createBookDto);

        verify(bookRepository).save(any(Book.class));
        assertEquals(savedBook, result);
    }
}
