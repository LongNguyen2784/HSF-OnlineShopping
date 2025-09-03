package shopping.onlineshopping.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import shopping.onlineshopping.dto.OrderDetailDTO;
import shopping.onlineshopping.modal.OrderDetailId;
import shopping.onlineshopping.service.OrderDetailService;

import java.util.List;

@RestController
@RequestMapping("/api/order-details")
@RequiredArgsConstructor
public class OrderDetailController {

    private final OrderDetailService orderDetailService;

    // Lấy một chi tiết đơn hàng theo composite key (orderId + productId)
    @GetMapping("/detail")
    public ResponseEntity<OrderDetailDTO> getOrderDetailById(
            @RequestParam int orderId,
            @RequestParam int productId
    ) {
        OrderDetailId id = new OrderDetailId(orderId, productId);
        return ResponseEntity.ok(orderDetailService.getOrderDetailById(id));
    }

    // Lấy tất cả chi tiết đơn hàng theo orderId
    @GetMapping("/order/{orderId}")
    public ResponseEntity<List<OrderDetailDTO>> getAllDetailsByOrderId(
            @PathVariable OrderDetailId orderId
    ) {
        return ResponseEntity.ok(orderDetailService.getOrderDetailByOrderId(orderId));
    }

    // (Optional) Xoá chi tiết đơn hàng
    @DeleteMapping("/detail")
    public ResponseEntity<Void> deleteOrderDetail(
            @RequestParam int orderId,
            @RequestParam int productId
    ) {
        orderDetailService.deleteOrderDetail(new OrderDetailId(orderId, productId));
        return ResponseEntity.noContent().build();
    }

    // (Optional) Thêm chi tiết đơn hàng
    @PostMapping("/detail")
    public ResponseEntity<OrderDetailDTO> createOrderDetail(@RequestBody OrderDetailDTO dto) {
        return ResponseEntity.ok(orderDetailService.createOrderDetail(dto));
    }
}