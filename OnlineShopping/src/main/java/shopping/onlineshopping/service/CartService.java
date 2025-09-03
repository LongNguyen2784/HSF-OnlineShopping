package shopping.onlineshopping.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import shopping.onlineshopping.modal.Cart;
import shopping.onlineshopping.modal.product.Product;
import shopping.onlineshopping.modal.user.Account;
import shopping.onlineshopping.repository.CartItemRepository;
import shopping.onlineshopping.repository.ProductRepository;
import shopping.onlineshopping.repository.userrepository.AccountRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CartService {
    private final CartItemRepository cartItemRepo;
    private final ProductRepository productRepo;
    private final AccountRepository accountRepo;
    public void addToCart(Long accountId, Long productId, int quantity) {
        Product product = productRepo.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        Account user = accountRepo.findById(accountId)
                .orElseThrow(() -> new RuntimeException("Account not found"));

        Cart item = new Cart();
        item.setUser(user); // Đã fetch đầy đủ thông tin từ DB
        item.setProduct(product);
        item.setQuantity(quantity);

        cartItemRepo.save(item);
    }

    public List<Cart> getCartItems(Long userId) {
        return cartItemRepo.findByUserId(userId);
    }

    public void clearCart(Long userId) {
        cartItemRepo.deleteByUserId(userId);
    }
}

