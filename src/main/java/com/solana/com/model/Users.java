package com.solana.com.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "firstname", nullable = false, length = 255)
    private String firstname;

    @Column(name = "lastname", nullable = false, length = 255)
    private String lastname;

    @Column(name = "avatar", nullable = false, length = 255)
    private String avatar;

    @Column(name="email")
    private String email;

    @Column(name = "birthday", nullable = false)
    private LocalDate birthday;

    @Column(name = "address",nullable = true, length = 255)
    private String address;

    @Column(name = "phone_number", nullable = false, length = 13)
    private String phoneNumber;

    @Column(name = "public_key",nullable = true, length = 255)
    private String publicKey;

    @Column(name = "createAt", nullable = false)
    private LocalDateTime createAt;

    @OneToOne(mappedBy = "user")
    private Account accounts;

}
