package shopping.onlineshopping.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import shopping.onlineshopping.dto.OrderDetailDTO;
import shopping.onlineshopping.mapper.OrderDetailMapper;
import shopping.onlineshopping.modal.Order;
import shopping.onlineshopping.modal.OrderDetail;
import shopping.onlineshopping.modal.OrderDetailId;
import shopping.onlineshopping.modal.product.Product;
import shopping.onlineshopping.repository.OrderDetailRepository;
import shopping.onlineshopping.repository.OrderRepository;
import shopping.onlineshopping.repository.ProductRepository;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderDetailService {

    private final OrderDetailRepository orderDetailRepository;
    private final OrderDetailMapper orderDetailMapper;
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;

    public OrderDetailDTO getOrderDetailById(OrderDetailId id){
        return orderDetailMapper.toOrderDetailDTO(orderDetailRepository.findById(id).get());
    }
    public List<OrderDetailDTO> getOrderDetailByOrderId(OrderDetailId id){
        return orderDetailRepository.findByOrderId(id).stream().map(orderDetailMapper::toOrderDetailDTO).toList();
    }

    public void deleteOrderDetail(OrderDetailId orderDetailId) {
        if (!orderDetailRepository.existsById(orderDetailId)) {
            throw new EntityNotFoundException("Không tìm thấy OrderDetail với ID: " + orderDetailId);
        }
        orderDetailRepository.deleteById(orderDetailId);
    }

    public OrderDetailDTO createOrderDetail(OrderDetailDTO dto) {
        // Tìm Order và Product
        Order order = orderRepository.findById((long) dto.getOrderId())
                .orElseThrow(() -> new EntityNotFoundException("Order không tồn tại"));

        Product product = productRepository.findById((long) dto.getProductId())
                .orElseThrow(() -> new EntityNotFoundException("Product không tồn tại"));

        // Tạo OrderDetailId
        OrderDetailId orderDetailId = new OrderDetailId(dto.getOrderId(), dto.getProductId());

        // Kiểm tra nếu đã tồn tại -> có thể xử lý khác nếu muốn cập nhật
        if (orderDetailRepository.existsById(orderDetailId)) {
            throw new IllegalArgumentException("OrderDetail đã tồn tại");
        }

        // Gán dữ liệu
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setOrderId(orderDetailId);
        orderDetail.setOrder(order);
        orderDetail.setProduct(product);
        orderDetail.setUnitPrice(dto.getUnitPrice());
        orderDetail.setQuantity(dto.getQuantity());
        orderDetail.setDiscount(BigDecimal.valueOf(dto.getDiscount()));

        // Lưu
        OrderDetail saved = orderDetailRepository.save(orderDetail);
        return orderDetailMapper.toOrderDetailDTO(saved);
    }
}
