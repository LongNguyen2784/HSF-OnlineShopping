package shopping.onlineshopping.mapper.userMapper;

import org.mapstruct.Mapper;
import shopping.onlineshopping.dto.userDTO.CustomerDTO;
import shopping.onlineshopping.modal.user.Customer;

@Mapper(componentModel = "spring")
public interface CustomerMapper {
    CustomerDTO toCustomerDTO(Customer customer);
    Customer toCustomer(CustomerDTO customerDTO);
}
