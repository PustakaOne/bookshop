package id.ac.ui.cs.pustakaone.bookshop.controller;

import id.ac.ui.cs.pustakaone.bookshop.model.Book;
import id.ac.ui.cs.pustakaone.bookshop.service.BookService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
public class BookControllerTest {
    @Mock
    private BookService bookService;

    @InjectMocks
    private BookController bookController;

    private MockMvc mockMvc;
    private Book book;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(bookController).build();
        book = Book.builder()
                .bookId(1L)
                .title("Tes")
                .author("tes")
                .publisher("Gramed")
                .stock(20)
                .price(200000)
                .build();
    }

    @Test
    void testGetAllBooks() throws Exception {
        List<Book> bookList = Collections.singletonList(book);
        when(bookService.getAllBooks()).thenReturn(bookList);

        mockMvc.perform(get("/books"))
                .andExpect(status().isOk())
                .andExpect(content().json("[{\"bookId\":1,\"title\":\"Tes\",\"author\":\"tes\",\"publisher\":\"Gramed\",\"stock\":20,\"price\":200000}]"));

        // Test exception scenario
        doThrow(new RuntimeException()).when(bookService).getAllBooks();
        mockMvc.perform(get("/books"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testGetBookDetail() throws Exception {
        when(bookService.getBookDetail(book.getBookId())).thenReturn(book);

        mockMvc.perform(get("/book/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Tes"))
                .andExpect(jsonPath("$.author").value("tes"));

        // Test exception scenario
        doThrow(new RuntimeException()).when(bookService).getBookDetail(book.getBookId());
        mockMvc.perform(get("/book/1"))
                .andExpect(status().isBadRequest());
    }
}
