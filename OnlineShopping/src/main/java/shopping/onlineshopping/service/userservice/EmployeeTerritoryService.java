package shopping.onlineshopping.service.userservice;

import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import shopping.onlineshopping.dto.userdto.EmployeeTerritoryDTO;
import shopping.onlineshopping.mapper.usermapper.EmployeeTerritoryMapper;
import shopping.onlineshopping.modal.Territory;
import shopping.onlineshopping.modal.user.Employee;
import shopping.onlineshopping.modal.user.EmployeeTerritory;
import shopping.onlineshopping.repository.TerritoryRepository;
import shopping.onlineshopping.repository.userrepository.EmployeeRepository;
import shopping.onlineshopping.repository.userrepository.EmployeeTerritoryRepository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@Service
@RequiredArgsConstructor
public class EmployeeTerritoryService {
    private final EmployeeTerritoryRepository employeeTerritoryRepository;
    private final EmployeeRepository employeeRepository;
    private final TerritoryRepository territoryRepository;
    private final EmployeeTerritoryMapper employeeTerritoryMapper;

    public EmployeeTerritoryDTO assignTerritoryToEmployee(EmployeeTerritoryDTO dto) {
        Employee employee = employeeRepository.findById(dto.getEmployeeId())
                .orElseThrow(() -> new RuntimeException("Employee không tồn tại"));

        Territory territory = territoryRepository.findById(dto.getTerritoryId())
                .orElseThrow(() -> new RuntimeException("Territory không tồn tại"));

        EmployeeTerritory et = new EmployeeTerritory();
        et.setEmployee(employee);
        et.setTerritory(territory);

        return employeeTerritoryMapper.toEmployeeTerritoryDTO(employeeTerritoryRepository.save(et));
    }

    public @NotNull List<EmployeeTerritoryDTO> getTerritoriesByEmployee(Long employeeId) {
        return Optional.ofNullable(employeeTerritoryRepository.findByEmployeeEmployeeId(employeeId))
                .orElse(Collections.emptyList())
                .stream()
                .map(employeeTerritoryMapper::toEmployeeTerritoryDTO)
                .collect(Collectors.toList());
    }

    public boolean isEmployeeInTerritory(Long employeeId, Long territoryId) {
        return employeeTerritoryRepository.existsByEmployeeEmployeeIdAndTerritoryTerritoryId(employeeId,territoryId);
    }
}