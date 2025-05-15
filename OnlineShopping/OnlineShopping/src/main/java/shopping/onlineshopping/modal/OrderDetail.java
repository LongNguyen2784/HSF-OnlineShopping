package shopping.onlineshopping.modal;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name="order_details")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class OrderDetail {
    @EmbeddedId
    private OrderDetailId orderId;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("orderId")
    @JoinColumn(name="order_id")
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("productId")
    @JoinColumn(name="product_id")
    private Product product;

    @Column(name="quantity", nullable = false)
    private int quantity;

    @Column(name="discount")
    private BigDecimal discount;

}
