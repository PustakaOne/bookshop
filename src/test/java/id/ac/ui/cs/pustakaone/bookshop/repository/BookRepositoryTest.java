package id.ac.ui.cs.pustakaone.bookshop.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;

import id.ac.ui.cs.pustakaone.bookshop.model.Book;
import java.util.List;

@DataJpaTest
public class BookRepositoryTest {
    @Autowired
    private BookRepository bookRepository;

    private Book book;

    @BeforeEach
    public void setUp() {
        book = Book.builder()
                .title("Test Book")
                .author("Test")
                .price(200000)
                .build();
    }

    @Test
    void testAddNewBook() {
        Book createdBook = bookRepository.save(book);
        assertNotNull(createdBook);
        assertEquals(book.getBookId(), createdBook.getBookId());
        assertEquals(book.getTitle(), createdBook.getTitle());
        assertEquals(book.getAuthor(), createdBook.getAuthor());
        assertEquals(book.getPrice(), createdBook.getPrice());
    }

    @Test
    void testGetBookDetail() {
        bookRepository.save(book);
        Book savedBook = bookRepository.getReferenceById(book.getBookId());
        assertNotNull(savedBook);
        assertEquals(book.getBookId(), savedBook.getBookId());
        assertEquals(book.getTitle(), savedBook.getTitle());
        assertEquals(book.getAuthor(), savedBook.getAuthor());
        assertEquals(book.getPrice(), savedBook.getPrice());
    }

    @Test
    void testGetAllBooks() {
        Book book2 = Book.builder()
                .title("Test Book 2")
                .author("Test")
                .price(200000)
                .build();

        bookRepository.save(book);
        bookRepository.save(book2);
        List<Book> allBooks = bookRepository.findAll();
        assertNotNull(allBooks);
        assertEquals(2, allBooks.size());
    }

    @Test
    void testDeleteBook() {
        bookRepository.save(book);
        List<Book> savedBooks = bookRepository.findAll();
        assertEquals(1, savedBooks.size());

        bookRepository.delete(book);
        savedBooks = bookRepository.findAll();
        assertEquals(0, savedBooks.size());
    }

    @Test
    void testUpdateBook() {
        bookRepository.save(book);
        Book updatedBook = Book.builder()
                .bookId(book.getBookId())
                .title("Book Title 2")
                .author("Test")
                .price(125000)
                .build();
        bookRepository.save(updatedBook);
        Book savedBook = bookRepository.getReferenceById(book.getBookId());
        assertNotNull(savedBook);
        assertEquals(updatedBook.getBookId(), savedBook.getBookId());
        assertEquals(updatedBook.getTitle(), savedBook.getTitle());
        assertEquals(updatedBook.getAuthor(), savedBook.getAuthor());
        assertEquals(updatedBook.getPrice(), savedBook.getPrice());
    }
}