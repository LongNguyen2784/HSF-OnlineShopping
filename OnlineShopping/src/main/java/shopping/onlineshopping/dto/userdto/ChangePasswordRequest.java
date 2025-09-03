package shopping.onlineshopping.dto.userdto;

import lombok.Data;

@Data
public class ChangePasswordRequest {
    private String token;
    private String newPassword;
    private String confirmNewPassword;
}