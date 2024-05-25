package id.ac.ui.cs.pustakaone.bookshop.repository;

import id.ac.ui.cs.pustakaone.bookshop.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findByBookId(Long bookId);
}
