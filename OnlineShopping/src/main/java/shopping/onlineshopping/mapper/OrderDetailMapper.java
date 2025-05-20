package shopping.onlineshopping.mapper;

import org.mapstruct.Mapper;
import shopping.onlineshopping.dto.OrderDetailDTO;
import shopping.onlineshopping.modal.OrderDetail;

@Mapper(componentModel = "spring")
public interface OrderDetailMapper {
    OrderDetailDTO toOrderDetailDTO(OrderDetail orderDetail);
    OrderDetail toOrderDetail(OrderDetailDTO orderDetailDTO);
}
