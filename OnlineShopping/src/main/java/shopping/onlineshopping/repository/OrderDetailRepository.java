package shopping.onlineshopping.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import shopping.onlineshopping.modal.OrderDetail;
import shopping.onlineshopping.modal.OrderDetailId;

@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetail, OrderDetailId> {
}
