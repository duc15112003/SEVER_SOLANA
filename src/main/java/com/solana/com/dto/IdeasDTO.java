package com.solana.com.dto;

import lombok.*;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class IdeasDTO {
    private Long id;
    private String title;
    private String description;
    private String status;
    private String image;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;
    private LocalDateTime endAt;
    private Long countFeedback;
    private Long awardForOneFeedback;
    private String accountUsername; // Chỉ chứa username
}
