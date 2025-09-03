package shopping.onlineshopping.modal.user;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;
import shopping.onlineshopping.modal.Region;
import shopping.onlineshopping.modal.product.Product;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name="employees")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Employee {

    @Id
    @Column(name = "employee_id")
    private Long employeeId;

    @OneToOne
    @JoinColumn(name = "employee_id", referencedColumnName = "id") // FK → accounts(id)
    private Account account;

    @Column(name="title")
    private String title;

    @Column(name="title_of_courtesy")
    @JsonProperty("titleOfCourtesy")
    private String titleOfCourtesy;

    @Column(name="birth_date")
    @JsonProperty("birthDate")
    private LocalDateTime birthDate;

    @Column(name="hire_date")
    @JsonProperty("hireDate")
    private LocalDateTime hireDate;

    @Column(name="address")
    private String address;

    @Column(name="city")
    private String city;

    @ManyToOne
    @JoinColumn(name = "region_id")
    private Region region;

    @Column(name="country")
    private String country;

    @Column(name="postal_code")
    @JsonProperty("postalCode")
    private String postalCode;

    @Column(name="home_phone")
    @JsonProperty("homePhone")
    private String homePhone;

    @Column(name="extension")
    private String extension;

    @OneToMany(mappedBy = "employee")
    @JsonManagedReference
    private List<Product> products;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reports_to")
    private Employee reportsTo;


}
