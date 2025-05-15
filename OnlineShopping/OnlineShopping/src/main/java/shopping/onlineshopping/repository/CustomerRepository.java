package shopping.onlineshopping.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import shopping.onlineshopping.modal.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
