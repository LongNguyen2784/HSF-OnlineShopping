package shopping.onlineshopping.service.userService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import shopping.onlineshopping.dto.userDTO.EmployeeDTO;
import shopping.onlineshopping.mapper.userMapper.EmployeeMapper;
import shopping.onlineshopping.repository.userRepository.EmployeeRepository;

import java.util.List;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private EmployeeMapper employeeMapper;

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
    public List<EmployeeDTO> getAllEmployeesByLastName(String lastName){
        return employeeRepository.findByLastName(lastName).stream().map(employeeMapper::toEmployeeDTO).toList();
    }
    public EmployeeDTO getEmployeeByHomePhone(String homePhone){
        return employeeMapper.toEmployeeDTO(employeeRepository.findByHomePhone(homePhone));
    }
}
