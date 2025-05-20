package shopping.onlineshopping.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import shopping.onlineshopping.dto.OrderDetailDTO;
import shopping.onlineshopping.mapper.OrderDetailMapper;
import shopping.onlineshopping.modal.OrderDetailId;
import shopping.onlineshopping.repository.OrderDetailRepository;

import java.util.List;

@Service
public class OrderDetailService {
    @Autowired
    private OrderDetailRepository orderDetailRepository;
    @Autowired
    private OrderDetailMapper orderDetailMapper;

    public OrderDetailDTO getOrderDetailById(OrderDetailId id){
        return orderDetailMapper.toOrderDetailDTO(orderDetailRepository.findById(id).get());
    }
    public List<OrderDetailDTO> getOrderDetailByOrderId(int id){
        return orderDetailRepository.findByOrderId(id).stream().map(orderDetailMapper::toOrderDetailDTO).toList();
    }
    public List<OrderDetailDTO> getOrderDetailByProductId(int id){
        return orderDetailRepository.findByProductId(id).stream().map(orderDetailMapper::toOrderDetailDTO).toList();
    }
}
