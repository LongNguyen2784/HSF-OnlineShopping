package shopping.onlineshopping.mapper.usermapper;

import org.mapstruct.Mapper;
import shopping.onlineshopping.dto.userdto.AssignRoleDTO;
import shopping.onlineshopping.dto.userdto.EmployeeDTO;
import shopping.onlineshopping.modal.user.Employee;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {
    EmployeeDTO toEmployeeDTO(Employee employee);
    Employee toEmployee(EmployeeDTO employeeDTO);
    AssignRoleDTO toAssignRoleDTO(Employee employee);
}
