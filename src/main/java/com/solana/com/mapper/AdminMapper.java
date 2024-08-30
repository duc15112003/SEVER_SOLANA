package com.solana.com.mapper;

import com.solana.com.dto.AccountDTO;
import com.solana.com.dto.AdminDTO;
import com.solana.com.model.Account;
import com.solana.com.model.Admin;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface AdminMapper {

    Admin toAdmin(AdminDTO adminDTO);

    AdminDTO toAdminDTO(Admin admin);
}
