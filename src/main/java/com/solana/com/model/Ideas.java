package com.solana.com.model;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Ideas {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", nullable = false, length = 100)
    private String title;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "status", nullable = false, length = 10)
    private String status;

    @Column(name = "image", nullable = false, length = 255)
    private String image;

    @Column(name = "CreateAt", nullable = false)
    private LocalDateTime createAt;

    @Column(name = "updateAt")
    private LocalDateTime updateAt;

    @Column(name = "endAt", nullable = false)
    private LocalDateTime endAt;

    @Column(name = "count_feedback")
    private Long countFeedback;

    @Column(name = "award_for_one_feedback", precision = 18, scale = 8)
    private Long awardForOneFeedback;

    @ManyToOne
    @JoinColumn(name = "username", nullable = false)
    private Account account;

    @OneToMany(mappedBy = "idea")
    private Set<Feedback> feedbacks;

    @OneToMany(mappedBy = "idea")
    private Set<Report> reports;

    @OneToMany(mappedBy = "idea")
    private Set<IdeaCategoryMapping> ideaCategoryMappings;

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

    public LocalDateTime getCreateAt() {
        return createAt;
    }

    public void setCreateAt(LocalDateTime createAt) {
        this.createAt = createAt;
    }

    public LocalDateTime getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(LocalDateTime updateAt) {
        this.updateAt = updateAt;
    }

    public LocalDateTime getEndAt() {
        return endAt;
    }

    public void setEndAt(LocalDateTime endAt) {
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

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Set<Feedback> getFeedbacks() {
        return feedbacks;
    }

    public void setFeedbacks(Set<Feedback> feedbacks) {
        this.feedbacks = feedbacks;
    }

    public Set<Report> getReports() {
        return reports;
    }

    public void setReports(Set<Report> reports) {
        this.reports = reports;
    }

    public Set<IdeaCategoryMapping> getIdeaCategoryMappings() {
        return ideaCategoryMappings;
    }

    public void setIdeaCategoryMappings(Set<IdeaCategoryMapping> ideaCategoryMappings) {
        this.ideaCategoryMappings = ideaCategoryMappings;
    }
}
