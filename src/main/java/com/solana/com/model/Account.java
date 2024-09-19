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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    public Set<RoleMapping> getRoleMappings() {
        return roleMappings;
    }

    public void setRoleMappings(Set<RoleMapping> roleMappings) {
        this.roleMappings = roleMappings;
    }

    public Set<Feedback> getFeedbacks() {
        return feedbacks;
    }

    public void setFeedbacks(Set<Feedback> feedbacks) {
        this.feedbacks = feedbacks;
    }

    public Set<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(Set<Transaction> transactions) {
        this.transactions = transactions;
    }
}
