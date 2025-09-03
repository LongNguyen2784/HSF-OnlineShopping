package shopping.onlineshopping.dto.userdto;
import lombok.*;

@NoArgsConstructor
@Getter
@AllArgsConstructor
@Setter
@ToString
public class SupplierDTO {
    private Long supplierId;
    @NonNull
    private String companyName;
    private String contactName;
    private String contactTitle;
    private String address;
    private String city;
    private String region;
    private String country;
    private String postalCode;
    private String phone;
    private String fax;
    private String homePage;

}
