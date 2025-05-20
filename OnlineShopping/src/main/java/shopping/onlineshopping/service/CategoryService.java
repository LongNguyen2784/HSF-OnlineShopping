package shopping.onlineshopping.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import shopping.onlineshopping.dto.CategoryDTO;
import shopping.onlineshopping.mapper.CategoryMapper;
import shopping.onlineshopping.repository.CategoryRepository;

import java.util.List;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private CategoryMapper categoryMapper;

    public CategoryDTO getCategoryById(Long id){
        return categoryMapper.toCategoryDTO(categoryRepository.findById(id).get());
    }
    public List<CategoryDTO> getAllCategories(){
        return categoryRepository.findAll().stream().map(categoryMapper::toCategoryDTO).toList();
    }
    public CategoryDTO createCategory(CategoryDTO categoryDTO){
        return categoryMapper.toCategoryDTO(categoryRepository.save(categoryMapper.toCategory(categoryDTO)));
    }
    public void deleteCategoryById(Long id){
        categoryRepository.deleteById(id);
    }
    public CategoryDTO updateCategory(CategoryDTO categoryDTO){
        return categoryMapper.toCategoryDTO(categoryRepository.save(categoryMapper.toCategory(categoryDTO)));
    }
    public CategoryDTO getCategoryByName(String name){
        return categoryMapper.toCategoryDTO(categoryRepository.findByName(name));
    }

}
