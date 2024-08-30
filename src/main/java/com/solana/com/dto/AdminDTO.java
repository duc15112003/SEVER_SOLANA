package com.solana.com.dto;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AdminDTO {
    private Long id;
    private String firstname;
    private String lastname;
    private String avatar;
    private LocalDate birthday;
    private String address;
    private String phoneNumber;
    private String publicKey;
    private LocalDate createAt;

    @Override
    public String toString() {
        return "AdminDTO{" +
                "id=" + id +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", avatar='" + avatar + '\'' +
                ", birthday=" + birthday +
                ", address='" + address + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", publicKey='" + publicKey + '\'' +
                ", createAt=" + createAt +
                '}';
    }
}
