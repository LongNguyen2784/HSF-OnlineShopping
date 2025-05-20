package shopping.onlineshopping.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import shopping.onlineshopping.modal.OrderDetail;
import shopping.onlineshopping.modal.OrderDetailId;

import java.util.List;

@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetail, OrderDetailId> {
    List<OrderDetail> findByOrderId(int orderId);
    List<OrderDetail> findByProductId(int productId);

}
