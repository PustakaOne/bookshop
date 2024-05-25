package id.ac.ui.cs.pustakaone.bookshop.service;

import id.ac.ui.cs.pustakaone.bookshop.dto.CreateUpdateBookDTO;
import id.ac.ui.cs.pustakaone.bookshop.model.Book;
import id.ac.ui.cs.pustakaone.bookshop.repository.BookRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import javax.swing.*;
import java.util.List;
import java.util.Optional;

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
    public Book updateBook(Long bookId, CreateUpdateBookDTO updateBookDto) {
        Optional<Book> optionalExistingBook = bookRepository.findById(bookId);
        if (optionalExistingBook.isEmpty()) {
            throw new EntityNotFoundException("Book with id " + bookId + " not found!");
        }
        Book existingBook = optionalExistingBook.get();

        if (updateBookDto.getTitle() != null) {
            existingBook.setTitle(updateBookDto.getTitle());
        }
        if (updateBookDto.getAuthor() != null) {
            existingBook.setAuthor(updateBookDto.getAuthor());
        }
        if (updateBookDto.getDescription() != null) {
            existingBook.setDescription(updateBookDto.getDescription());
        }
        if (updateBookDto.getPrice() != null) {
            existingBook.setPrice(updateBookDto.getPrice());
        }
        if (updateBookDto.getStock() != null) {
            existingBook.setStock(updateBookDto.getStock());
        }
        if (updateBookDto.getReleaseDate() != null) {
            existingBook.setReleaseDate(updateBookDto.getReleaseDate());
        }
        if (updateBookDto.getCoverUrl() != null) {
            existingBook.setCoverUrl(updateBookDto.getCoverUrl());
        }
        if (updateBookDto.getPublisher() != null) {
            existingBook.setPublisher(updateBookDto.getPublisher());
        }
        if (updateBookDto.getIsbn() != null) {
            existingBook.setIsbn(updateBookDto.getIsbn());
        }
        if (updateBookDto.getPages() != null) {
            existingBook.setPages(updateBookDto.getPages());
        }
        if (updateBookDto.getLang() != null) {
            existingBook.setLang(updateBookDto.getLang());
        }
        if (updateBookDto.getCategory() != null) {
            existingBook.setCategory(updateBookDto.getCategory());
        }

        return bookRepository.save(existingBook);
    }

    @Override
    public Book createBook(CreateUpdateBookDTO createBookDto) {
        Book newBook = Book.builder()
                .title(createBookDto.getTitle())
                .author(createBookDto.getAuthor())
                .description(createBookDto.getDescription())
                .price(createBookDto.getPrice())
                .stock(createBookDto.getStock())
                .releaseDate(createBookDto.getReleaseDate())
                .coverUrl(createBookDto.getCoverUrl())
                .publisher(createBookDto.getPublisher())
                .isbn(createBookDto.getIsbn())
                .pages(createBookDto.getPages())
                .lang(createBookDto.getLang())
                .category(createBookDto.getCategory())
                .build();
        return bookRepository.save(newBook);
    }

    @Override
    public void deleteBook(long id) {
        bookRepository.deleteById(id);
    }
}
