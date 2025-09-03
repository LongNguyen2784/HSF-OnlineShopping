package shopping.onlineshopping.repository.userrepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import shopping.onlineshopping.modal.user.Shipper;

@Repository
public interface ShipperRepository extends JpaRepository<Shipper, Long> {
    Shipper findByShipperName(String shipperName);
    Shipper findByPhone(String phone);


}
