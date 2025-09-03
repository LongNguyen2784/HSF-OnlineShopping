package shopping.onlineshopping.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import shopping.onlineshopping.dto.TerritoryDTO;
import shopping.onlineshopping.modal.Territory;

@Mapper(componentModel = "spring")
public interface TerritoryMapper {
    @Mapping(source = "territoryId", target = "territoryId")
    @Mapping(source = "territoryName", target = "territoryName")
    @Mapping(source = "territoryDescription", target = "territoryDescription")
    @Mapping(source = "region.regionId", target = "region")
    TerritoryDTO toTerritoryDTO(Territory territory);
    @Mapping(source = "territoryId", target = "territoryId")
    @Mapping(source = "territoryName", target = "territoryName")
    @Mapping(source = "territoryDescription", target = "territoryDescription")
    @Mapping(source = "region", target = "region.regionId")
    Territory toTerritory(TerritoryDTO territoryDTO);
}
