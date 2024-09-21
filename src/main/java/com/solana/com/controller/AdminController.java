package com.solana.com.controller;

import com.solana.com.dto.AccountDTO;
import com.solana.com.dto.AdminDTO;
import com.solana.com.mapper.AdminMapper;

import com.solana.com.respone.ApiResponse;
import com.solana.com.service.AdminService;
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

@CrossOrigin("*")
@RestController
@RequestMapping("/api/admin")
public class AdminController {
    @Autowired
    AdminService adminService;

    @GetMapping("all")
    public ResponseEntity<ApiResponse<PagedModel<EntityModel<AdminDTO>>>> getAllAdmin(@RequestParam(value = "page", defaultValue = "0") int page,
                                                                   @RequestParam(value = "size", defaultValue = "10") int size,
                                                                   PagedResourcesAssembler<AdminDTO> assembler) {
        Page<AdminDTO> admins = adminService.getAll(PageRequest.of(page,size));
        HttpStatus status;
        ApiResponse<PagedModel<EntityModel<AdminDTO>>> response;
        if (admins.hasContent()) {
            status = HttpStatus.OK;
            response = ApiResponse.<PagedModel<EntityModel<AdminDTO>>>builder()
                    .code(status.value())
                    .message("Successfully find all admins")
                    .result(assembler.toModel(admins))
                    .build();
        } else {
            status = HttpStatus.NOT_FOUND;
            response = ApiResponse.<PagedModel<EntityModel<AdminDTO>>>builder()
                    .code(status.value())
                    .message("Fail to find all admins")
                    .result(null)
                    .build();
        }
        return ResponseEntity.status(status).body(response);
    }

    @PostMapping("/create")
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

    @PutMapping("/update")
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

    @DeleteMapping("/id/{id}")
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
