package shopping.onlineshopping.modal;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="Shippers")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Shipper {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int shipperId;

    @Column(name="company_name", nullable = false)
    @JsonProperty("companyName")
    private String companyName;
    @Column(name="shipper_name", nullable = false)
    @JsonProperty("shipperName")
    private String shipperName;
    @Column(name="phone")
    private String phone;
}
