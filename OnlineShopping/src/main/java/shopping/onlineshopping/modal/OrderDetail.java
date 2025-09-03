package shopping.onlineshopping.modal;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;
import shopping.onlineshopping.modal.product.Product;

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
    private OrderDetailId orderId = new OrderDetailId();

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("orderId")
    @JoinColumn(name="order_id")
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("productId")
    @JoinColumn(name="product_id",nullable = false)
    private Product product;

    @Column(name="unit_price",nullable = false)
    @JsonProperty("unitPrice")
    private BigDecimal unitPrice;

    @Column(name="quantity", nullable = false)
    private int quantity;

    @Column(name="discount")
    private BigDecimal discount;

}
