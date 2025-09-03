package shopping.onlineshopping.mapper.usermapper;

import org.mapstruct.*;
import shopping.onlineshopping.dto.userdto.AccountDTO;
import shopping.onlineshopping.dto.userdto.RegisterRequest;
import shopping.onlineshopping.dto.userdto.UpdateAccountRequest;
import shopping.onlineshopping.dto.userdto.UserProfileDTO;
import shopping.onlineshopping.modal.user.Account;

@Mapper(componentModel = "spring")
public interface AccountMapper {
    // tạo ở service
    Account fromRegisterRequest(RegisterRequest request);

    AccountDTO toDTO(Account account);
    @Mapping(source = "id", target = "id")
    UserProfileDTO toProfileDTO(Account account);

    @Mapping(target = "fullName", source = "fullName")
    @Mapping(target = "phoneNumber", source = "phoneNumber")
    @Mapping(target = "imageUrl", source = "imageUrl")
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateAccountFromRequest(UpdateAccountRequest request, @MappingTarget Account account);
}
