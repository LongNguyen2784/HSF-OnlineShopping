package shopping.onlineshopping.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import shopping.onlineshopping.modal.Order;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    Order findOrderByCustomer_CustomerId(Long customerCustomerId);
    Order findOrderByEmployee_EmployeeId(Long employeeEmployeeId);
    List<Order> findByOrderDate(LocalDateTime orderDate);
    List<Order> findOrderByShipper_ShipperId(int shipperShipperId);
}
