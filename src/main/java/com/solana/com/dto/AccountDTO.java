package com.solana.com.dto;

import com.solana.com.model.*;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

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