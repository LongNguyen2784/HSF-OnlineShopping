package shopping.onlineshopping.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import shopping.onlineshopping.dto.OrderDTO;
import shopping.onlineshopping.mapper.OrderMapper;
import shopping.onlineshopping.modal.Cart;
import shopping.onlineshopping.modal.CartItem;
import shopping.onlineshopping.modal.Order;
import shopping.onlineshopping.modal.OrderDetail;
import shopping.onlineshopping.modal.user.Customer;
import shopping.onlineshopping.repository.CartItemRepository;
import shopping.onlineshopping.repository.CartRepository;
import shopping.onlineshopping.repository.OrderRepository;
import shopping.onlineshopping.repository.userrepository.CustomerRepository;
import shopping.onlineshopping.repository.userrepository.EmployeeRepository;
import shopping.onlineshopping.repository.userrepository.ShipperRepository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final CustomerRepository customerRepository;
    private final EmployeeRepository employeeRepository;
    private final ShipperRepository shipperRepository;
    private final CartItemRepository cartItemRepository;
    private final CartRepository cartRepository;

    public OrderDTO getOrderById(Long id) {
        return orderMapper.toOrderDTO(orderRepository.findById(id).get());
    }

    public Order createOrderFromCustomer(OrderDTO dto) {
        Customer customer = customerRepository.findById(dto.getCustomerId())
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        Order order = new Order();
        order.setCustomer(customer);
//        order.setEmployee(); // nếu cần
//        order.setShipper(...);  // nếu cần

        // Set ship info từ customer
        order.setShipName(customer.getContactName());
        order.setShipAddress(customer.getAddress());
        order.setShipCity(customer.getCity());
        order.setShipRegion(customer.getRegion());
        order.setShipPostalCode(customer.getPostalCode());
        order.setShipCountry(customer.getCountry());

        // Set các thông tin khác
        order.setCustomer(customer);
        order.setOrderDate(LocalDateTime.now());
        order.setRequiredDate(LocalDateTime.now().plusDays(5)); // giả định
        order.setShipName(customer.getContactName());
        order.setShipAddress(customer.getAddress());
        order.setShipCity(customer.getCity());
        order.setShipRegion(customer.getRegion());
        order.setShipPostalCode(customer.getPostalCode());
        order.setShipCountry(customer.getCountry());
        // v.v...
        return orderRepository.save(order);
    }

    public Order placeOrder(Long userId) {
        // Lấy Customer
        Customer customer = customerRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        // Lấy danh sách giỏ hàng của user
        List<Cart> cartItems = cartRepository.findByUserId(userId);
        if (cartItems.isEmpty()) {
            throw new RuntimeException("Giỏ hàng trống");
        }

        // Tạo Order
        Order order = new Order();
        order.setCustomer(customer);
        order.setOrderDate(LocalDateTime.now());
        order.setRequiredDate(LocalDateTime.now().plusDays(5));
        order.setShipName(customer.getContactName());
        order.setShipAddress(customer.getAddress());
        order.setShipCity(customer.getCity());
        order.setShipRegion(customer.getRegion());
        order.setShipPostalCode(customer.getPostalCode());
        order.setShipCountry(customer.getCountry());

        order = orderRepository.save(order); // lưu để có orderId

        // Tạo OrderDetail từ Cart
        for (Cart cart : cartItems) {
            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setOrder(order);
            orderDetail.setProduct(cart.getProduct());
            orderDetail.setQuantity(cart.getQuantity());
            orderDetail.setUnitPrice(cart.getProduct().getUnitPrice());
            orderDetail.setDiscount(BigDecimal.ZERO);

            // Nếu dùng cascade thì add vào order
            order.getOrderDetails().add(orderDetail);
        }

        // Lưu lại Order có OrderDetails
        orderRepository.save(order); // nếu cascade ALL thì tự lưu cả OrderDetail

        // Xoá cart sau khi đặt hàng
        cartRepository.deleteAll(cartItems);

        return order;
    }


    public void deleteOrderById(Long id) {
        orderRepository.deleteById(id);
    }

    public OrderDTO updateOrder(OrderDTO orderDTO) {
        return orderMapper.toOrderDTO(orderRepository.save(orderMapper.toOrder(orderDTO)));
    }

    public List<OrderDTO> getAllOrders() {
        return orderRepository.findAll().stream().map(orderMapper::toOrderDTO).toList();
    }

    public OrderDTO getOrderByCustomerId(Long customerId) {
        return orderMapper.toOrderDTO(orderRepository.findOrderByCustomer_CustomerId(customerId));
    }

    public OrderDTO getOrderByEmployeeId(Long employeeId) {
        return orderMapper.toOrderDTO(orderRepository.findOrderByEmployee_EmployeeId(employeeId));
    }

    public List<OrderDTO> getAllOrdersByOrderDate(LocalDateTime orderDate) {
        return orderRepository.findByOrderDate(orderDate).stream().map(orderMapper::toOrderDTO).toList();
    }

    public List<OrderDTO> getAllOrdersByShipperId(int id) {
        return orderRepository.findOrderByShipper_ShipperId(id).stream().map(orderMapper::toOrderDTO).toList();
    }

}
