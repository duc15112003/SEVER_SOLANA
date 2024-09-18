package com.solana.com.dto;

import com.solana.com.model.Admin;
import com.solana.com.model.Users;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AccountDTO {
    private String username;
    private String password;
    private Users user;
    private Admin admin;
}