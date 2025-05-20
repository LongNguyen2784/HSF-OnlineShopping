package shopping.onlineshopping.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import shopping.onlineshopping.dto.ProductDTO;
import shopping.onlineshopping.mapper.ProductMapper;
import shopping.onlineshopping.repository.ProductRepository;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductMapper productMapper;

    public List<ProductDTO> getAllProducts(){
        return productRepository.findAll().stream().map(productMapper::toProductDTO).toList();
    }
    public ProductDTO getProductById(Long id){
        return productMapper.toProductDTO(productRepository.findById(id).get());
    }
    public ProductDTO getProductByName(String name){
        return productMapper.toProductDTO(productRepository.findByName(name));
    }
    public List<ProductDTO> getProductsByPriceRange(Double minPrice,Double maxPrice){
        return productRepository.findByPriceBetween(minPrice,maxPrice).stream().map(productMapper::toProductDTO).toList();
    }
    public List<ProductDTO> getProductsByCategory(String category){
        return productRepository.findByCategory(category).stream().map(productMapper::toProductDTO).toList();
    }

    public ProductDTO updateProduct(ProductDTO productDTO){
        return productMapper.toProductDTO(productRepository.save(productMapper.toProduct(productDTO)));
    }
    public ProductDTO createProduct(ProductDTO productDTO){
        return productMapper.toProductDTO(productRepository.save(productMapper.toProduct(productDTO)));
    }
    public void deleteProductById(Long id){
        productRepository.deleteById(id);
    }
}
