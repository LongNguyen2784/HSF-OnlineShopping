package shopping.onlineshopping.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import shopping.onlineshopping.modal.Order;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    Order findByCustomerId(Long customerId);
    Order findByEmployeeId(Long employeeId);
    List<Order> findByOrderDate(LocalDate orderDate);
    List<Order> findByShipperId(Long Id);
}
