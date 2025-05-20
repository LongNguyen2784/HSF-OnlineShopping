package shopping.onlineshopping.dto;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class EmployeeDTO {
    @NonNull
    private String lastName;
    @NonNull
    private String firstName;
    private String title;
    private String titleOfCourtesy;
    private String birthDate;
    private String hireDate;
    private String address;
    private String city;
    private String region;
    private String country;
    private String postalCode;
    private String homePhone;
    private String extension;


}
