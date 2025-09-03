package shopping.onlineshopping.mapper.usermapper;

import org.mapstruct.Mapper;
import shopping.onlineshopping.dto.userdto.CustomerDTO;
import shopping.onlineshopping.modal.user.Customer;

@Mapper(componentModel = "spring")
public interface CustomerMapper {
    CustomerDTO toCustomerDTO(Customer customer);
    Customer toCustomer(CustomerDTO customerDTO);
}
