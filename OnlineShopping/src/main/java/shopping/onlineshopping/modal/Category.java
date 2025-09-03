package shopping.onlineshopping.modal;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;
import shopping.onlineshopping.modal.product.Product;

import java.util.List;

@Entity
@Table(name="categories")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long categoryId;

    @Column(name="category_name" , nullable = false)
    @JsonProperty("categoryName")
    private String categoryName;
    @ManyToOne
    @JoinColumn(name = "region_id",nullable = false)
    @JsonBackReference
    private Region region;

    @Column(name="description" , nullable = false)
    private String description;
}
