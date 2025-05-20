package shopping.onlineshopping.service.userService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import shopping.onlineshopping.dto.userDTO.EmployeeTerritoryDTO;
import shopping.onlineshopping.mapper.userMapper.EmployeeTerritoryMapper;
import shopping.onlineshopping.modal.user.EmployeeTerritoryId;
import shopping.onlineshopping.repository.userRepository.EmployeeTerritoryRepository;

import java.util.List;

@Service
public class EmployeeTerritoryService {
    @Autowired
    private EmployeeTerritoryRepository employeeTerritoryRepository;
    @Autowired
    private EmployeeTerritoryMapper employeeTerritoryMapper;

    public void deleteEmployeeTerritoryById(EmployeeTerritoryId id){
        employeeTerritoryRepository.deleteById(id);
    }
    public List<EmployeeTerritoryDTO> getEmployeeTerritoryByEmployeeId(Long id){
        return employeeTerritoryRepository.findByIdEmployeeId(id).stream()
                .map(employeeTerritoryMapper::toEmployeeTerritoryDTO)
                .toList();
    }
    public List<EmployeeTerritoryDTO> getEmployeeTerritoryByTerritoryId(Long id){
        return  employeeTerritoryRepository.findByIdTerritoryId(id).stream().map(employeeTerritoryMapper::toEmployeeTerritoryDTO).toList();
    }

}
