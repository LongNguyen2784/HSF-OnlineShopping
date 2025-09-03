package shopping.onlineshopping.modal.user;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="suppliers")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Supplier {
    @Id
    @Column(name = "supplier_id")
    private Long supplierId;

    @OneToOne
    @MapsId
    @JoinColumn(name = "supplier_id", referencedColumnName = "id")
    private Account account;

    @Column(name="company_name" , nullable = false)
    @JsonProperty("companyName")
    private String companyName;

    @Column(name="contact_name")
    @JsonProperty("contactName")
    private String contactName;

    @Column(name="contact_title")
    @JsonProperty("contactTitle")
    private String contactTitle;

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

    @Column(name="phone")
    private String phone;

    @Column(name="fax")
    private String fax;

    @Column(name="home_page")
    @JsonProperty("homePage")
    private String homePage;
}
