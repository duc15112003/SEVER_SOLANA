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

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class IdeasService {
    @Autowired
    private IdeasRepository ideasRepository;

    @Autowired
    private IdeasMapper ideasMapper;

    public Page<IdeasDTO> getAll(PageRequest pageRequest) {
        Page<Ideas> ideaPage = ideasRepository.findAll(pageRequest);
        List<IdeasDTO> ideaDTOlist = ideaPage.getContent().stream()
                .map(ideasMapper::toIdeasDTO)
                .collect(Collectors.toList());
        return new PageImpl<>(ideaDTOlist, pageRequest, ideaPage.getTotalElements());
    }

    public IdeasDTO getIdeasById(Long id) {
        return ideasRepository.findById(id)
                .map(ideasMapper::toIdeasDTO)
                .orElse(null); // Consider throwing a custom exception if not found
    }

    public IdeasDTO save(IdeasDTO ideasDTO) {
        // Ensure id is null for new entities
        if (ideasDTO.getId() != null) {
            throw new IllegalArgumentException("ID should be null for new ideas");
        }

        // Convert DTO to entity and set creation date
        Ideas idea = ideasMapper.toIdeas(ideasDTO);
        idea.setCreatedAt(LocalDate.now());

        // Save the entity and convert back to DTO
        return ideasMapper.toIdeasDTO(ideasRepository.save(idea));
    }

    public IdeasDTO update(IdeasDTO ideasDTO) {
        if (ideasDTO.getId() == null) {
            throw new IllegalArgumentException("ID must be provided for update");
        }

        // Find the existing idea
        Optional<Ideas> existingIdeaOptional = ideasRepository.findById(ideasDTO.getId());
        if (existingIdeaOptional.isPresent()) {
            Ideas existingIdea = existingIdeaOptional.get();
            Ideas updatedIdea = ideasMapper.toIdeas(ideasDTO);

            // Preserve the original creation date
            updatedIdea.setCreatedAt(existingIdea.getCreatedAt());

            return ideasMapper.toIdeasDTO(ideasRepository.save(updatedIdea));
        }

        return null; // Consider throwing a custom exception if not found
    }

    public boolean delete(Long id) {
        if (ideasRepository.existsById(id)) {
            ideasRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
