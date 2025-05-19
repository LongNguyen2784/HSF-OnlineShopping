package shopping.onlineshopping.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import shopping.onlineshopping.modal.Territory;

@Repository
public interface TerritoryRepository extends JpaRepository<Territory, Long> {
}
