package shopping.onlineshopping.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import shopping.onlineshopping.modal.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
}
