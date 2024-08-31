package com.solana.com.service;

import com.solana.com.dao.FeedbackRepository;
import com.solana.com.dto.FeedbackDTO;
import com.solana.com.mapper.FeedbackMapper;
import com.solana.com.model.Feedback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service

public class FeedbackService {

    @Autowired
    private FeedbackRepository feedbackRepository;

    public List<FeedbackDTO> getAllFeedback() {
        List<Feedback> feedbackList = feedbackRepository.findAll();
        List<FeedbackDTO> feedbackDTOs = new ArrayList<FeedbackDTO>();
        for (Feedback feedback : feedbackList) {
            FeedbackDTO feedbackDTO = FeedbackMapper.INSTANCE.toFeedbackDTO(feedback);
            feedbackDTOs.add(feedbackDTO);
        }
        return feedbackDTOs;
    }

    public FeedbackDTO getFeedbackById(Long id) {
        Feedback feedback = feedbackRepository.findById(id).get();
        FeedbackDTO feedbackDTO = FeedbackMapper.INSTANCE.toFeedbackDTO(feedback);
        return feedbackDTO;
    }

    public FeedbackDTO save(FeedbackDTO feedbackDTO) {
        Feedback feedback = FeedbackMapper.INSTANCE.toFeedback(feedbackDTO);
        feedbackRepository.save(feedback);
        return feedbackDTO;
    }

    public FeedbackDTO update(FeedbackDTO feedbackDTO) {
        Feedback feedback = FeedbackMapper.INSTANCE.toFeedback(feedbackDTO);
        feedback.setCreateAt(LocalDate.now());
        return FeedbackMapper.INSTANCE.toFeedbackDTO(feedbackRepository.save(feedback));
    }

    public void delete(Long id) {
        feedbackRepository.deleteById(id);
    }
}
}
