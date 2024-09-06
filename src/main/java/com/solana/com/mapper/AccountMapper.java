package com.solana.com.mapper;

import com.solana.com.dto.AccountDTO;
import com.solana.com.model.Account;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;


@Mapper(componentModel = "spring")
public interface AccountMapper {
    AccountMapper INSTANCE = Mappers.getMapper(AccountMapper.class);
    Account toAccount(AccountDTO accountDTO);
    AccountDTO toAccountDTO(Account account);
}
