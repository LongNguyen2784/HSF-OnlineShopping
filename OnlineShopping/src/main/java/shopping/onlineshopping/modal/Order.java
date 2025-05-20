package shopping.onlineshopping.modal;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;
import shopping.onlineshopping.modal.user.Customer;
import shopping.onlineshopping.modal.user.Employee;
import shopping.onlineshopping.modal.user.Shipper;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name="orders")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int orderId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="customer_id")
    private Customer customer ;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="employee_id")
    private Employee employee;

    @Column(name="order_date")
    @JsonProperty("orderDate")
    private LocalDateTime orderDate;

    @Column(name="required_date")
    @JsonProperty("requiredDate")
    private LocalDateTime requiredDate;

    @Column(name="shipped_date")
    @JsonProperty("shippedDate")
    private LocalDateTime shippedDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="ship_via" ,referencedColumnName="shipper_id")
    private Shipper shipper;

    @Column(name="freight")
    private BigDecimal freight;

    @Column(name="ship_name")
    @JsonProperty("shipName")
    private String shipName;

    @Column(name="ship_address")
    @JsonProperty("shipAddress")
    private String shipAddress;

    @Column(name="ship_city")
    @JsonProperty("shipCity")
    private String shipCity;

    @Column(name="ship_region")
    @JsonProperty("shipRegion")
    private String shipRegion;

    @Column(name="ship_postal_code")
    @JsonProperty("shipPostalCode")
    private String shipPostalCode;

    @Column(name="ship_country")
    @JsonProperty("shipCountry")
    private String shipCountry;
}
