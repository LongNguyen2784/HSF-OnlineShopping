package shopping.onlineshopping.mapper;

import org.mapstruct.Mapper;
import shopping.onlineshopping.dto.RegionDTO;
import shopping.onlineshopping.modal.Region;

@Mapper(componentModel = "spring")
public interface RegionMapper {
    RegionDTO toRegionDTO(Region region);
    Region toRegion(RegionDTO regionDTO);
}
