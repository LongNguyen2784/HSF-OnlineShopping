package shopping.onlineshopping.mapper.usermapper;

import org.mapstruct.Mapper;
import shopping.onlineshopping.dto.userdto.ShipperDTO;
import shopping.onlineshopping.modal.user.Shipper;

@Mapper(componentModel = "spring")
public interface ShipperMapper {
    ShipperDTO toShipperDTO(Shipper shipper);
    Shipper toShipper(ShipperDTO shipperDTO);
}
