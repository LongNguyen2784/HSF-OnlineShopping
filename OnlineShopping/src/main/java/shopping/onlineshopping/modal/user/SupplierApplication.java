package shopping.onlineshopping.modal.user;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "supplier_applications")
@Data
public class SupplierApplication {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "account_id", nullable = false)
    private Account account;

    private String companyName;
    private String contactName;
    private String contactTitle;
    private String address;
    private String city;
    private String region;
    private String country;
    private String postalCode;
    private String phone;
    private String fax;
    private String homePage;

    @Enumerated(EnumType.STRING)
    private Status status = Status.INACTIVE;

    private String adminNote;

    private LocalDateTime submittedAt = LocalDateTime.now();
    private LocalDateTime reviewedAt;
}

