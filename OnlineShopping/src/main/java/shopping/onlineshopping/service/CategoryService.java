package shopping.onlineshopping.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import shopping.onlineshopping.dto.CategoryDTO;
import shopping.onlineshopping.mapper.CategoryMapper;
import shopping.onlineshopping.modal.Category;
import shopping.onlineshopping.modal.user.Account;
import shopping.onlineshopping.modal.user.Roles;
import shopping.onlineshopping.repository.CategoryRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    public List<CategoryDTO> getAllCategories() {
        return categoryRepository.findAll()
                .stream()
                .map(categoryMapper::toCategoryDTO)
                .collect(Collectors.toList());
    }

    public List<CategoryDTO> getAllCategoriesByRegion(long regionId) {
        return categoryRepository.findByRegionRegionId(regionId)
                .stream()
                .map(categoryMapper::toCategoryDTO)
                .collect(Collectors.toList());
    }

    public CategoryDTO createCategoryByAdmin(Account account, CategoryDTO categoryDTO) {
        if (account.getRole() != Roles.ADMIN) {
            throw new AccessDeniedException("Chỉ admin mới có quyền tạo danh mục.");
        }

        Category category = categoryMapper.toCategory(categoryDTO);
        Category saved = categoryRepository.save(category);
        return categoryMapper.toCategoryDTO(saved);
    }
    public void deleteCategoryByAdmin(Account account, Long categoryId) {
        if (account.getRole() != Roles.ADMIN) {
            throw new AccessDeniedException("Chỉ admin mới có quyền xóa danh mục");
        }

        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new EntityNotFoundException("Category không tồn tại"));

        categoryRepository.delete(category);
    }
}
