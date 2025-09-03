package shopping.onlineshopping.mapper.usermapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;
import shopping.onlineshopping.dto.userdto.SupplierDTO;
import shopping.onlineshopping.modal.Region;
import shopping.onlineshopping.modal.user.Supplier;

@Mapper(componentModel = "spring")
public interface SupplierMapper {
    @Mappings({
            @Mapping(source = "supplierId", target = "supplierId"),
            @Mapping(source = "companyName", target = "companyName"),
            @Mapping(source = "contactName", target = "contactName"),
            @Mapping(source = "contactTitle", target = "contactTitle"),
            @Mapping(source = "address", target = "address"),
            @Mapping(source = "city", target = "city"),
            @Mapping(source = "region", target = "region"),
            @Mapping(source = "country", target = "country"),
            @Mapping(source = "postalCode", target = "postalCode"),
            @Mapping(source = "phone", target = "phone"),
            @Mapping(source = "fax", target = "fax"),
            @Mapping(source = "homePage", target = "homePage")
    })
    SupplierDTO toSupplierDTO(Supplier supplier);
    @Mappings({
            @Mapping(source = "supplierId", target = "supplierId"),
            @Mapping(source = "companyName", target = "companyName"),
            @Mapping(source = "contactName", target = "contactName"),
            @Mapping(source = "contactTitle", target = "contactTitle"),
            @Mapping(source = "address", target = "address"),
            @Mapping(source = "city", target = "city"),
            @Mapping(source = "region", target = "region"),
            @Mapping(source = "country", target = "country"),
            @Mapping(source = "postalCode", target = "postalCode"),
            @Mapping(source = "phone", target = "phone"),
            @Mapping(source = "fax", target = "fax"),
            @Mapping(source = "homePage", target = "homePage")
    })
    Supplier toSupplier(SupplierDTO supplierDTO);
}
