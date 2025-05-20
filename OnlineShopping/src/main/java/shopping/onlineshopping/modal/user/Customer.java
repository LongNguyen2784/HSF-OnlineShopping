package shopping.onlineshopping.modal.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="customers")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String customerId;

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

    @Column(name="postal_code")
    @JsonProperty("postalCode")
    private String postalCode;

    @Column(name="country")
    private String country;

    @Column(name="phone")
    private String phone;

    @Column(name="fax")
    private String fax;

    @OneToOne
    @JoinColumn(name = "account_id")
    private Account account;
}
