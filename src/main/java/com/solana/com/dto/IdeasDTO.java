package com.solana.com.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
public class IdeasDTO {
    private Long id;
    private String title;
    private String description;
    private String status;
    private String image;
    private String createAt;
    private String updateAt;
    private String endAt;
    private Long countFeedback;
    private Long awardForOneFeedback;
    private String accountUsername;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getCreateAt() {
        return createAt;
    }

    public void setCreateAt(String createAt) {
        this.createAt = createAt;
    }

    public String getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(String updateAt) {
        this.updateAt = updateAt;
    }

    public String getEndAt() {
        return endAt;
    }

    public void setEndAt(String endAt) {
        this.endAt = endAt;
    }

    public Long getCountFeedback() {
        return countFeedback;
    }

    public void setCountFeedback(Long countFeedback) {
        this.countFeedback = countFeedback;
    }

    public Long getAwardForOneFeedback() {
        return awardForOneFeedback;
    }

    public void setAwardForOneFeedback(Long awardForOneFeedback) {
        this.awardForOneFeedback = awardForOneFeedback;
    }

    public String getAccountUsername() {
        return accountUsername;
    }

    public void setAccountUsername(String accountUsername) {
        this.accountUsername = accountUsername;
    }

    @Override
    public String toString() {
        return "IdeasDTO{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", status='" + status + '\'' +
                ", image='" + image + '\'' +
                ", createAt='" + createAt + '\'' +
                ", updateAt='" + updateAt + '\'' +
                ", endAt='" + endAt + '\'' +
                ", countFeedback=" + countFeedback +
                ", awardForOneFeedback=" + awardForOneFeedback +
                ", accountUsername='" + accountUsername + '\'' +
                '}';
    }
}
