package shopping.onlineshopping.repository.userrepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import shopping.onlineshopping.modal.user.Status;
import shopping.onlineshopping.modal.user.SupplierApplication;

import java.util.List;

@Repository
public interface SupplierApplicationRepository  extends JpaRepository<SupplierApplication,Long> {
    List<SupplierApplication> findByStatus(Status status);
    boolean existsByAccount_IdAndStatus(Long accountId, Status status);
}
