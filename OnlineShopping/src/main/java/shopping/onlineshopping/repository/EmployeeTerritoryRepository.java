package shopping.onlineshopping.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import shopping.onlineshopping.modal.EmployeeTerritory;
import shopping.onlineshopping.modal.EmployeeTerritoryId;

import java.util.List;

@Repository
public interface EmployeeTerritoryRepository extends JpaRepository<EmployeeTerritory, EmployeeTerritoryId> {
    List<EmployeeTerritory> findByIdEmployeeId(Long employeeId);
    List<EmployeeTerritory> findByIdTerritoryId(Long territoryId);
}
