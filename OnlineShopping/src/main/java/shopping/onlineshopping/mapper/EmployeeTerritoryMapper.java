package shopping.onlineshopping.mapper;

import org.mapstruct.Mapper;
import shopping.onlineshopping.dto.EmployeeTerritoryDTO;
import shopping.onlineshopping.modal.EmployeeTerritory;

@Mapper(componentModel = "spring")
public interface EmployeeTerritoryMapper {
    EmployeeTerritoryDTO toEmployeeTerritoryDTO(EmployeeTerritory employeeTerritory);
    EmployeeTerritory toEmployeeTerritory(EmployeeTerritoryDTO employeeTerritoryDTO);
}
