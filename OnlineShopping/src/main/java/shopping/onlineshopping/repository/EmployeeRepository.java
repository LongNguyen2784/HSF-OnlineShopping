package shopping.onlineshopping.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import shopping.onlineshopping.modal.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
