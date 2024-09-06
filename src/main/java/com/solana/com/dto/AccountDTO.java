package com.solana.com.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AccountDTO {
    private String username;
    private String password;
    private Long user;
    private Long admin;

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

    public Long getUser() {
        return user;
    }

    public void setUser(Long user) {
        this.user = user;
    }

    public Long getAdmin() {
        return admin;
    }

    public void setAdmin(Long admin) {
        this.admin = admin;
    }

    @Override
    public String toString() {
        return "AccountDTO{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", user=" + user +
                ", admin=" + admin +
                '}';
    }
}