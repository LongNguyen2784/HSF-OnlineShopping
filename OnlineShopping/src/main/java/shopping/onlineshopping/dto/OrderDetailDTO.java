package shopping.onlineshopping.dto;
import lombok.*;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetailDTO {
    private int orderId;
    private int productId;
    private BigDecimal unitPrice;
    private int quantity;
    private float discount;
}
