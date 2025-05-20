package shopping.onlineshopping.modal;
import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class OrderDetailId implements Serializable {
    private int orderId;
    private int productId;


}

