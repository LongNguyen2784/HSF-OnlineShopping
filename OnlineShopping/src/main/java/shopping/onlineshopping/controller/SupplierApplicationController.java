package shopping.onlineshopping.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import shopping.onlineshopping.dto.userdto.SupplierApplicationRequest;
import shopping.onlineshopping.service.userservice.SupplierApplicationService;

@RestController
@RequestMapping("/api/supplier-applications")
@RequiredArgsConstructor
public class SupplierApplicationController {

    private final SupplierApplicationService applicationService;

    @PostMapping("/submit")
    public ResponseEntity<?> submit(@RequestBody SupplierApplicationRequest request, @RequestParam Long accountId) {
        applicationService.submitApplication(accountId, request);
        return ResponseEntity.ok("Application submitted successfully");
    }

    @PostMapping("/{id}/approve")
    public ResponseEntity<?> approve(@PathVariable Long id, @RequestParam String note) {
        applicationService.approveApplication(id, note);
        return ResponseEntity.ok("Application approved and supplier created");
    }
}
