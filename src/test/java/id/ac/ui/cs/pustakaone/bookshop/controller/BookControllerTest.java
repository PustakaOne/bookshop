package id.ac.ui.cs.pustakaone.bookshop.controller;

import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
public class BookControllerTest {
    @InjectMocks
    private BookController bookController;
    private MockMvc mockMvc;

    @Test
    void testGetBookList() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(bookController).build();

        mockMvc.perform(get("/books/"))
                .andExpect(status().isOk()).andExpect(content().string("Book List"));
    }

    @Test
    void testGetBookDetail() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(bookController).build();

        mockMvc.perform(get("/book/1"))
                .andExpect(status().isOk()).andExpect(content().string("Book 1"));
    }
}
