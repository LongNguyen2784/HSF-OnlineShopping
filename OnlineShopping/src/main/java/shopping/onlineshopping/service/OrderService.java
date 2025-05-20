package shopping.onlineshopping.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import shopping.onlineshopping.dto.OrderDTO;
import shopping.onlineshopping.mapper.OrderMapper;
import shopping.onlineshopping.repository.OrderRepository;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderMapper orderMapper;

    public OrderDTO getOrderById(Long id){
        return orderMapper.toOrderDTO(orderRepository.findById(id).get());
    }
    public OrderDTO createOrder(OrderDTO orderDTO){
        return orderMapper.toOrderDTO(orderRepository.save(orderMapper.toOrder(orderDTO)));
    }
    public void deleteOrderById(Long id){
        orderRepository.deleteById(id);
    }
    public OrderDTO updateOrder(OrderDTO orderDTO){
        return orderMapper.toOrderDTO(orderRepository.save(orderMapper.toOrder(orderDTO)));
    }
    public List<OrderDTO> getAllOrders(){
        return orderRepository.findAll().stream().map(orderMapper::toOrderDTO).toList();
    }
    public OrderDTO getOrderByCustomerId(Long customerId){
        return orderMapper.toOrderDTO(orderRepository.findByCustomerId(customerId));
    }
    public OrderDTO getOrderByEmployeeId(Long employeeId){
        return orderMapper.toOrderDTO(orderRepository.findByEmployeeId(employeeId));
    }
    public List<OrderDTO> getAllOrdersByOrderDate(LocalDate orderDate){
        return orderRepository.findByOrderDate(orderDate).stream().map(orderMapper::toOrderDTO).toList();
    }
    public List<OrderDTO> getAllOrdersByShipperId(Long id){
        return orderRepository.findByShipperId(id).stream().map(orderMapper::toOrderDTO).toList();
    }
}
