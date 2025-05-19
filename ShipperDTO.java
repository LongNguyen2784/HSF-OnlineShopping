package shopping.onlineshopping.dto;
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
