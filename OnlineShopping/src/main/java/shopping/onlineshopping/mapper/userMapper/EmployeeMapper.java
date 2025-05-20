package shopping.onlineshopping.mapper.userMapper;

import org.mapstruct.Mapper;
import shopping.onlineshopping.dto.userDTO.EmployeeDTO;
import shopping.onlineshopping.modal.user.Employee;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {
    EmployeeDTO toEmployeeDTO(Employee employee);
    Employee toEmployee(EmployeeDTO employeeDTO);
}
