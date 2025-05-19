package shopping.onlineshopping.dto;
import lombok.*;
import java.math.BigDecimal;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ProductDTO {
    @NonNull
    private String productName;
    private Long supplierId;
    private Long categoryId;
    private String quantityPerUnit;
    private BigDecimal unit_price;
    private int unitsInStock;
    private int unitsOnOrder;
    private int reorderLevel;
    private int discontinued;
}
