package shopping.onlineshopping.modal;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="categories")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int categoryId;

    @Column(name="category_name" , nullable = false)
    @JsonProperty("categoryName")
    private String categoryName;

    @Column(name="description" , nullable = false)
    private String description;
}
