package id.ac.ui.cs.pustakaone.bookshop.repository;

import id.ac.ui.cs.pustakaone.bookshop.model.Book;
import id.ac.ui.cs.pustakaone.bookshop.model.BookCart;
import id.ac.ui.cs.pustakaone.bookshop.model.Cart;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class BookCartRepositoryTests {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private BookCartRepository bookCartRepository;

    @Test
    public void testFindByBookIdAndCartId() {
        // given
        Cart cart = new Cart("cart1");
        Book book = new Book("book1");
        BookCart bookCart = new BookCart("bookCart1", book, cart, 1);
        entityManager.persist(cart);
        entityManager.persist(book);
        entityManager.persist(bookCart);
        entityManager.flush();

        // when
        Optional<BookCart> found = bookCartRepository.findByBookIdAndCartId(book.getId(), cart.getId());

        // then
        assertThat(found).isPresent();
        assertThat(found.get().getId()).isEqualTo(bookCart.getId());
    }

    @Test
    public void testSaveBookCart() {
        // given
        Cart cart = new Cart("cart2");
        Book book = new Book("book2");
        BookCart bookCart = new BookCart("bookCart2", book, cart, 1);
        entityManager.persist(cart);
        entityManager.persist(book);

        // when
        BookCart savedBookCart = bookCartRepository.save(bookCart);

        // then
        assertThat(entityManager.find(BookCart.class, savedBookCart.getId())).isEqualTo(savedBookCart);
    }
}
