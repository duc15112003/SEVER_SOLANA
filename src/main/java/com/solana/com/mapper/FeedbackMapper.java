package com.solana.com.mapper;

import com.solana.com.dto.FeedbackDTO;
import com.solana.com.model.Account;
import com.solana.com.model.Feedback;
import com.solana.com.util.FormatDate;
import org.mapstruct.*;

import java.sql.Timestamp;
import java.util.List;


@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface FeedbackMapper {

    @Mapping(target = "createAt", qualifiedByName = "stringToTimestamp",ignore = true)
    @Mapping(target="account",ignore = true)
    Feedback toFeedback(FeedbackDTO feedbackDTO);

    @Mapping(target = "createAt", qualifiedByName = "timestampToString")
    @Mapping(target="account",qualifiedByName ="accountToString")
    FeedbackDTO toFeedbackDTO(Feedback feedback);

    @Named("stringToTimestamp")
    default Timestamp stringToTimestamp(String string) {
        return string == null ? null : Timestamp.valueOf(string);
    }

    @Named("timestampToString")
    default String timestampToString(Timestamp timestamp) {
        return timestamp == null ? null : FormatDate.FormatTimestampToString(timestamp) ;
    }

    @Named("accountToString")
    default String accountToString(Account account){
        return account.getUsername();
    }


    List<FeedbackDTO> feedbacksToFeedbackDTOs(List<Feedback> feedbacks);

    List<Feedback> feedbackDTOsToFeedbacks(List<FeedbackDTO> feedbackDTOs);
}
