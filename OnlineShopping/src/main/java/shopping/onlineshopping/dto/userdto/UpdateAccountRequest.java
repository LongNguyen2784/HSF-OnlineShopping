package shopping.onlineshopping.dto.userdto;

import lombok.Data;

@Data
public class UpdateAccountRequest {
    private String fullName;
    private String phoneNumber;
    private String imageUrl;
}
