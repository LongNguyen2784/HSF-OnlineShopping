package shopping.onlineshopping.modal.product;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;
import shopping.onlineshopping.modal.Category;
import shopping.onlineshopping.modal.Territory;
import shopping.onlineshopping.modal.user.Employee;
import shopping.onlineshopping.modal.user.Supplier;

import java.math.BigDecimal;

@Entity
@Table(name="products")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int productId;

    @Column(name="product_name" , nullable = false)
    @JsonProperty("productName")
    private String productName;

    @Column(name = "image_url")
    @JsonProperty("imageUrl")
    private String imageUrl;

    @JoinColumn(name="supplier_id")
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    private Supplier supplier;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    @JsonBackReference
    private Employee employee;

    @JoinColumn(name="category_id")
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    private Category category;

    @Column(name="quantity_per_unit")
    @JsonProperty("quantityPerUnit")
    private String quantityPerUnit;

    @Column(name="description")
    private String description;

    @Column(name="unit_price")
    @JsonProperty("unitPrice")
    private BigDecimal unitPrice;

    @Column(name="units_in_stock")
    @JsonProperty("unitsInStock")
    private int unitsInStock;

    @Column(name="units_on_order")
    @JsonProperty("unitsOnOrder")
    private int unitsOnOrder;

    @Column(name="reorder_level")
    @JsonProperty("reorderLevel")
    private int reorderLevel;

    @Column(name="discontinued")
    private int discontinued;

    @Column(name = "discount", precision = 5, scale = 2)
    private BigDecimal discount = BigDecimal.ZERO;

    @Enumerated(EnumType.STRING)
    @Column(name="status", nullable = false)
    private ProductStatus status;

    @ManyToOne
    @JoinColumn(name = "territory_id", nullable = false)
    private Territory territory;
}
