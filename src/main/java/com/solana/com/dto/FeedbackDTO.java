package com.solana.com.dto;


import java.util.Set;

public class FeedbackDTO {
    private Long id;
    private String feedback;
    private String createAt;
    private String status;
    private Integer rate;
    private String account;
    private Long ideaId;

    public FeedbackDTO() {
    }

    public FeedbackDTO(Long id, String feedback, String createAt, String status, Integer rate, String account, Long ideaId) {
        this.id = id;
        this.feedback = feedback;
        this.createAt = createAt;
        this.status = status;
        this.rate = rate;
        this.account = account;
        this.ideaId = ideaId;
    }

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

    public String getCreateAt() {
        return createAt;
    }

    public void setCreateAt(String createAt) {
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

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public Long getIdeaId() {
        return ideaId;
    }

    public void setIdeaId(Long ideaId) {
        this.ideaId = ideaId;
    }

    @Override
    public String toString() {
        return "FeedbackDTO{" +
                "id=" + id +
                ", feedback='" + feedback + '\'' +
                ", createAt='" + createAt + '\'' +
                ", status='" + status + '\'' +
                ", rate=" + rate +
                ", account='" + account + '\'' +
                ", ideaId=" + ideaId +
                '}';
    }
}
