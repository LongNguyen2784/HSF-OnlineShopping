package shopping.onlineshopping.mapper;

import org.mapstruct.Mapper;
import shopping.onlineshopping.dto.SupplierDTO;
import shopping.onlineshopping.modal.Supplier;

@Mapper(componentModel = "spring")
public interface SupplierMapper {
    SupplierDTO toSupplierDTO(Supplier supplier);
    Supplier toSupplier(SupplierDTO supplierDTO);
}
