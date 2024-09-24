package com.solana.com.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Table(name = "account")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Account {
    @Id
    @Column(name = "username", nullable = false, length = 50)
    private String username;

    @Column(name = "password", nullable = false, length = 255)
    private String password;

    @Column(name = "public_key", length = 255)
    private String publicKey;

    @Column(name = "private_key", length = 255)
    private String privateKey;

    @OneToOne
    @JoinColumn(name = "id_user")
    private Users user;

    @OneToOne
    @JoinColumn(name = "id_admin")
    private Admin admin;

    @OneToMany(mappedBy = "account")
    private Set<RoleMapping> roleMappings;

    @OneToMany(mappedBy = "account")
    private Set<Feedback> feedbacks;

    @OneToMany(mappedBy = "account")
    private Set<Transaction> transactions;

}
