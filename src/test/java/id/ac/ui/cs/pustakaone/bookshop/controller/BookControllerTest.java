package id.ac.ui.cs.pustakaone.bookshop.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import id.ac.ui.cs.pustakaone.bookshop.dto.CreateUpdateBookDTO;
import id.ac.ui.cs.pustakaone.bookshop.model.Book;
import id.ac.ui.cs.pustakaone.bookshop.service.BookService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
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

    CreateUpdateBookDTO createBookDto;

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
        createBookDto = new CreateUpdateBookDTO();
    }

    @Test
    void testGetBookList() throws Exception {
        List<Book> bookList = Collections.singletonList(book);
        when(bookService.getAllBooks()).thenReturn(bookList);

        mockMvc.perform(get("/books"))
                .andExpect(status().isOk())
                .andExpect(content().json("[{\"title\":\"Tes\",\"author\":\"tes\",\"publisher\":\"Gramed\",\"stock\":20,\"price\":200000}]"));
    }

    @Test
    void testGetBookDetail() throws Exception {
        bookService.addNewBook(book);
        Book savedBook = bookService.getBookDetail(book.getBookId());
        when(bookService.getBookDetail(book.getBookId())).thenReturn(savedBook);

        mockMvc.perform(get("/book/1"))
                .andExpect(status().isOk());
    }

    @Test
    public void testCreateBookSuccess() throws Exception {

        when(bookService.createBook(any(CreateUpdateBookDTO.class))).thenReturn(book);
        when(bookService.getBookDetail(book.getBookId())).thenReturn(book);

        mockMvc.perform(post("/book")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(createBookDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.bookId").value(book.getBookId()))
                .andExpect(jsonPath("$.title").value(book.getTitle()));
    }

    @Test
    public void testCreateBookFailure() throws Exception {
        when(bookService.createBook(any(CreateUpdateBookDTO.class))).thenThrow(new RuntimeException("Error creating book"));

        mockMvc.perform(post("/book")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(createBookDto)))
                .andExpect(status().isBadRequest());
    }
}
