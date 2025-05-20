package shopping.onlineshopping.modal;

import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;

@Embeddable
@NoArgsConstructor
@Getter
@AllArgsConstructor
@Setter
@ToString
public class EmployeeTerritoryId implements Serializable {
    private int employeeId;
    private int territoryId;
}
