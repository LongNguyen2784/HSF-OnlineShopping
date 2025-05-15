package shopping.onlineshopping.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import shopping.onlineshopping.modal.Region;

@Repository
public interface RegionRepository extends JpaRepository<Region, Long> {
}
