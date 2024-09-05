package com.solana.com.service;

import com.solana.com.dao.AccountRepository;
import com.solana.com.dao.RoleMappingRepository;
import com.solana.com.model.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final AccountRepository accountRepository;
    private final RoleMappingRepository roleMappingRepository;

    public CustomUserDetailsService(AccountRepository accountRepository, RoleMappingRepository roleMappingRepository) {
        this.accountRepository = accountRepository;
        this.roleMappingRepository = roleMappingRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = accountRepository.findById(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));

        // Lấy danh sách quyền (roles) từ cơ sở dữ liệu
        List<GrantedAuthority> authorities = roleMappingRepository.findByUsername(username)
                .stream()
                .map(roleMapping -> new SimpleGrantedAuthority("ROLE_" + roleMapping.getRole().getName())) // Thêm prefix "ROLE_"
                .collect(Collectors.toList());

        return new org.springframework.security.core.userdetails.User(
                account.getUsername(),
                account.getPassword(),
                authorities // Gán quyền cho tài khoản
        );
    }
}
