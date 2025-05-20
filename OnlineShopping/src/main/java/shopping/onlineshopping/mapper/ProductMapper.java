package shopping.onlineshopping.mapper;

import org.mapstruct.Mapper;
import shopping.onlineshopping.dto.ProductDTO;
import shopping.onlineshopping.modal.Product;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    ProductDTO toProductDTO(Product product);
    Product toProduct(ProductDTO productDTO);

}
