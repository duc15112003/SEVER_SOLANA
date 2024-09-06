package com.solana.com.mapper;

import com.solana.com.dto.AccountDTO;
import com.solana.com.model.Account;
import com.solana.com.model.Admin;
import com.solana.com.model.Users;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface AccountMapper {
     AccountMapper INSTANCE = Mappers.getMapper(AccountMapper.class);
    @Mapping(target = "user",ignore = true)
    @Mapping(target = "admin",ignore = true)
    Account toAccount(AccountDTO accountDTO);

    @Mapping(target = "user",qualifiedByName="userToLong")
    @Mapping(target = "admin",qualifiedByName = "adminToLong")

    AccountDTO toAccountDTO(Account account);

    @Named("userToLong")
    default Long userToLong(Users u){
        return u==null?null:u.getId();
    }
    @Named("adminToLong")
    default Long adminToLong(Admin a){
        return a==null?null:a.getId();
    }
}
