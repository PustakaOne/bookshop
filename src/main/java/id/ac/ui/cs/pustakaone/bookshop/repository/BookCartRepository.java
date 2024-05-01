package id.ac.ui.cs.pustakaone.bookshop.repository;

import id.ac.ui.cs.pustakaone.bookshop.model.BookCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookCartRepository extends JpaRepository<BookCart, Long> {
}