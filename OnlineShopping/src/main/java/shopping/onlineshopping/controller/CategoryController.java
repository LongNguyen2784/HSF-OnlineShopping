package shopping.onlineshopping.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import shopping.onlineshopping.dto.CategoryDTO;
import shopping.onlineshopping.modal.user.Account;
import shopping.onlineshopping.service.CategoryService;

import java.util.List;

@RestController
@RequestMapping("/api/category")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;
    @GetMapping("")
    public ResponseEntity<List<CategoryDTO>> getAll() {
        List<CategoryDTO> list = categoryService.getAllCategories(); // ✅ Mapper convert đúng
        return ResponseEntity.ok(list); // ✅ Không trả về entity
    }

    @PostMapping("/admin")
    public CategoryDTO createCategory(@AuthenticationPrincipal Account account,
            @RequestBody CategoryDTO category){
        return categoryService.createCategoryByAdmin(account,category);
    }
    @DeleteMapping("/admin/{id}")
    public void deleteCategory(@AuthenticationPrincipal Account account, @PathVariable Long id) {
        categoryService.deleteCategoryByAdmin(account,id);
    }
    @GetMapping("/region/{regionId}")
    public List<CategoryDTO> getCategoryByRegionId(@PathVariable("regionId") int regionId){
        return categoryService.getAllCategoriesByRegion(regionId);
    }

}
