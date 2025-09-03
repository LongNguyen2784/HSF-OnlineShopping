package shopping.onlineshopping.modal;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import shopping.onlineshopping.modal.product.Product;
import shopping.onlineshopping.modal.user.Account;
import shopping.onlineshopping.modal.user.Customer;

import java.time.LocalDateTime;

@Entity
@Table(name = "carts")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Người dùng (Customer)
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private Account user;

    @OneToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    // Sản phẩm
    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    // Số lượng sản phẩm trong giỏ
    @Column(nullable = false)
    private int quantity;

    // Có thể thêm thời gian thêm vào giỏ nếu cần
    @Column(name = "added_date")
    private LocalDateTime addedDate;
}
