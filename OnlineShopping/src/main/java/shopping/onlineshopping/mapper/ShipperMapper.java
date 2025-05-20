package shopping.onlineshopping.mapper;

import org.mapstruct.Mapper;
import shopping.onlineshopping.dto.ShipperDTO;
import shopping.onlineshopping.modal.Shipper;

@Mapper(componentModel = "spring")
public interface ShipperMapper {
    ShipperDTO toShipperDTO(Shipper shipper);
    Shipper toShipper(ShipperDTO shipperDTO);
}
