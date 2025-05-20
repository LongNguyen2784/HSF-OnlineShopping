package shopping.onlineshopping.dto.userDTO;

import lombok.Data;
import shopping.onlineshopping.modal.user.Roles;

@Data
public class RegisterRequest {
    private String email;
    private String password;
    private String confirmPassword;
    private Roles role; // Enum
}
