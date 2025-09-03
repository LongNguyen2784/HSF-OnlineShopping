package shopping.onlineshopping.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import shopping.onlineshopping.modal.Order;
import shopping.onlineshopping.modal.Payment;
import shopping.onlineshopping.security.VNPayConfig;
import shopping.onlineshopping.repository.OrderRepository;
import shopping.onlineshopping.repository.PaymentRepository;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final OrderRepository orderRepository;

    /**
     * Xử lý callback từ VNPay và xác minh tính hợp lệ của chữ ký.
     */
    public int verifyVNPaySignature(Map<String, String> allParams) {
        Map<String, String> fields = new HashMap<>();

        for (Map.Entry<String, String> entry : allParams.entrySet()) {
            String key = URLEncoder.encode(entry.getKey(), StandardCharsets.US_ASCII);
            String value = URLEncoder.encode(entry.getValue(), StandardCharsets.US_ASCII);

            if (!key.equals("vnp_SecureHash") && !key.equals("vnp_SecureHashType")) {
                fields.put(key, value);
            }
        }

        String vnp_SecureHash = allParams.get("vnp_SecureHash");
        String signValue = VNPayConfig.hashAllFields(fields);

        if (signValue.equals(vnp_SecureHash)) {
            return "00".equals(allParams.get("vnp_TransactionStatus")) ? 1 : 0;
        }
        return -1;
    }

    /**
     * Lưu thông tin thanh toán từ VNPay vào bảng Payment.
     */
    public void saveVNPayPayment(Long orderId, Map<String, String> allParams) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new IllegalArgumentException("Order không tồn tại"));

        Payment payment = new Payment();
        payment.setOrder(order);
        payment.setMethod("VNPAY");
        payment.setStatus(getStatusFromCode(allParams.get("vnp_TransactionStatus")));
        payment.setPaymentDate(LocalDateTime.now());

        paymentRepository.save(payment);
    }

    private String getStatusFromCode(String code) {
        if ("00".equals(code)) return "SUCCESS";
        else if ("01".equals(code)) return "FAILED";
        else return "PENDING";
    }

    public Payment getPaymentByOrderId(Long orderId) {
        return paymentRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy thanh toán cho đơn hàng ID: " + orderId));
    }
}
