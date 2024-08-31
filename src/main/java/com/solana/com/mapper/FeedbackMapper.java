package com.solana.com.mapper;

import com.solana.com.dto.FeedbackDTO;
import com.solana.com.model.Feedback;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;


@Mapper(componentModel = "spring")
public interface FeedbackMapper {
    FeedbackMapper INSTANCE = Mappers.getMapper(FeedbackMapper.class);

    Feedback toFeedback(FeedbackDTO feedbackDTO);
    FeedbackDTO toFeedbackDTO(Feedback feedback);

}
