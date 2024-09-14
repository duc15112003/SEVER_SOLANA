package com.solana.com.service;

import com.solana.com.dao.AdminRepository;
import com.solana.com.dto.AdminDTO;
import com.solana.com.mapper.AdminMapper;
import com.solana.com.model.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AdminService {
    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private AdminMapper adminMapper;

    public Page<AdminDTO> getAll(PageRequest pageRequest) {
        Page<Admin> adminList = adminRepository.findAll(pageRequest);
        List<AdminDTO> adminDTOlist = new ArrayList<AdminDTO>();
        for (Admin adm : adminList) {
            AdminDTO admDTO = adminMapper.toAdminDTO(adm);
            adminDTOlist.add(admDTO);
        }
        return new PageImpl<>(adminDTOlist,adminList.getPageable(),adminList.getTotalElements());
    }

    public AdminDTO getAdminById(Long id) {
        Optional<Admin> admin = adminRepository.findById(id);
        return admin.map(value -> adminMapper.toAdminDTO(value)).orElse(null);
    }

    public AdminDTO save(AdminDTO AdminDTO) {
        Admin admin = adminMapper.toAdmin(AdminDTO);
        admin.setCreateAt(LocalDateTime.now());
        return adminMapper.toAdminDTO(adminRepository.save(admin));
    }

    public AdminDTO update(AdminDTO AdminDTO) {
        Admin admin = adminMapper.toAdmin(AdminDTO);
        return adminMapper.toAdminDTO(adminRepository.save(admin));
    }

    public boolean delete(Long id) {
        if (adminRepository.findById(id).isPresent()) {
            adminRepository.deleteById(id);
            return true;
        }else{
            return false;
        }
    }
}
