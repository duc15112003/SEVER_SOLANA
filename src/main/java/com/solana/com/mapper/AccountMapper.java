package com.solana.com.mapper;

import com.solana.com.dto.AccountDTO;
import com.solana.com.model.Account;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring",uses = {UsersMapper.class, AdminMapper.class})
public interface AccountMapper {

//    @Mapping(source = "user", target = "user")
//    @Mapping(source = "admin", target = "admin")
    AccountDTO toAccountDTO(Account account);
//
//    @Mapping(source = "user", target = "user")
//    @Mapping(source = "admin", target = "admin")
    Account toAccount(AccountDTO accountDTO);
}
