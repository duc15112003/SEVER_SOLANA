package com.solana.com.controller;

import com.solana.com.dto.NotificationDTO;
import com.solana.com.respone.ApiResponse;
import com.solana.com.service.NotificationService;
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

@RestController
@RequestMapping("/api/notifications")
@RequiredArgsConstructor
public class NotificationController {
    @Autowired
    NotificationService notificationService;

    @GetMapping("/all")
    public ResponseEntity<ApiResponse<PagedModel<EntityModel<NotificationDTO>>>> getAllNotification(@RequestParam(value = "page", defaultValue = "0") int page,
                                                                                          @RequestParam(value = "size", defaultValue = "10") int size,
                                                                                          PagedResourcesAssembler<NotificationDTO> assembler) {
        Page<NotificationDTO> notifications = notificationService.getAll(PageRequest.of(page,size));
        HttpStatus status;
        ApiResponse<PagedModel<EntityModel<NotificationDTO>>> response;
        if (notifications.hasContent()) {
            status = HttpStatus.OK;
            response = ApiResponse.<PagedModel<EntityModel<NotificationDTO>>>builder()
                    .code(status.value())
                    .message("Successfully find all notifications")
                    .result(assembler.toModel(notifications))
                    .build();
        } else {
            status = HttpStatus.NOT_FOUND;
            response = ApiResponse.<PagedModel<EntityModel<NotificationDTO>>>builder()
                    .code(status.value())
                    .message("Fail to find all notifications")
                    .result(null)
                    .build();
        }
        return ResponseEntity.status(status).body(response);
    }

    @PostMapping("/create")
    public ResponseEntity<ApiResponse<NotificationDTO>> createNotification(@RequestBody NotificationDTO notificationDTO) {
        NotificationDTO notificationD = notificationService.save(notificationDTO);
        ApiResponse<NotificationDTO> response;
        HttpStatus status;
        if (notificationD != null) {
            status = HttpStatus.CREATED;
            response = ApiResponse.<NotificationDTO>builder()
                    .code(status.value())
                    .message("Successfully created notifications")
                    .result(notificationD)
                    .build();
        } else {
            status = HttpStatus.BAD_REQUEST;
            response = ApiResponse.<NotificationDTO>builder()
                    .code(HttpStatus.BAD_REQUEST.value())
                    .message("Failed to create notification")
                    .result(null)
                    .build();
        }
        return ResponseEntity.status(status).body(response);
    }

    @PutMapping("/update")
    public ResponseEntity<ApiResponse<NotificationDTO>> updateNotification(@RequestBody NotificationDTO notificationDTO) {
        NotificationDTO updatedNotification = notificationService.update(notificationDTO);

        ApiResponse<NotificationDTO> response;
        HttpStatus status;

        if (updatedNotification != null) {
            status = HttpStatus.OK;
            response = ApiResponse.<NotificationDTO>builder()
                    .code(status.value())
                    .message("Successfully updated notification")
                    .result(updatedNotification)
                    .build();
        } else {
            status = HttpStatus.BAD_REQUEST;
            response = ApiResponse.<NotificationDTO>builder()
                    .code(status.value())
                    .message("Notification not found")
                    .result(null)
                    .build();
        }
        return ResponseEntity.status(status).body(response);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse<String>> deleteNotification(@PathVariable Long id) {
        boolean idDeleted = notificationService.delete(id);
        HttpStatus status;
        ApiResponse<String> response;
        if (idDeleted) {
            status = HttpStatus.NO_CONTENT;
            response = ApiResponse.<String>builder()
                    .code(status.value())
                    .message("Successfully deleted notification")
                    .result(null)
                    .build();
        } else {
            status = HttpStatus.NOT_FOUND;
            response = ApiResponse.<String>builder()
                    .code(status.value())
                    .message("Fail to delete notification")
                    .result(null)
                    .build();
        }
        return ResponseEntity.status(status).body(response);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<ApiResponse<NotificationDTO>> getNotificationById(@PathVariable Long id) {
        NotificationDTO notificationDTO = notificationService.getNotificationById(id);
        HttpStatus status;
        ApiResponse<NotificationDTO> response;
        if (notificationDTO != null) {
            status = HttpStatus.OK;
            response = ApiResponse.<NotificationDTO>builder()
                    .code(status.value())
                    .message("Successfully to finded notification")
                    .result(notificationDTO)
                    .build();
        } else {
            status = HttpStatus.NOT_FOUND;
            response = ApiResponse.<NotificationDTO>builder()
                    .code(status.value())
                    .message("Fail to find notification")
                    .result(null)
                    .build();
        }
        return ResponseEntity.status(status).body(response);
    }

}
