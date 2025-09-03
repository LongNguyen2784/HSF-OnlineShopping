package shopping.onlineshopping.dto;

import lombok.*;
import shopping.onlineshopping.modal.user.Customer;
import shopping.onlineshopping.modal.user.Shipper;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class OrderDTO {
    private Long customerId;
    private Long employeeId;
    private LocalDateTime orderDate;
    private LocalDateTime requiredDate;
    private LocalDateTime shippedDate;
    private Long shipperId;
    private BigDecimal freight;
    private List<OrderDetailDTO> items;
    private Long OrderId;
}
