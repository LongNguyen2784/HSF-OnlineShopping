package shopping.onlineshopping.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import shopping.onlineshopping.dto.userdto.SupplierDTO;
import shopping.onlineshopping.service.userservice.SupplierService;

import java.util.List;

@RestController
@RequestMapping("/api/suppliers")
@RequiredArgsConstructor
public class SupplierController {

    private final SupplierService supplierService;

    @GetMapping
    public ResponseEntity<List<SupplierDTO>> getAllSuppliers() {
        return ResponseEntity.ok(supplierService.getAllSuppliers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SupplierDTO> getSupplierById(@PathVariable Long id) {
        return ResponseEntity.ok(supplierService.getSupplierById(id));
    }

    @PostMapping
    public ResponseEntity<SupplierDTO> createSupplier(@RequestBody SupplierDTO supplierDTO) {
        return ResponseEntity.ok(supplierService.createSupplier(supplierDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<SupplierDTO> updateSupplier(@PathVariable Long id, @RequestBody SupplierDTO supplierDTO) {
        supplierDTO.setSupplierId(id); // đảm bảo ID được cập nhật đúng
        return ResponseEntity.ok(supplierService.updateSupplier(supplierDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSupplier(@PathVariable Long id) {
        supplierService.deleteSupplierById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/company-name")
    public ResponseEntity<SupplierDTO> getSupplierByCompanyName(@RequestParam String name) {
        return ResponseEntity.ok(supplierService.getSupplierByCompanyName(name));
    }

    @GetMapping("/city")
    public ResponseEntity<List<SupplierDTO>> getSuppliersByCity(@RequestParam String city) {
        return ResponseEntity.ok(supplierService.getAllSuppliersByCity(city));
    }
}
