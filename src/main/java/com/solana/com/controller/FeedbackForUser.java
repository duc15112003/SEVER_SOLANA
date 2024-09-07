package com.solana.com.controller;

import com.solana.com.dto.FeedbackDTO;
import com.solana.com.model.Feedback;
import com.solana.com.model.Ideas;
import com.solana.com.respone.ApiResponse;
import com.solana.com.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("api/user/feedback")
@RestController
@CrossOrigin("*")
public class FeedbackForUser {
    @Autowired
    private FeedbackService feedbackService;

    @GetMapping("/get")
    public ResponseEntity<ApiResponse<List<FeedbackDTO>>> getAllForUser(@RequestParam("username") String username) {
        ApiResponse<List<FeedbackDTO>> response = new ApiResponse<>();
        List<FeedbackDTO> list = feedbackService.findByUsername(username);
        response.setCode(HttpStatus.OK.value());
        response.setMessage("get All successfuly");
        response.setResult(list);
        return ResponseEntity.ok(response);
    }
}
