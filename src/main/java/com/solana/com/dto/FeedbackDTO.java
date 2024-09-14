package com.solana.com.dto;

import lombok.*;

import java.time.LocalDateTime;

@Data
public class FeedbackDTO {
    private Long id;
    private String feedback;
    private LocalDateTime createAt;
    private String status;
    private Integer rate;
    private AccountDTO account;
    private IdeasDTO idea;

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

    public AccountDTO getAccount() {
        return account;
    }

    public void setAccount(AccountDTO account) {
        this.account = account;
    }

    public IdeasDTO getIdea() {
        return idea;
    }

    public void setIdea(IdeasDTO idea) {
        this.idea = idea;
    }
}
