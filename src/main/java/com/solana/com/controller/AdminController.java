package com.solana.com.controller;

import com.solana.com.dto.AdminDTO;
import com.solana.com.mapper.AdminMapper;

import com.solana.com.respone.ApiResponse;
import com.solana.com.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class AdminController {
    @Autowired
    AdminService adminService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<AdminDTO>>> getAllAdmin() {
        List<AdminDTO> admins = adminService.getAll();
        HttpStatus status;
        ApiResponse<List<AdminDTO>> response;
        if (!admins.isEmpty()) {
            status = HttpStatus.OK;
            response = ApiResponse.<List<AdminDTO>>builder()
                    .code(status.value())
                    .message("Successfully find all admins")
                    .result(admins)
                    .build();
        } else {
            status = HttpStatus.NOT_FOUND;
            response = ApiResponse.<List<AdminDTO>>builder()
                    .code(status.value())
                    .message("Fail to find all admins")
                    .result(null)
                    .build();
        }
        return ResponseEntity.status(status).body(response);
    }

    @PostMapping
    public ResponseEntity<ApiResponse<AdminDTO>> createAdmin(@RequestBody AdminDTO adminDTO) {
        AdminDTO adminD = adminService.save(adminDTO);
        ApiResponse<AdminDTO> response;
        HttpStatus status;
        if (adminD != null) {
            status = HttpStatus.CREATED;
            response = ApiResponse.<AdminDTO>builder()
                    .code(status.value())
                    .message("Successfully created admins")
                    .result(adminD)
                    .build();
        } else {
            status = HttpStatus.BAD_REQUEST;
            response = ApiResponse.<AdminDTO>builder()
                    .code(HttpStatus.BAD_REQUEST.value())
                    .message("Failed to create admin")
                    .result(null)
                    .build();
        }
        return ResponseEntity.status(status).body(response);
    }

    @PutMapping
    public ResponseEntity<ApiResponse<AdminDTO>> updateAdmin(@RequestBody AdminDTO adminDTO) {
        AdminDTO updatedAdmin = adminService.update(adminDTO);

        ApiResponse<AdminDTO> response;
        HttpStatus status;

        if (updatedAdmin != null) {
            status = HttpStatus.OK;
            response = ApiResponse.<AdminDTO>builder()
                    .code(status.value())
                    .message("Successfully updated admin")
                    .result(updatedAdmin)
                    .build();
        } else {
            status = HttpStatus.BAD_REQUEST;
            response = ApiResponse.<AdminDTO>builder()
                    .code(status.value())
                    .message("Admin not found")
                    .result(null)
                    .build();
        }
        return ResponseEntity.status(status).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> deleteAdmin(@PathVariable Long id) {
        boolean idDeleted = adminService.delete(id);
        HttpStatus status;
        ApiResponse<String> response;
        if (idDeleted) {
            status = HttpStatus.NO_CONTENT;
            response = ApiResponse.<String>builder()
                    .code(status.value())
                    .message("Successfully deleted admin")
                    .result(null)
                    .build();
        } else {
            status = HttpStatus.NOT_FOUND;
            response = ApiResponse.<String>builder()
                    .code(status.value())
                    .message("Fail to delete admin")
                    .result(null)
                    .build();
        }
        return ResponseEntity.status(status).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<AdminDTO>> getAdminById(@PathVariable Long id) {
        AdminDTO adminDTO = adminService.getAdminById(id);
        HttpStatus status;
        ApiResponse<AdminDTO> response;
        if (adminDTO != null) {
            status = HttpStatus.OK;
            response = ApiResponse.<AdminDTO>builder()
                    .code(status.value())
                    .message("Successfully to finded admin")
                    .result(adminDTO)
                    .build();
        } else {
            status = HttpStatus.NOT_FOUND;
            response = ApiResponse.<AdminDTO>builder()
                    .code(status.value())
                    .message("Fail to find admin")
                    .result(null)
                    .build();
        }
        return ResponseEntity.status(status).body(response);
    }

}
