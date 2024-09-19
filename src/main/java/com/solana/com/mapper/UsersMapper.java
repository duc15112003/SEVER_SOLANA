package com.solana.com.mapper;

import com.solana.com.dto.UsersDTO;
import com.solana.com.model.Users;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UsersMapper {
    UsersDTO toUsersDto(Users user);
    @Mapping(source = "createAt",target = "createAt",ignore = true)
    Users toUsers(UsersDTO usersDTO);
}
