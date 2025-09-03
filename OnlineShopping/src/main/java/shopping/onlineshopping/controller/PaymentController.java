package shopping.onlineshopping.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import shopping.onlineshopping.modal.Payment;
import shopping.onlineshopping.service.VNPayService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import shopping.onlineshopping.service.PaymentService;

import java.util.Map;

@RestController
@RequestMapping("/api/payments")
@RequiredArgsConstructor
public class PaymentController {

    private final VNPayService vnPayService;
    private final PaymentService paymentService;

    /**
     * Callback VNPay redirect về: dùng query param thay vì HttpServletRequest
     */
    @GetMapping("/vnpay-return")
    public ResponseEntity<?> handleVNPayReturn(@RequestParam Map<String, String> allParams) {
        int result = vnPayService.orderReturn(allParams); // sửa lại service để nhận Map<String, String>
        String orderIdStr = allParams.get("vnp_OrderInfo"); // ID đơn hàng client gửi lúc tạo order

        if (result == 1 && orderIdStr != null) {
            try {
                Long orderId = Long.parseLong(orderIdStr);
                paymentService.saveVNPayPayment(orderId, allParams); // cũng sửa service để nhận Map
                return ResponseEntity.ok("✅ Thanh toán VNPay thành công!");
            } catch (NumberFormatException e) {
                return ResponseEntity.badRequest().body("❌ Order ID không hợp lệ!");
            }
        } else if (result == 0) {
            return ResponseEntity.badRequest().body("❌ Thanh toán thất bại từ VNPay");
        } else {
            return ResponseEntity.badRequest().body("❌ Sai chữ ký hash!");
        }
    }
    @GetMapping("/order/{orderId}")
    public ResponseEntity<Payment> getPaymentByOrder(@PathVariable Long orderId) {
        return ResponseEntity.ok(paymentService.getPaymentByOrderId(orderId));
    }
}



