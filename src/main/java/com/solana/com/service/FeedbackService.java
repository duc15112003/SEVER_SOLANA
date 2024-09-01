package com.solana.com.service;

import com.solana.com.dao.AccountRepository;
import com.solana.com.dao.FeedbackRepository;
import com.solana.com.dao.IdeasRepository;
import com.solana.com.dto.FeedbackDTO;
import com.solana.com.mapper.FeedbackMapper;
import com.solana.com.model.Feedback;
import com.solana.com.util.FormatDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    public List<FeedbackDTO> getAll() {
        List<Feedback> feedbackList = feedbackRepository.findAll();
        List<FeedbackDTO> feedbackDTOlist = new ArrayList<FeedbackDTO>();
        for (Feedback acc : feedbackList) {
            FeedbackDTO accDTO = feedbackMapper.toFeedbackDTO(acc);
            feedbackDTOlist.add(accDTO);
        }
        return feedbackDTOlist;
    }

    public FeedbackDTO getFeedbackById(Long id) {
        Optional<Feedback> feedback = feedbackRepository.findById(id);
        return feedback.map(value -> feedbackMapper.toFeedbackDTO(feedback.get())).orElse(null);
    }

    public FeedbackDTO save(FeedbackDTO feedbackDTO) {
        Feedback feedback = feedbackMapper.toFeedback(feedbackDTO);
        feedback.setCreateAt(Timestamp.valueOf(LocalDateTime.now()));
        feedback.setAccount(accountRepository.findById(feedbackDTO.getAccount()).orElse(null));
        feedback.setIdea(ideasRepository.findById(feedbackDTO.getIdeaId()).orElse(null));
        if (feedback.getAccount() == null || feedback.getIdea() == null) {
            return null;
        } else {
            return feedbackMapper.toFeedbackDTO(feedbackRepository.save(feedback));
        }
    }

    public FeedbackDTO update(FeedbackDTO feedbackDTO) {
        Feedback feedback = feedbackMapper.toFeedback(feedbackDTO);
        feedback.setAccount(accountRepository.findById(feedbackDTO.getAccount()).orElse(null));
        feedback.setIdea(ideasRepository.findById(feedbackDTO.getIdeaId()).orElse(null));
        if (feedback.getAccount() == null || feedback.getIdea() == null) {
            return null;
        } else {
            return feedbackMapper.toFeedbackDTO(feedbackRepository.save(feedback));
        }
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
