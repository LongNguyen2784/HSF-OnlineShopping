package shopping.onlineshopping.service.userservice;

import org.springframework.stereotype.Service;
import shopping.onlineshopping.dto.userdto.AssignRoleDTO;
import shopping.onlineshopping.dto.userdto.EmployeeDTO;
import shopping.onlineshopping.exception.ResourceNotFoundException;
import shopping.onlineshopping.mapper.usermapper.EmployeeMapper;
import shopping.onlineshopping.modal.user.Account;
import shopping.onlineshopping.modal.user.Employee;
import shopping.onlineshopping.repository.userrepository.EmployeeRepository;

import java.nio.file.AccessDeniedException;
import java.util.List;

@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final EmployeeMapper employeeMapper;

    public EmployeeService(EmployeeRepository employeeRepository, EmployeeMapper employeeMapper) {
        this.employeeRepository = employeeRepository;
        this.employeeMapper = employeeMapper;
    }

    public EmployeeDTO getEmployeeById(Long id){
        return employeeMapper.toEmployeeDTO(employeeRepository.findById(id).get());
    }
    public EmployeeDTO createEmployee(EmployeeDTO employeeDTO){
        return employeeMapper.toEmployeeDTO(employeeRepository.save(employeeMapper.toEmployee(employeeDTO)));
    }
    public void deleteEmployeeById(Long id){
        employeeRepository.deleteById(id);
    }
    public EmployeeDTO updateEmployee(EmployeeDTO employeeDTO){
        return employeeMapper.toEmployeeDTO(employeeRepository.save(employeeMapper.toEmployee(employeeDTO)));
    }
    public List<EmployeeDTO> getAllEmployees(){
        return employeeRepository.findAll().stream().map(employeeMapper::toEmployeeDTO).toList();
    }
    public EmployeeDTO getEmployeeByHomePhone(String homePhone){
        return employeeMapper.toEmployeeDTO(employeeRepository.findByHomePhone(homePhone));
    }

}
