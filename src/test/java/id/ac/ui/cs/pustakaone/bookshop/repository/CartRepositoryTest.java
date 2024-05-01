//package id.ac.ui.cs.pustakaone.bookshop.repository;
//
//import id.ac.ui.cs.pustakaone.bookshop.model.Cart;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//@DataJpaTest
//public class CartRepositoryTests {
//
//    @Autowired
//    private TestEntityManager entityManager;
//
//    @Autowired
//    private CartRepository cartRepository;
//
//    @Test
//    public void testFindById() {
//        // given
//        Cart cart = new Cart("cart1");
//        entityManager.persist(cart);
//        entityManager.flush();
//
//        // when
//        Cart found = cartRepository.findById(cart.getId()).orElse(null);
//
//        // then
//        assertThat(found).isNotNull();
//        assertThat(found.getId()).isEqualTo(cart.getId());
//    }
//
//    @Test
//    public void testSaveCart() {
//        // given
//        Cart cart = new Cart("cart2");
//
//        // when
//        Cart savedCart = cartRepository.save(cart);
//
//        // then
//        assertThat(entityManager.find(Cart.class, savedCart.getId())).isEqualTo(savedCart);
//    }
//
//    @Test
//    public void testDeleteCart() {
//        // given
//        Cart cart = new Cart("cart3");
//        entityManager.persist(cart);
//        entityManager.flush();
//
//        // when
//        cartRepository.delete(cart);
//        Cart deletedCart = entityManager.find(Cart.class, cart.getId());
//
//        // then
//        assertThat(deletedCart).isNull();
//    }
//}
