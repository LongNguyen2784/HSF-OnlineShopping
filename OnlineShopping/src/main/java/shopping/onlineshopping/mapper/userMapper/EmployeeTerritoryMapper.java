package shopping.onlineshopping.mapper.userMapper;

import org.mapstruct.Mapper;
import shopping.onlineshopping.dto.userDTO.EmployeeTerritoryDTO;
import shopping.onlineshopping.modal.user.EmployeeTerritory;

@Mapper(componentModel = "spring")
public interface EmployeeTerritoryMapper {
    EmployeeTerritoryDTO toEmployeeTerritoryDTO(EmployeeTerritory employeeTerritory);
    EmployeeTerritory toEmployeeTerritory(EmployeeTerritoryDTO employeeTerritoryDTO);
}
