package com.solana.com.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AccountDTO {
    private String username;
    private String password;
    private Long userId;
    private Long adminId;
}