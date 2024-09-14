package com.solana.com.mapper;

import com.solana.com.dto.IdeasDTO;
import com.solana.com.model.Ideas;
import org.mapstruct.Mapper;

import static org.mapstruct.ReportingPolicy.WARN;

@Mapper(componentModel = "spring",unmappedSourcePolicy = WARN,uses = {AccountMapper.class})
public interface IdeasMapper {

    Ideas toIdeas(IdeasDTO ideasDTO);

    IdeasDTO toIdeasDTO(Ideas ideas);
}
