package shopping.onlineshopping.repository.userRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import shopping.onlineshopping.modal.user.Customer;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Customer findByEmail(String email);
    Customer findByContactName(String contactName);
    List<Customer> findByCountry(String country);
    List<Customer> findByRegion(String region);
    List<Customer> findByCity(String city);

}
