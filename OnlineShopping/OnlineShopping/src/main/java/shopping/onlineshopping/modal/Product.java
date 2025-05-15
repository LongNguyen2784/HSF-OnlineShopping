package shopping.onlineshopping.modal;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

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

    @JoinColumn(name="supplier_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Supplier supplier;

    @JoinColumn(name="category_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Category category;

    @Column(name="quantity_per_unit")
    @JsonProperty("quantityPerUnit")
    private String quantityPerUnit;

    @Column(name="unit_price")
    @JsonProperty("unitPrice")
    private BigDecimal unit_price;

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
}
