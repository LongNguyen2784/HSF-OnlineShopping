package shopping.onlineshopping.mapper;

import org.mapstruct.Mapper;
import shopping.onlineshopping.dto.CategoryDTO;
import shopping.onlineshopping.modal.Category;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    CategoryDTO toCategoryDTO(Category category);
    Category toCategory(CategoryDTO categoryDTO);
}
