package shopping.onlineshopping.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import shopping.onlineshopping.modal.Territory;

import java.util.List;

@Repository
public interface TerritoryRepository extends JpaRepository<Territory, Long> {
    Territory findByTerritoryDescription(String description);
    List<Territory> findAllByRegionRegionId(Long regionId);
}
