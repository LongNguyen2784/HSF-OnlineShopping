package shopping.onlineshopping.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import shopping.onlineshopping.dto.OrderDTO;
import shopping.onlineshopping.modal.Order;
import shopping.onlineshopping.service.OrderService;

import java.util.List;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @GetMapping("/{id}")
    public ResponseEntity<OrderDTO> getOrderById(@PathVariable Long id) {
        return ResponseEntity.ok(orderService.getOrderById(id));
    }

    @GetMapping
    public ResponseEntity<List<OrderDTO>> getAllOrders() {
        return ResponseEntity.ok(orderService.getAllOrders());
    }

    @PostMapping
    public ResponseEntity<Order> createOrder(@RequestBody OrderDTO dto) {
        return ResponseEntity.ok(orderService.createOrderFromCustomer(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrderDTO> updateOrder(@PathVariable Long id, @RequestBody OrderDTO dto) {
        dto.setOrderId(id); // Ensure correct ID is set
        return ResponseEntity.ok(orderService.updateOrder(dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long id) {
        orderService.deleteOrderById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/customer/{customerId}")
    public ResponseEntity<OrderDTO> getOrderByCustomerId(@PathVariable Long customerId) {
        return ResponseEntity.ok(orderService.getOrderByCustomerId(customerId));
    }

    @GetMapping("/employee/{employeeId}")
    public ResponseEntity<OrderDTO> getOrderByEmployeeId(@PathVariable Long employeeId) {
        return ResponseEntity.ok(orderService.getOrderByEmployeeId(employeeId));
    }

    @GetMapping("/shipper/{shipperId}")
    public ResponseEntity<List<OrderDTO>> getOrdersByShipperId(@PathVariable int shipperId) {
        return ResponseEntity.ok(orderService.getAllOrdersByShipperId(shipperId));
    }

    @GetMapping("/date")
    public ResponseEntity<List<OrderDTO>> getOrdersByDate(@RequestParam String date) {
        LocalDateTime orderDate = LocalDateTime.parse(date);
        return ResponseEntity.ok(orderService.getAllOrdersByOrderDate(orderDate));
    }

    @PostMapping("/place/{userId}")
    public ResponseEntity<Order> placeOrder(@PathVariable Long userId) {
        return ResponseEntity.ok(orderService.placeOrder(userId));
    }
}



