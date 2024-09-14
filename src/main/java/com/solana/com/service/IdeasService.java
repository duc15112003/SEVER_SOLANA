package com.solana.com.service;

import com.solana.com.dao.IdeasRepository;
import com.solana.com.dto.IdeasDTO;
import com.solana.com.mapper.IdeasMapper;
import com.solana.com.model.Ideas;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class IdeasService {
    @Autowired
    IdeasRepository ideasRepository;
    @Autowired
    IdeasMapper ideasMapper;

    public Page<IdeasDTO> getAll(PageRequest pageRequest) {
        Page<Ideas> ideaPage = ideasRepository.findAll(pageRequest);
        List<IdeasDTO> ideaDTOlist = new ArrayList<IdeasDTO>();
        for (Ideas idea : ideaPage.getContent()) {
            IdeasDTO ideaDTO = ideasMapper.toIdeasDTO(idea);
            ideaDTOlist.add(ideaDTO);
        }
        return new PageImpl<>(ideaDTOlist, ideaPage.getPageable(), ideaPage.getTotalElements());
    }

    public IdeasDTO getIdeasById(Long id) {
        Optional<Ideas> ideas = ideasRepository.findById(id);
        return ideas.map(value -> ideasMapper.toIdeasDTO(ideas.get())).orElse(null);
    }

    public IdeasDTO save(IdeasDTO ideasDTO) {
        ideasDTO.setId(null);
        Ideas idea = ideasMapper.toIdeas(ideasDTO);
        idea.setCreateAt(LocalDateTime.now());
        return ideasMapper.toIdeasDTO(ideasRepository.save(idea));
    }

    public IdeasDTO update(IdeasDTO ideasDTO) {
        Ideas idea = ideasMapper.toIdeas(ideasDTO);
        Optional<Ideas> ideasOptional = ideasRepository.findById(idea.getId());
        if (ideasOptional.isPresent()) {
            idea.setCreateAt(ideasOptional.get().getCreateAt());
            return ideasMapper.toIdeasDTO(ideasRepository.save(idea));
        }else{
            return null;
        }

    }

    public boolean delete(Long id) {
        if (ideasRepository.findById(id).isPresent()) {
            ideasRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

}
