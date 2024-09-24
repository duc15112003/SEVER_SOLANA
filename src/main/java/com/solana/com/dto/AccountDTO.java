package com.solana.com.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class AccountDTO {
    private String username;
    private String password;
    private UsersDTO user;
    private AdminDTO admin;
    private String publicKey;
    private String privateKey;

}