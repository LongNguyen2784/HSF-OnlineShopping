package shopping.onlineshopping.repository.userRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import shopping.onlineshopping.modal.user.Shipper;

@Repository
public interface ShipperRepository extends JpaRepository<Shipper, Long> {
    Shipper findByShipperShipperName(String shipperName);
    Shipper findByShipperPhone(String phone);

}
