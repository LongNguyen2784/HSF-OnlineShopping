package shopping.onlineshopping.mapper.usermapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import shopping.onlineshopping.dto.userdto.EmployeeTerritoryDTO;
import shopping.onlineshopping.modal.user.EmployeeTerritory;

@Mapper(componentModel = "spring")
public interface EmployeeTerritoryMapper {
    @Mapping(source = "employee.employeeId", target = "employeeId")
    @Mapping(source = "territory.territoryId", target = "territoryId")
    EmployeeTerritoryDTO toEmployeeTerritoryDTO(EmployeeTerritory employeeTerritory);
    @Mapping(source = "employeeId", target = "employee.employeeId")
    @Mapping(source = "territoryId", target = "territory.territoryId")
    EmployeeTerritory toEmployeeTerritory(EmployeeTerritoryDTO employeeTerritoryDTO);
}
