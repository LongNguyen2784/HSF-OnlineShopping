package shopping.onlineshopping.modal;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="regions")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Region {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int regionId;

    @Column(name="region_description", nullable = false)
    @JsonProperty("regionDescription")
    private String regionDescription;
}
