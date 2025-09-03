package shopping.onlineshopping.service;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;
import shopping.onlineshopping.dto.ProductDTO;
import shopping.onlineshopping.dto.ProductShortDTO;
import shopping.onlineshopping.dto.ProductUpdateByEmployeeDTO;
import shopping.onlineshopping.dto.userdto.EmployeeTerritoryDTO;
import shopping.onlineshopping.exception.ResourceNotFoundException;
import shopping.onlineshopping.mapper.ProductMapper;
import shopping.onlineshopping.modal.Category;
import shopping.onlineshopping.modal.Territory;
import shopping.onlineshopping.modal.product.Product;
import shopping.onlineshopping.modal.product.ProductStatus;
import shopping.onlineshopping.modal.user.*;
import shopping.onlineshopping.repository.CategoryRepository;
import shopping.onlineshopping.repository.ProductRepository;
import shopping.onlineshopping.repository.userrepository.EmployeeRepository;
import shopping.onlineshopping.repository.userrepository.EmployeeTerritoryRepository;
import shopping.onlineshopping.service.userservice.EmployeeTerritoryService;
import java.math.BigDecimal;
import java.nio.file.AccessDeniedException;
import java.util.List;
import java.util.stream.Collectors;
@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final EmployeeTerritoryRepository employeeTerritoryRepository;
    private final ProductMapper productMapper;
    private final EmployeeRepository employeeRepository;
    private final CategoryRepository categoryRepository;
    private final EmployeeTerritoryService employeeTerritoryService;

    public ProductDTO createProductByEmployee(@NotNull Account account, ProductDTO dto)
            throws AccessDeniedException {

        if (account.getRole() != Roles.EMPLOYEE) {
            throw new IllegalStateException("Only employees can create products.");
        }

        Employee employee = employeeRepository.findById(account.getId())
                .orElseThrow(() -> new EntityNotFoundException("Employee not found"));
        Category category = categoryRepository.findById(dto.getCategoryId())
                .orElseThrow(() -> new EntityNotFoundException("Category not found"));

        List<EmployeeTerritoryDTO> territories = employeeTerritoryService.getTerritoriesByEmployee(employee.getEmployeeId());
        Long territoryId = territories.stream()
                .map(EmployeeTerritoryDTO::getTerritoryId)
                .findFirst()
                .orElseThrow(() -> new AccessDeniedException("Không tìm thấy territoryId."));
        if (!employeeTerritoryService.isEmployeeInTerritory(employee.getEmployeeId(), territoryId)) {
            throw new AccessDeniedException("Employee không có quyền tạo sản phẩm trong vùng này.");
        }

        Product product = productMapper.toProduct(dto);
        product.setEmployee(employee);
        product.setCategory(category);

        Product saved = productRepository.save(product);
        return productMapper.toProductDTO(saved);
    }


        public @NotNull List<ProductShortDTO> searchProducts(String keyword) {
        List<Product> products;
        try {
            Long id = Long.parseLong(keyword);
            products = productRepository.findByIdOrProductNameContainingIgnoreCase(id, keyword);
        } catch (NumberFormatException e) {
            products = productRepository.findByProductNameContainingIgnoreCase(keyword);
        }

        return products.stream()
                .map(productMapper::toShortDTO)
                .collect(Collectors.toList());
    }

    public List<ProductDTO> getProductsByPriceRange(BigDecimal minPrice, BigDecimal maxPrice){
        return productRepository.findByUnitPriceBetween(minPrice,maxPrice).stream().map(productMapper::toProductDTO).collect(Collectors.toList());
    }

    public ProductDTO getProductByName(String productName){
        return productMapper.toProductDTO(productRepository.findByProductName(productName));
    }
    public List<ProductDTO> getProductsByCategory(int id){
        return productRepository.findByCategoryCategoryId(id).stream().map(productMapper::toProductDTO).collect(Collectors.toList());
    }
    @Transactional
    public ProductUpdateByEmployeeDTO updateProductByEmployee(Account account, Long productId, ProductUpdateByEmployeeDTO dto) {
        if (account.getRole() != Roles.EMPLOYEE) {
            throw new IllegalStateException("Only employee account can update product.");
        }

        Long employeeId = account.getId(); // hoặc lấy từ account

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new IllegalStateException("Product not found"));

        // Kiểm tra quyền theo region
        List<EmployeeTerritory> territories = employeeTerritoryRepository.findByEmployeeEmployeeId(employeeId);
        boolean hasPermission = territories.stream()
                .anyMatch(et -> et.getTerritory().getRegion().equals(product.getCategory().getRegion()));

        if (!hasPermission) {
            throw new IllegalStateException("Employee doesn't have permission to update this product.");
        }

        // Chỉ set các trường cho phép
        product.setUnitPrice(dto.getUnitPrice());
        product.setUnitsInStock(dto.getUnitsInStock());
        product.setStatus(ProductStatus.valueOf(dto.getStatus().toUpperCase()));
        product.setImageUrl(dto.getImageUrl());
        product.setDiscount(dto.getDiscount());

        productRepository.save(product);

        return productMapper.toProductUpdateByEmployeeDTO(product);
    }
    private boolean canEmployeeAccessProduct(Employee employee, Product product) {
        Territory productTerritory = product.getTerritory();

        if (productTerritory == null) return false;

        // Giả sử bạn có repository EmployeeTerritoryRepository
        List<EmployeeTerritory> employeeTerritories = employeeTerritoryRepository.findByEmployee(employee);

        return employeeTerritories.stream()
                .anyMatch(et -> et.getTerritory().getTerritoryId().equals(productTerritory.getTerritoryId()));
    }
    @Transactional()
    public ProductUpdateByEmployeeDTO getProductForEmployee(Account account, Long productId) throws AccessDeniedException {
        if (account.getRole() != Roles.EMPLOYEE) {
            throw new AccessDeniedException("Chỉ nhân viên mới được xem sản phẩm.");
        }

        Employee employee = employeeRepository.findByAccount(account)
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy thông tin nhân viên."));

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy sản phẩm"));

        if (!canEmployeeAccessProduct(employee, product)) {
            throw new AccessDeniedException("Bạn không có quyền xem sản phẩm này");
        }

        return productMapper.toProductUpdateByEmployeeDTO(product);
    }



    public  List<ProductDTO> getAllProducts() {
        return productRepository.findAll().stream().map(productMapper::toProductDTO).collect(Collectors.toList()) ;
    }
    public ProductDTO getProductById(Long productId) {
        return productRepository.findById(productId).stream().map(productMapper::toProductDTO).toList().getFirst();
    }
}
