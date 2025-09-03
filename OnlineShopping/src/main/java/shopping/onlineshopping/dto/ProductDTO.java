package shopping.onlineshopping.dto;
import lombok.*;
import java.math.BigDecimal;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {
    private Long  id;
    @NonNull
    private String productName;
    private Long supplierId;
    private Long createdByEmployeeId;
    private Long categoryId;
    private Long territoryId;
    private BigDecimal discount;
    private BigDecimal discountedPrice;
    private String status;
    private String categoryName;
    private String imageUrl;
    private String quantityPerUnit;
    private String description;
    private BigDecimal unitPrice;
    private int unitsInStock;
    private int unitsOnOrder;
    private int reorderLevel;
    private int discontinued;
}
