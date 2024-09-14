package com.solana.com.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table
@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "report", columnDefinition = "nvarchar(max)")
    private String report;

    @Column(name = "reply", columnDefinition = "nvarchar(max)")
    private String reply;

    @Column(name = "createAt")
    private LocalDateTime createAt;

    @Column(name = "replyAt")
    private LocalDateTime replyAt;

    @Column(name = "status", length = 10)
    private String status;

    @ManyToOne
    @JoinColumn(name = "username")
    private Account account;

    @ManyToOne
    @JoinColumn(name = "id_idea")
    private Ideas idea;

    @ManyToOne
    @JoinColumn(name = "id_feedback")
    private Feedback feedback;


}
