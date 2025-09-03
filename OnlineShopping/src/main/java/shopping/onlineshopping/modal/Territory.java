package shopping.onlineshopping.modal;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="territories")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Territory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long territoryId;

    @Column(name = "territory_name", nullable = false)
    @JsonProperty("territoryName")
    private String territoryName;

    @Column(name="territory_description", nullable = false)
    @JsonProperty("territoryDescription")
    private String territoryDescription;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="region_id", nullable = false)
    private Region region;
}
