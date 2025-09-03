package shopping.onlineshopping.modal;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "payments")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "order_id")
    private Order order;

    private String method; // VNPAY, MOMO, CASH

    private String status; // SUCCESS, FAILED, PENDING

    private LocalDateTime paymentDate;

    private String transactionId; // mã giao dịch VNPay (vnp_TransactionNo)
    private String responseCode;  // mã phản hồi (vnp_ResponseCode)
    private Long amount;
    private String bankCode;


}
