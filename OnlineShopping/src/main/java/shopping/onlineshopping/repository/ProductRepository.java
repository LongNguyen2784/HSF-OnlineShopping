package shopping.onlineshopping.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import shopping.onlineshopping.modal.product.Product;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ProductRepository  extends JpaRepository<Product, Long> {
    @Query("SELECT p FROM Product p WHERE p.productId = :productId OR LOWER(p.productName) LIKE LOWER(CONCAT('%', :productName, '%'))")
    List<Product> findByIdOrProductNameContainingIgnoreCase(
            @Param("productId") Long productId,
            @Param("productName") String productName
    );
    List<Product> findByProductNameContainingIgnoreCase(String productName);
    Product findByProductName(String productName);
    List<Product> findByUnitPriceBetween(BigDecimal minPrice, BigDecimal maxPrice);
    List<Product> findByCategoryCategoryId(int categoryId);

}
