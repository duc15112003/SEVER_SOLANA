package com.solana.com.mapper;

import com.solana.com.dto.AccountDTO;
import com.solana.com.model.Account;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface AccountMapper {
    AccountMapper INSTANCE = Mappers.getMapper(AccountMapper.class);

    @Mapping(source = "user", target = "user")
    @Mapping(source = "admin", target = "admin")
    AccountDTO toAccountDTO(Account account);

    @Mapping(source = "user", target = "user")
    @Mapping(source = "admin", target = "admin")
    Account toAccount(AccountDTO accountDTO);
}
