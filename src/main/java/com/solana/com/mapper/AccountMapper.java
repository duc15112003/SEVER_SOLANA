package com.solana.com.mapper;

import com.solana.com.dto.AccountDTO;
import com.solana.com.model.Account;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AccountMapper {
    Account toAccount(AccountDTO accountDTO);

    AccountDTO toAccountDTO(Account account);
}
