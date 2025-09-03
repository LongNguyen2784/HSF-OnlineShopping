package shopping.onlineshopping.modal;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;
import shopping.onlineshopping.modal.user.Employee;

import java.util.List;

@Entity
@Table(name="regions")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Region {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long regionId;
    @JsonManagedReference
    @OneToMany(mappedBy = "region")
    private List<Employee> employees;
    @JsonManagedReference
    @OneToMany(mappedBy = "region")
    private List<Category>  categories;
    @OneToMany(mappedBy = "region")
    @JsonManagedReference
    private List<Territory> territories;
    @Column(name="region_description", nullable = false)
    @JsonProperty("regionDescription")
    private String regionDescription;

}
