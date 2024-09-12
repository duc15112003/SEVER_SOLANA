package com.solana.com.dto;

import lombok.*;

import java.time.LocalDate;



@Builder
public class UsersDTO {
    private Long id;

    private String firstname;

    private String lastname;

    private String avatar;

    private LocalDate birthday;

    private String email;

    private String address;

    private String phoneNumber;

    private String publicKey;

    private String createAt;

    public UsersDTO(Long id, String firstname, String lastname, String avatar, LocalDate birthday, String email, String address, String phoneNumber, String publicKey, String createAt) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.avatar = avatar;
        this.birthday = birthday;
        this.email = email;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.publicKey = publicKey;
        this.createAt = createAt;
    }

    public UsersDTO() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPublicKey() {
        return publicKey;
    }

    public void setPublicKey(String publicKey) {
        this.publicKey = publicKey;
    }

    public String getCreateAt() {
        return createAt;
    }

    public void setCreateAt(String createAt) {
        this.createAt = createAt;
    }
}
