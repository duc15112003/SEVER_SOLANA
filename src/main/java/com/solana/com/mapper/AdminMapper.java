package com.solana.com.mapper;

import com.solana.com.dto.AdminDTO;
import com.solana.com.model.Admin;
import org.mapstruct.Mapper;

import static org.mapstruct.ReportingPolicy.WARN;

@Mapper(componentModel = "spring",unmappedSourcePolicy = WARN)
public interface AdminMapper {

    Admin toAdmin(AdminDTO adminDTO);

    AdminDTO toAdminDTO(Admin admin);
}
