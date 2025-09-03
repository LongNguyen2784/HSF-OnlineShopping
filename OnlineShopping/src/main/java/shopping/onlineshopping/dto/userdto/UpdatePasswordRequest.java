package shopping.onlineshopping.dto.userdto;

import io.micrometer.common.lang.NonNull;
import lombok.Data;

@Data
public class UpdatePasswordRequest {
    @NonNull
    private String oldPassword;
    @NonNull
    private String newPassword;
}
