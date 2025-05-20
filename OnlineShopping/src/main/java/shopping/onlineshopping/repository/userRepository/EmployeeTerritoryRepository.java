package shopping.onlineshopping.repository.userRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import shopping.onlineshopping.modal.user.EmployeeTerritory;
import shopping.onlineshopping.modal.user.EmployeeTerritoryId;

import java.util.List;

@Repository
public interface EmployeeTerritoryRepository extends JpaRepository<EmployeeTerritory, EmployeeTerritoryId> {
    List<EmployeeTerritory> findByIdEmployeeId(Long employeeId);
    List<EmployeeTerritory> findByIdTerritoryId(Long territoryId);
}
