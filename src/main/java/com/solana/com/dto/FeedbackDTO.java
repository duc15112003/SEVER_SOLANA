package com.solana.com.dto;

import lombok.*;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FeedbackDTO {
    private Long id;
    private String feedback;
    private LocalDateTime createAt;
    private String status;
    private Integer rate;
    private String accountUsername;
    private Long ideaId;
    private Set<Long> reportIds;
}
