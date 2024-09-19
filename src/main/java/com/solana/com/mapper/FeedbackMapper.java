package com.solana.com.mapper;

import com.solana.com.dto.FeedbackDTO;
import com.solana.com.model.Feedback;
import org.mapstruct.*;

import static org.mapstruct.ReportingPolicy.WARN;

@Mapper(componentModel = "spring",unmappedSourcePolicy = WARN,uses = {AccountMapper.class,IdeasMapper.class})
public interface FeedbackMapper {

    Feedback toFeedback(FeedbackDTO feedbackDTO);
    FeedbackDTO toFeedbackDTO(Feedback feedback);
}
