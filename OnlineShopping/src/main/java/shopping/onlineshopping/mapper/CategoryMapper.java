package shopping.onlineshopping.mapper;

import org.mapstruct.*;
import shopping.onlineshopping.dto.CategoryDTO;
import shopping.onlineshopping.modal.Category;
import shopping.onlineshopping.modal.Region;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    @Mapping(source = "region.regionId", target = "regionId")
    CategoryDTO toCategoryDTO(Category category);

    @Mapping(source = "regionId", target = "region")
    Category toCategory(CategoryDTO dto);

    default Region map(Long regionId) {
        if (regionId == null) return null;
        Region region = new Region();
        region.setRegionId(regionId);
        return region;
    }
}



