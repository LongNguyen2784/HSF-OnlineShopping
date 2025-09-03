package shopping.onlineshopping.repository.userrepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import shopping.onlineshopping.modal.user.Account;
import shopping.onlineshopping.modal.user.Employee;

import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Employee findByHomePhone(String homePhone);
    Optional<Employee> findByAccount(Account account);
}
