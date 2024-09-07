package com.solana.com.mapper;

import com.solana.com.dto.UsersDTO;
import com.solana.com.model.Users;
import com.solana.com.util.FormatDate;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Mapper(componentModel = "spring")
public interface UsersMapper {
    @Mapping(target = "createAt", qualifiedByName = "stringToTimestamp")
    Users toUsers(UsersDTO usersDTO);

    @Mapping(target = "createAt", qualifiedByName = "timestampToString")
    UsersDTO toUsersDTO(Users users);

    @Named("timestampToString")
    default String timestampToString(Timestamp timestamp) {
        return timestamp == null ? null : FormatDate.formatTimestampToString(timestamp);
    }

    @Named("stringToTimestamp")
    default Timestamp stringToTimestamp(String string) {
        return Timestamp.valueOf(LocalDateTime.now());
    }
}
