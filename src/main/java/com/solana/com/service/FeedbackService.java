package com.solana.com.service;

import com.solana.com.dao.AccountRepository;
import com.solana.com.dao.FeedbackRepository;
import com.solana.com.dao.IdeasRepository;
import com.solana.com.dto.FeedbackDTO;
import com.solana.com.mapper.FeedbackMapper;
import com.solana.com.model.Feedback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FeedbackService {
    @Autowired
    private FeedbackRepository feedbackRepository;
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private IdeasRepository ideasRepository;

    @Autowired
    private FeedbackMapper feedbackMapper;


    public Page<FeedbackDTO> getAll(PageRequest pageRequest) {
        Page<Feedback> feedbackList = feedbackRepository.findAll(pageRequest);
        List<FeedbackDTO> feedbackDTOlist = new ArrayList<FeedbackDTO>();
        for (Feedback feed : feedbackList.getContent()) {
            FeedbackDTO feedDTO = feedbackMapper.toFeedbackDTO(feed);
            feedbackDTOlist.add(feedDTO);
        }

        return new PageImpl<>(feedbackDTOlist,feedbackList.getPageable(),feedbackList.getTotalElements());
    }

    public FeedbackDTO getFeedbackById(Long id) {
        Optional<Feedback> feedback = feedbackRepository.findById(id);
        return feedback.map(value -> feedbackMapper.toFeedbackDTO(feedback.get())).orElse(null);
    }

    public FeedbackDTO save(FeedbackDTO feedbackDTO) {
        Feedback feedback = feedbackMapper.toFeedback(feedbackDTO);
        feedback.setCreateAt(LocalDateTime.now());
        if (feedback.getAccount() == null || feedback.getIdea() == null) {
            return null;
        } else {
            feedback.setAccount(accountRepository.findById(feedbackDTO.getAccount().getUsername()).orElse(null));
            feedback.setIdea(ideasRepository.findById(feedbackDTO.getIdea().getId()).orElse(null));
            return feedbackMapper.toFeedbackDTO(feedbackRepository.save(feedback));
        }
    }

    public FeedbackDTO update(FeedbackDTO feedbackDTO) {
        Feedback feedback = feedbackMapper.toFeedback(feedbackDTO);
        feedback.setAccount(accountRepository.findById(feedbackDTO.getAccount().getUsername()).orElse(null));
        feedback.setIdea(ideasRepository.findById(feedbackDTO.getIdea().getId()).orElse(null));
        if (feedback.getAccount() == null || feedback.getIdea() == null) {
            return null;
        } else {
            return feedbackMapper.toFeedbackDTO(feedbackRepository.save(feedback));
        }
    }

    public List<FeedbackDTO> findByUsername(String username) {
        // Tìm danh sách Feedback từ repository
        List<Feedback> feedbacks = feedbackRepository.findFeedbackByUsername(username);

        // Chuyển đổi danh sách Feedback thành danh sách FeedbackDTO
        return feedbacks.stream()
                .map(feedback -> feedbackMapper.toFeedbackDTO(feedback))
                .collect(Collectors.toList());
    }

    public boolean delete(Long id) {
        if (feedbackRepository.findById(id).isPresent()) {
            feedbackRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}
