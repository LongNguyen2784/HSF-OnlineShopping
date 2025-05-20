package shopping.onlineshopping.mapper.userMapper;

import org.mapstruct.Mapper;
import shopping.onlineshopping.dto.userDTO.ShipperDTO;
import shopping.onlineshopping.modal.user.Shipper;

@Mapper(componentModel = "spring")
public interface ShipperMapper {
    ShipperDTO toShipperDTO(Shipper shipper);
    Shipper toShipper(ShipperDTO shipperDTO);
}
