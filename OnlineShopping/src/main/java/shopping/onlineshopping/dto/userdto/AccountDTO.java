package shopping.onlineshopping.dto.userdto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import shopping.onlineshopping.modal.user.Roles;
import shopping.onlineshopping.modal.user.Status;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountDTO {
    private String username;
    private String email;
    private Roles role;
    private Status status;

}
