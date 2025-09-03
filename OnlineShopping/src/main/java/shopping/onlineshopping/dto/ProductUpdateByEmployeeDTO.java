package shopping.onlineshopping.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductUpdateByEmployeeDTO {
    private Long productId;
    private BigDecimal unitPrice;
    private BigDecimal discount;
    private int unitsInStock;
    private Integer stockQuantity;
    private String status;
    private String imageUrl;
}
