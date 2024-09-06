package com.solana.com.mapper;

import com.solana.com.dto.IdeasDTO;
import com.solana.com.model.Ideas;
import com.solana.com.util.FormatDate;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Mapper(componentModel = "spring")
public interface IdeasMapper {

    @Mapping(target = "createdAt", qualifiedByName = "defaultTimestamp")
    @Mapping(target = "updateAt", qualifiedByName = "defaultTimestamp")
    @Mapping(target = "endAt", qualifiedByName = "defaultTimestamp")
    Ideas toIdeas(IdeasDTO ideasDTO);

    @Mapping(target = "createdAt", qualifiedByName = "timestampToString")
    @Mapping(target = "updateAt", qualifiedByName = "timestampToString")
    @Mapping(target = "endAt", qualifiedByName = "timestampToString")
    IdeasDTO toIdeasDTO(Ideas ideas);

    @Named("defaultTimestamp")
    default Timestamp defaultTimestamp() {
        return Timestamp.valueOf(LocalDateTime.now());
    }

    @Named("timestampToString")
    default String timestampToString(Timestamp timestamp) {
        return timestamp == null ? null : FormatDate.formatTimestampToString(timestamp);
    }

}
