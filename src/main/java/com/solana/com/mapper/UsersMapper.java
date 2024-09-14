package com.solana.com.mapper;

import com.solana.com.dto.UsersDTO;
import com.solana.com.model.Users;
import org.mapstruct.Mapper;

import static org.mapstruct.ReportingPolicy.WARN;

@Mapper(componentModel = "spring",unmappedSourcePolicy = WARN,uses = {AccountMapper.class})
public interface UsersMapper {
    Users toUsers(UsersDTO usersDTO);

    UsersDTO toUsersDTO(Users users);
}
