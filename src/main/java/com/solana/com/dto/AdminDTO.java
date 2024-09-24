package com.solana.com.dto;

import jakarta.persistence.Column;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class AdminDTO {
    private Long id;
    private String firstname;
    private String lastname;
    private String avatar;
    private LocalDate birthday;
    private String address;
    private String email;
    private String position;
    private String salary;
    private String phoneNumber;
    private String publicKey;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;
    private LocalDateTime deleteAt;

}
