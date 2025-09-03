package shopping.onlineshopping.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import shopping.onlineshopping.modal.Cart;

import java.util.List;

@Repository
public interface CartItemRepository extends JpaRepository<Cart, Long> {
    List<Cart> findByUserId(Long Id);
    void deleteByUserId(Long Id);
}
