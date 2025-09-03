package shopping.onlineshopping.dto.userdto;

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
