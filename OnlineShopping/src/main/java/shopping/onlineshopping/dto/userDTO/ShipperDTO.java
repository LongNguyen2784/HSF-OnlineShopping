package shopping.onlineshopping.dto.userDTO;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
@Setter
public class ShipperDTO {
    @NonNull
    private String companyName;
    private String phone;
}
