package shopping.onlineshopping.dto;
import lombok.*;
import java.math.BigDecimal;

@NoArgsConstructor
@Getter
@AllArgsConstructor
@ToString
@Setter
public class OrderDetailDTO {
    private Long orderId;
    private Long productId;
    private int quantity;
    private BigDecimal discount;
}
