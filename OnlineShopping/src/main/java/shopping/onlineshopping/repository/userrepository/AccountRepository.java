package shopping.onlineshopping.repository.userrepository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import shopping.onlineshopping.modal.user.Account;
import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    Optional<Account> findByUsername(String username);
    Optional<Account> findByEmail(String email);
    boolean existsByEmail(@Param("email") String email);
    boolean existsByUsername(@Param("username") String username);
    Optional<Account> findByUsernameAndActivationToken(String username, String token);

    Optional<Account> findByResetToken(String resetToken);

}
