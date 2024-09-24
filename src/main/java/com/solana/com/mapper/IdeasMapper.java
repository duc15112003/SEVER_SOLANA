package com.solana.com.mapper;

import com.solana.com.dto.IdeasDTO;
import com.solana.com.model.Ideas;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IdeasMapper {

    // Map the 'account.username' field from Ideas to 'accountUsername' in IdeasDTO
//    @Mapping(source = "account.username", target = "accountUsername")
    IdeasDTO toIdeasDTO(Ideas ideas);

    // Map the IdeasDTO back to the Ideas entity
//    @Mapping(source = "accountUsername", target = "account.username")
    Ideas toIdeas(IdeasDTO ideasDTO);

    // Map a list of Ideas to a list of IdeasDTO
    List<IdeasDTO> toIdeasDTOList(List<Ideas> ideasList);

    // Map a list of IdeasDTO to a list of Ideas
    List<Ideas> toIdeasList(List<IdeasDTO> ideasDTOList);
}
