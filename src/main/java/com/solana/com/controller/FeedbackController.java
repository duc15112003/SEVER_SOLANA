package com.solana.com.controller;

import com.solana.com.dto.AdminDTO;
import com.solana.com.dto.FeedbackDTO;
import com.solana.com.respone.ApiResponse;
import com.solana.com.service.FeedbackService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/feedback")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class FeedbackController {
    @Autowired
    FeedbackService feedbackService;

    @GetMapping("/all")
    public ResponseEntity<ApiResponse<PagedModel<EntityModel<FeedbackDTO>>>> getAllFeedback(@RequestParam(value = "page", defaultValue = "0") int page,
                                                                                         @RequestParam(value = "size", defaultValue = "10") int size,
                                                                                         PagedResourcesAssembler<FeedbackDTO> assembler) {
        Page<FeedbackDTO> feedbacks = feedbackService.getAll(PageRequest.of(page,size));
        HttpStatus status;
        ApiResponse<PagedModel<EntityModel<FeedbackDTO>>> response;
        if (feedbacks.hasContent()) {
            status = HttpStatus.OK;
            response = ApiResponse.<PagedModel<EntityModel<FeedbackDTO>>>builder()
                    .code(status.value())
                    .message("Successfully find all feedbacks")
                    .result(assembler.toModel(feedbacks))
                    .build();
        } else {
            status = HttpStatus.NOT_FOUND;
            response = ApiResponse.<PagedModel<EntityModel<FeedbackDTO>>>builder()
                    .code(status.value())
                    .message("Fail to find all accounts")
                    .result(null)
                    .build();
        }
        return ResponseEntity.status(status).body(response);
    }

    @PostMapping("/create")
    public ResponseEntity<ApiResponse<FeedbackDTO>> createFeedback(@RequestBody FeedbackDTO feedbackDTO) {
        FeedbackDTO feedbackD = feedbackService.save(feedbackDTO);
        ApiResponse<FeedbackDTO> response;
        HttpStatus status;
        if (feedbackD != null) {
            status = HttpStatus.CREATED;
            response = ApiResponse.<FeedbackDTO>builder()
                    .code(status.value())
                    .message("Successfully created feedbacks")
                    .result(feedbackD)
                    .build();
        } else {
            status = HttpStatus.BAD_REQUEST;
            response = ApiResponse.<FeedbackDTO>builder()
                    .code(HttpStatus.BAD_REQUEST.value())
                    .message("Failed to create feedback")
                    .result(null)
                    .build();
        }
        return ResponseEntity.status(status).body(response);
    }

    @PutMapping("/update")
    public ResponseEntity<ApiResponse<FeedbackDTO>> updateFeedback(@RequestBody FeedbackDTO feedbackDTO) {
        FeedbackDTO updatedFeedback = feedbackService.update(feedbackDTO);

        ApiResponse<FeedbackDTO> response;
        HttpStatus status;

        if (updatedFeedback != null) {
            status = HttpStatus.OK;
            response = ApiResponse.<FeedbackDTO>builder()
                    .code(status.value())
                    .message("Successfully updated feedback")
                    .result(updatedFeedback)
                    .build();
        } else {
            status = HttpStatus.BAD_REQUEST;
            response = ApiResponse.<FeedbackDTO>builder()
                    .code(status.value())
                    .message("Feedback not found")
                    .result(null)
                    .build();
        }
        return ResponseEntity.status(status).body(response);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse<String>> deleteFeedback(@PathVariable Long id) {
        boolean idDeleted = feedbackService.delete(id);
        HttpStatus status;
        ApiResponse<String> response;
        if (idDeleted) {
            status = HttpStatus.NO_CONTENT;
            response = ApiResponse.<String>builder()
                    .code(status.value())
                    .message("Successfully deleted feedback")
                    .result(null)
                    .build();
        } else {
            status = HttpStatus.NOT_FOUND;
            response = ApiResponse.<String>builder()
                    .code(status.value())
                    .message("Fail to delete feedback")
                    .result(null)
                    .build();
        }
        return ResponseEntity.status(status).body(response);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<ApiResponse<FeedbackDTO>> getFeedbackById(@PathVariable Long id) {
        FeedbackDTO feedbackDTO = feedbackService.getFeedbackById(id);
        HttpStatus status;
        ApiResponse<FeedbackDTO> response;
        if (feedbackDTO != null) {
            status = HttpStatus.OK;
            response = ApiResponse.<FeedbackDTO>builder()
                    .code(status.value())
                    .message("Successfully to finded feedback")
                    .result(feedbackDTO)
                    .build();
        } else {
            status = HttpStatus.NOT_FOUND;
            response = ApiResponse.<FeedbackDTO>builder()
                    .code(status.value())
                    .message("Fail to find feedback")
                    .result(null)
                    .build();
        }
        return ResponseEntity.status(status).body(response);
    }
}
