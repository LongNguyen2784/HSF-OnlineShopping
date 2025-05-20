package shopping.onlineshopping.dto.userDTO;

import lombok.*;
import shopping.onlineshopping.modal.user.Roles;
import shopping.onlineshopping.modal.user.Status;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class AccountDTO {
    private String lastName;
    private String firstName;
    private Roles role;
    private Status status;
}
