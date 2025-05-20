package shopping.onlineshopping.mapper;

import org.mapstruct.Mapper;
import shopping.onlineshopping.dto.TerritoryDTO;
import shopping.onlineshopping.modal.Territory;

@Mapper(componentModel = "spring")
public interface TerritoryMapper {
    TerritoryDTO toTerritoryDTO(Territory territory);
    Territory toTerritory(TerritoryDTO territoryDTO);
}
