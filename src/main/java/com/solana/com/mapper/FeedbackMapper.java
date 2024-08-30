package com.solana.com.mapper;

import com.solana.com.dto.FeedbackDTO;
import com.solana.com.model.Feedback;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.sql.Timestamp;
import java.time.LocalDateTime;


@Mapper(componentModel = "spring")
public interface FeedbackMapper {
    @Mapping(source = "createAt", target = "createAt", qualifiedByName = "localDateTimeToTimestamp")
    Feedback toFeedback(FeedbackDTO feedbackDTO);
    @Mapping(source = "createAt", target = "createAt", qualifiedByName = "timestampToLocalDateTime")
    FeedbackDTO toFeedbackDTO(Feedback feedback);

    @Named("localDateTimeToTimestamp")
    default Timestamp localDateTimeToTimestamp(LocalDateTime localDateTime) {
        return localDateTime == null ? null : Timestamp.valueOf(localDateTime);
    }

    @Named("timestampToLocalDateTime")
    default LocalDateTime timestampToLocalDateTime(Timestamp timestamp) {
        return timestamp == null ? null : timestamp.toLocalDateTime();
    }
}
