package shopping.onlineshopping.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import shopping.onlineshopping.modal.Shipper;

@Repository
public interface ShipperRepository extends JpaRepository<Shipper, Long> {
}
