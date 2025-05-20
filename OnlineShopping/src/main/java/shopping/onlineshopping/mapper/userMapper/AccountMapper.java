package shopping.onlineshopping.mapper.userMapper;

import org.mapstruct.Mapper;
import shopping.onlineshopping.dto.userDTO.AccountDTO;
import shopping.onlineshopping.modal.user.Account;

@Mapper(componentModel = "spring")
public interface AccountMapper {
        AccountDTO toDTO(Account account);
        Account toEntity(AccountDTO dto);
    }


