package shopping.onlineshopping.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import shopping.onlineshopping.dto.CategoryDTO;
import shopping.onlineshopping.service.CategoryService;

import java.util.List;

@RestController
@RequestMapping("/api/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;
    @GetMapping()
    public List<CategoryDTO> getAllCategories(){
        return categoryService.getAllCategories();
    }
    @GetMapping("/{id}")
    public CategoryDTO getCategoryById(@PathVariable Long id){
        return categoryService.getCategoryById(id);
    }
    @GetMapping("/{name}")
    public CategoryDTO getCategoryByName(@PathVariable String name){
        return categoryService.getCategoryByName(name);
    }
    @PostMapping()
    public CategoryDTO createCategory(@RequestBody CategoryDTO category){
        return categoryService.createCategory(category);
    }
    @PutMapping()
    public CategoryDTO updateCategory(@RequestBody CategoryDTO category){
        return categoryService.updateCategory(category);
    }
    @DeleteMapping("/{id}")
    public void deleteCategoryById(@PathVariable Long id){
        categoryService.deleteCategoryById(id);
    }
}
