package id.ac.ui.cs.pustakaone.bookshop.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import java.util.Date;

@ExtendWith(MockitoExtension.class)
public class BookConcreteBuilderTest {
    @InjectMocks
    private BookConcreteBuilder builder;

    @BeforeEach
    public void setUp() {
        builder = new BookConcreteBuilder();
    }

    @Test
    public void testReset() {
        // Build a book
        builder.buildBookId("12345");
        builder.buildTitle("Sample Book");
        builder.buildAuthor("John Doe");

        // Reset the builder
        builder.reset();

        // Get the book after reset
        Book book = builder.getBook();

        // Assert that all attributes are reset to their default values
        assertNull(book.getId());
        assertNull(book.getTitle());
        assertNull(book.getAuthor());
        assertNull(book.getPublisher());
        assertNull(book.getDescription());
        assertEquals(0, book.getPrice());
        assertEquals(0, book.getStock());
        assertNull(book.getReleaseDate());
        assertNull(book.getIsbn());
        assertNull(book.getCoverUrl());
        assertNull(book.getCategory());
        assertEquals(0, book.getPages());
        assertNull(book.getLang());
    }

    @Test
    public void testBuildBookId() {
        builder.buildBookId("12345");
        Book book = builder.getBook();
        assertEquals("12345", book.getId());
    }

    @Test
    public void testBuildTitle() {
        builder.buildTitle("Sample Book");
        Book book = builder.getBook();
        assertEquals("Sample Book", book.getTitle());
    }

    @Test
    public void testBuildAuthor() {
        builder.buildAuthor("John Doe");
        Book book = builder.getBook();
        assertEquals("John Doe", book.getAuthor());
    }

    @Test
    public void testBuildPublisher() {
        builder.buildPublisher("Sample Publisher");
        Book book = builder.getBook();
        assertEquals("Sample Publisher", book.getPublisher());
    }

    @Test
    public void testBuildDescription() {
        builder.buildDescription("This is a sample book description.");
        Book book = builder.getBook();
        assertEquals("This is a sample book description.", book.getDescription());
    }

    @Test
    public void testBuildPrice() {
        builder.buildPrice(25);
        Book book = builder.getBook();
        assertEquals(25, book.getPrice());
    }

    @Test
    public void testBuildStock() {
        builder.buildStock(100);
        Book book = builder.getBook();
        assertEquals(100, book.getStock());
    }

    @Test
    public void testBuildReleaseDate() {
        Date releaseDate = new Date();
        builder.buildReleaseDate(releaseDate);
        Book book = builder.getBook();
        assertEquals(releaseDate, book.getReleaseDate());
    }

    @Test
    public void testBuildIsbn() {
        builder.buildIsbn("978-1234567890");
        Book book = builder.getBook();
        assertEquals("978-1234567890", book.getIsbn());
    }

    @Test
    public void testBuildCoverUrl() {
        builder.buildCoverUrl("https://example.com/book-cover.jpg");
        Book book = builder.getBook();
        assertEquals("https://example.com/book-cover.jpg", book.getCoverUrl());
    }

    @Test
    public void testBuildCategory() {
        builder.buildCategory("Fiction");
        Book book = builder.getBook();
        assertEquals("Fiction", book.getCategory());
    }

    @Test
    public void testBuildPages() {
        builder.buildPages(200);
        Book book = builder.getBook();
        assertEquals(200, book.getPages());
    }

    @Test
    public void testBuildLang() {
        builder.buildLang("English");
        Book book = builder.getBook();
        assertEquals("English", book.getLang());
    }
}
