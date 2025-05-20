package shopping.onlineshopping.dto.userDTO;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
@Setter
public class EmployeeTerritoryDTO {
    @NonNull
    private Long employeeId;
    @NonNull
    private Long territoryId;
}
