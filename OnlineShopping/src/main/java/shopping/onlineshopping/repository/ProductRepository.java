package shopping.onlineshopping.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import shopping.onlineshopping.modal.product.Product;

import java.util.List;

@Repository
public interface ProductRepository  extends JpaRepository<Product, Long> {
    Product findByName(String name);
    List<Product> findByPriceBetween(Double minPrice, Double maxPrice);
    List<Product> findByCategory(String category);
}
