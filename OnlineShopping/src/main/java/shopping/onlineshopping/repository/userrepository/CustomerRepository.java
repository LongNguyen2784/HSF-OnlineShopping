package shopping.onlineshopping.repository.userrepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import shopping.onlineshopping.modal.user.Customer;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Customer findByContactName(String contactName);
    List<Customer> findByCountry(String country);
    List<Customer> findByRegion(String region);
    List<Customer> findByCity(String city);

}
