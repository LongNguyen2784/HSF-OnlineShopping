package shopping.onlineshopping.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import shopping.onlineshopping.dto.ProductDTO;
import shopping.onlineshopping.dto.ProductShortDTO;
import shopping.onlineshopping.dto.ProductUpdateByEmployeeDTO;
import shopping.onlineshopping.modal.user.Account;
import shopping.onlineshopping.modal.user.Roles;
import shopping.onlineshopping.repository.userrepository.EmployeeRepository;
import shopping.onlineshopping.service.ProductService;
import java.math.BigDecimal;
import java.nio.file.AccessDeniedException;
import java.util.List;

@RestController
@RequestMapping("/api/product")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;
    private final EmployeeRepository employeeRepository;

    @GetMapping()
    public ResponseEntity<List<ProductDTO>> getAllProducts(){
        return ResponseEntity.ok(productService.getAllProducts());
    }
    @GetMapping("/search")
    public List<ProductShortDTO> searchProducts(@RequestParam("q") String keyword) {
        return productService.searchProducts(keyword);
    }
    @GetMapping("/id/{id}")
    public ProductDTO getProductById(@PathVariable Long id){
        return productService.getProductById(id);
    }
    @GetMapping("/name/{name}")
    public ProductDTO getProductByName(@PathVariable String name){
        return productService.getProductByName(name);
    }
    @GetMapping("/price-range/{minPrice}/{maxPrice}")
    public List<ProductDTO> getProductsByPriceRange(@PathVariable BigDecimal minPrice, @PathVariable BigDecimal maxPrice){
        return productService.getProductsByPriceRange(minPrice,maxPrice);
    }
    @GetMapping("/category/{categoryId}")
    public List<ProductDTO> getProductsByCategory(@PathVariable int categoryId){
        return productService.getProductsByCategory(categoryId);
    }
    @PostMapping("/employee/create")
    public ResponseEntity<ProductDTO> createProduct(
            @AuthenticationPrincipal Account account,
            @RequestBody ProductDTO productDTO) throws AccessDeniedException {

        if (account.getRole() != Roles.EMPLOYEE) {
            throw new AccessDeniedException("Chỉ Employee mới được tạo product");
        }

        ProductDTO createdProduct = productService.createProductByEmployee(account, productDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdProduct);
    }
    @PutMapping("/{id}/employee/update")
    public ResponseEntity<ProductUpdateByEmployeeDTO> updateProductByEmployee(
            @AuthenticationPrincipal Account account,
            @PathVariable("id") Long productId,
            @RequestBody ProductUpdateByEmployeeDTO dto) {

        ProductUpdateByEmployeeDTO updatedProduct = productService.updateProductByEmployee(account, productId, dto);
        return ResponseEntity.ok(updatedProduct);
    }
    @GetMapping("/{id}/employee")
    public ResponseEntity<ProductUpdateByEmployeeDTO> getProductForEmployee(
            @AuthenticationPrincipal Account account,
            @PathVariable("id") Long productId) throws AccessDeniedException {
        System.out.println("Current employee account: " + account);
        ProductUpdateByEmployeeDTO dto = productService.getProductForEmployee(account, productId);
        return ResponseEntity.ok(dto);
    }
}
