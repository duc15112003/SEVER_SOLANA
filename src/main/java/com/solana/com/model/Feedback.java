package com.solana.com.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table
@Data
public class Feedback {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "feedback", columnDefinition = "nvarchar(max)")
    private String feedback;

    @Column(name = "createAt")
    private LocalDateTime createAt;

    @Column(name = "status", length = 10)
    private String status;

    @Column(name = "rate")
    private Integer rate;

    @ManyToOne
    @JoinColumn(name = "username",nullable = false)
    private Account account;

    @ManyToOne
    @JoinColumn(name = "id_idea",nullable = false)
    private Ideas idea;

    @OneToMany(mappedBy = "feedback")
    private Set<Report> reports;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public LocalDateTime getCreateAt() {
        return createAt;
    }

    public void setCreateAt(LocalDateTime createAt) {
        this.createAt = createAt;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getRate() {
        return rate;
    }

    public void setRate(Integer rate) {
        this.rate = rate;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Ideas getIdea() {
        return idea;
    }

    public void setIdea(Ideas idea) {
        this.idea = idea;
    }

    public Set<Report> getReports() {
        return reports;
    }

    public void setReports(Set<Report> reports) {
        this.reports = reports;
    }
}
