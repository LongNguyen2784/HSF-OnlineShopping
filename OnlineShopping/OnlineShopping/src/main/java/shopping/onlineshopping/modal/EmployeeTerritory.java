package shopping.onlineshopping.modal;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="employee_territories")
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class EmployeeTerritory {
    @EmbeddedId
    private EmployeeTerritoryId employeeTerritoryId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="employee_id",nullable = false)
    private Employee employee;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="territory_id",nullable = false)
    private Territory territory;
}
