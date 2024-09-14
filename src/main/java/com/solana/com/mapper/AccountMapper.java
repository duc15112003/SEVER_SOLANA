package com.solana.com.mapper;

import com.solana.com.dto.AccountDTO;
import com.solana.com.model.Account;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import static org.mapstruct.ReportingPolicy.WARN;

@Mapper(componentModel = "spring",unmappedSourcePolicy = WARN,uses = {UsersMapper.class,AdminMapper.class})
public interface AccountMapper {
    Account toAccount(AccountDTO accountDTO);

    AccountDTO toAccountDTO(Account account);

}
