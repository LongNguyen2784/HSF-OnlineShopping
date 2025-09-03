package shopping.onlineshopping.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import shopping.onlineshopping.modal.Payment;
import java.util.Optional;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
    Optional<Payment> findById(Long orderId);

}