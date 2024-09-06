package com.solana.com.dto;

import com.solana.com.model.Admin;
import com.solana.com.model.Users;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class AccountDTO {
    private String username;
    private String password;
    private Users user;
    private Admin admin;
}