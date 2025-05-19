package shopping.onlineshopping.modal;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="employees")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int employeeId;

    @Column(name="last_name", nullable = false)
    @JsonProperty("lastName")
    private String lastName;

    @Column(name="first_name", nullable = false)
    @JsonProperty("firstName")
    private String firstName;

    @Column(name="title")
    private String title;

    @Column(name="title_of_courtesy")
    @JsonProperty("titleOfCourtesy")
    private String titleOfCourtesy;

    @Column(name="birth_date")
    @JsonProperty("birthDate")
    private String birthDate;

    @Column(name="hire_date")
    @JsonProperty("hireDate")
    private String hireDate;

    @Column(name="address")
    private String address;

    @Column(name="city")
    private String city;

    @Column(name="region")
    private String region;

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


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reports_to")
    private Employee employee;

}
