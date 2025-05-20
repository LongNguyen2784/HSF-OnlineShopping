package shopping.onlineshopping.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import shopping.onlineshopping.dto.ProductDTO;
import shopping.onlineshopping.service.ProductService;

import java.util.List;

@RestController
@RequestMapping("/api/product")
public class ProductController {
    @Autowired
    private ProductService productService;
    @GetMapping()
    public List<ProductDTO> getAllProducts(){
        return productService.getAllProducts();
    }
    @GetMapping("/{id}")
    public ProductDTO getProductById(@PathVariable Long id){
        return productService.getProductById(id);
    }
    @GetMapping("/{name}")
    public ProductDTO getProductByName(@PathVariable String name){
        return productService.getProductByName(name);
    }
    @GetMapping("/{minPrice}/{maxPrice}")
    public List<ProductDTO> getProductsByPriceRange(@PathVariable Double minPrice,@PathVariable Double maxPrice){
        return productService.getProductsByPriceRange(minPrice,maxPrice);
    }
    @GetMapping("/{category}")
    public List<ProductDTO> getProductsByCategory(@PathVariable String category){
        return productService.getProductsByCategory(category);
    }
    @PostMapping()
    public ProductDTO createProduct(@RequestBody ProductDTO productDTO){
        return productService.createProduct(productDTO);
    }
    @PutMapping()
    public ProductDTO updateProduct(@RequestBody ProductDTO productDTO){
        return productService.updateProduct(productDTO);
    }
    @DeleteMapping("/{id}")
    public void deleteProductById(@PathVariable Long id){
        productService.deleteProductById(id);
    }
}
