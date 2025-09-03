package shopping.onlineshopping.repository.userrepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import shopping.onlineshopping.modal.user.Employee;
import shopping.onlineshopping.modal.user.EmployeeTerritory;
import shopping.onlineshopping.modal.user.EmployeeTerritoryId;

import java.util.List;

@Repository
public interface EmployeeTerritoryRepository extends JpaRepository<EmployeeTerritory, EmployeeTerritoryId> {
    List<EmployeeTerritory> findByEmployeeEmployeeId(Long employeeId);
    List<EmployeeTerritory> findByEmployee(Employee employee);
    boolean existsByEmployeeEmployeeIdAndTerritoryTerritoryId(Long employeeId, Long territoryId);

}
