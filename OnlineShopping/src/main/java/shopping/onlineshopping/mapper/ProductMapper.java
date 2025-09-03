package shopping.onlineshopping.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import shopping.onlineshopping.dto.ProductDTO;
import shopping.onlineshopping.dto.ProductShortDTO;
import shopping.onlineshopping.dto.ProductUpdateByEmployeeDTO;
import shopping.onlineshopping.modal.product.Product;
import shopping.onlineshopping.modal.product.ProductStatus;
import shopping.onlineshopping.modal.user.Supplier;

import java.math.BigDecimal;
import java.util.List;

@Mapper(
        componentModel = "spring",
        imports = {ProductStatus.class}
)
public interface ProductMapper {

    @Mapping(source = "productId", target = "id")
    @Mapping(source = "productName",target = "productName")
    @Mapping(source = "supplier.supplierId", target = "supplierId")
    @Mapping(source = "employee.employeeId", target = "createdByEmployeeId")
    @Mapping(source = "category.categoryId", target = "categoryId")
    @Mapping(source = "category.categoryName", target = "categoryName")
    @Mapping(source = "territory.territoryId", target = "territoryId")
    @Mapping(source = "discount", target = "discount")
    @Mapping(target = "discountedPrice", expression = "java(calcDiscountedPrice(product.getUnitPrice(), product.getDiscount()))")
    ProductDTO toProductDTO(Product product);

    @Mapping(source = "id", target = "productId")
    @Mapping(source = "supplierId", target = "supplier", qualifiedByName = "mapSupplierIdToSupplier")
    @Mapping(target = "employee", ignore = true)
    @Mapping(target = "category", ignore = true)
    @Mapping(source = "territoryId", target = "territory.territoryId")
    @Mapping(source = "discount", target = "discount")
    @Mapping(target = "status", expression = "java(productDTO.getStatus() != null ? ProductStatus.valueOf(productDTO.getStatus()) : ProductStatus.PUBLIC)")
    Product toProduct(ProductDTO productDTO);

    @Mapping(source = "discount", target = "discount")
    ProductUpdateByEmployeeDTO toProductUpdateByEmployeeDTO(Product product);
    ProductShortDTO toShortDTO(Product product);
    @Mapping(source = "productName", target = "productName")
    List<ProductShortDTO> toShortDTOs(List<Product> products);
    @Named("mapSupplierIdToSupplier")
    static Supplier mapSupplierIdToSupplier(Long supplierId) {
        if (supplierId == null) return null;
        Supplier supplier = new Supplier();
        supplier.setSupplierId(supplierId);
        return supplier;
    }
    default BigDecimal calcDiscountedPrice(BigDecimal unitPrice, BigDecimal discount) {
        if (unitPrice == null) return BigDecimal.ZERO;
        if (discount == null || discount.compareTo(BigDecimal.ZERO) <= 0) return unitPrice;
        // discount: 10 nghĩa là 10% giảm
        return unitPrice.subtract(
                unitPrice.multiply(discount).divide(BigDecimal.valueOf(100))
        ).setScale(2, BigDecimal.ROUND_HALF_UP);
    }

}
