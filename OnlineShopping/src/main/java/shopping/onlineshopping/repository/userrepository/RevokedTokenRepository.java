package shopping.onlineshopping.repository.userrepository;

import org.springframework.data.repository.CrudRepository;
import shopping.onlineshopping.modal.user.RevokedToken;

public interface RevokedTokenRepository extends CrudRepository<RevokedToken, Long> {
}