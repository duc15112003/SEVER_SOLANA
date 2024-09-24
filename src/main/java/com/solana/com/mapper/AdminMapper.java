package com.solana.com.mapper;

import com.solana.com.dto.AdminDTO;
import com.solana.com.model.Admin;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import static org.mapstruct.ReportingPolicy.WARN;

@Mapper(componentModel = "spring")
public interface AdminMapper {
    // Customize this as needed
    Admin toAdmin(AdminDTO adminDTO);
    // Customize this as needed
    AdminDTO toAdminDTO(Admin admin);
}
