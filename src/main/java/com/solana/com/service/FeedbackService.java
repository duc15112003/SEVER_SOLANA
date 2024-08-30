package com.solana.com.service;

import com.solana.com.dao.FeedbackRepository;
import com.solana.com.dto.FeedbackDTO;
import com.solana.com.mapper.FeedbackMapper;
import com.solana.com.model.Feedback;
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
        return  feedback.map(value->feedbackMapper.toFeedbackDTO(feedback.get())).orElse(null) ;
    }

    public FeedbackDTO save(FeedbackDTO FeedbackDTO) {
        Feedback feedback = feedbackMapper.toFeedback(FeedbackDTO);
        feedback.setCreateAt(Timestamp.valueOf(LocalDateTime.now()));
        System.out.println(FeedbackDTO.toString());
        System.out.println(feedback.toString());
        return feedbackMapper.toFeedbackDTO(feedbackRepository.save(feedback));
    }

    public FeedbackDTO update(FeedbackDTO FeedbackDTO) {
        Feedback feedback = feedbackMapper.toFeedback(FeedbackDTO);
        System.out.println(feedback.toString());
        System.out.println(FeedbackDTO);
        return feedbackMapper.toFeedbackDTO(feedbackRepository.save(feedback));
    }

    public boolean delete(Long id) {
        if(feedbackRepository.findById(id).isPresent()){
            feedbackRepository.deleteById(id);
            return true;
        }else{
            return false;
        }
    }
}
