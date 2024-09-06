package com.solana.com.controller;

import com.solana.com.dto.IdeasDTO;
import com.solana.com.respone.ApiResponse;
import com.solana.com.service.IdeasService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ideas")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class IdeasController {

    private final IdeasService ideasService;

    @GetMapping("/all")
    public ResponseEntity<ApiResponse<PagedModel<EntityModel<IdeasDTO>>>> getAllUser(@RequestParam(value = "page", defaultValue = "0") int page,
                                                                                     @RequestParam(value = "size", defaultValue = "10") int size,
                                                                                     PagedResourcesAssembler<IdeasDTO> assembler) {
        Page<IdeasDTO> ideas = ideasService.getAll(PageRequest.of(page, size));
        HttpStatus status;
        ApiResponse<PagedModel<EntityModel<IdeasDTO>>> response;
        if (ideas.hasContent()) {
            status = HttpStatus.OK;
            response = ApiResponse.<PagedModel<EntityModel<IdeasDTO>>>builder()
                    .code(status.value())
                    .message("Successfully find all ideas")
                    .result(assembler.toModel(ideas))
                    .build();
        } else {
            status = HttpStatus.NOT_FOUND;
            response = ApiResponse.<PagedModel<EntityModel<IdeasDTO>>>builder()
                    .code(status.value())
                    .message("Fail to find all ideas")
                    .result(null)
                    .build();
        }
        return ResponseEntity.status(status).body(response);
    }

    @PostMapping("/create")
    public ResponseEntity<ApiResponse<IdeasDTO>> createUser(@RequestBody IdeasDTO ideasDTO) {
        IdeasDTO ideasD = ideasService.save(ideasDTO);
        ApiResponse<IdeasDTO> response;
        HttpStatus status;
        if (ideasD != null) {
            status = HttpStatus.CREATED;
            response = ApiResponse.<IdeasDTO>builder()
                    .code(status.value())
                    .message("Successfully created ideas")
                    .result(ideasD)
                    .build();
        } else {
            status = HttpStatus.BAD_REQUEST;
            response = ApiResponse.<IdeasDTO>builder()
                    .code(HttpStatus.BAD_REQUEST.value())
                    .message("Failed to create ideas")
                    .result(null)
                    .build();
        }
        return ResponseEntity.status(status).body(response);
    }

    @PutMapping("/update")
    public ResponseEntity<ApiResponse<IdeasDTO>> updateUser(@RequestBody IdeasDTO ideasDTO) {
        IdeasDTO updatedIdea = ideasService.update(ideasDTO);

        ApiResponse<IdeasDTO> response;
        HttpStatus status;

        if (updatedIdea != null) {
            status = HttpStatus.OK;
            response = ApiResponse.<IdeasDTO>builder()
                    .code(status.value())
                    .message("Successfully updated ideas")
                    .result(updatedIdea)
                    .build();
        } else {
            status = HttpStatus.BAD_REQUEST;
            response = ApiResponse.<IdeasDTO>builder()
                    .code(status.value())
                    .message("User not found")
                    .result(null)
                    .build();
        }
        return ResponseEntity.status(status).body(response);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse<String>> deleteUser(@PathVariable Long id) {
        boolean idDeleted = ideasService.delete(id);
        HttpStatus status;
        ApiResponse<String> response;
        if (idDeleted) {
            status = HttpStatus.NO_CONTENT;
            response = ApiResponse.<String>builder()
                    .code(status.value())
                    .message("Successfully deleted ideas")
                    .result(null)
                    .build();
        } else {
            status = HttpStatus.NOT_FOUND;
            response = ApiResponse.<String>builder()
                    .code(status.value())
                    .message("Fail to delete ideas")
                    .result(null)
                    .build();
        }
        return ResponseEntity.status(status).body(response);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<ApiResponse<IdeasDTO>> getUserById(@PathVariable Long id) {
        IdeasDTO ideasDTO = ideasService.getIdeasById(id);
        HttpStatus status;
        ApiResponse<IdeasDTO> response;
        if (ideasDTO != null) {
            status = HttpStatus.OK;
            response = ApiResponse.<IdeasDTO>builder()
                    .code(status.value())
                    .message("Successfully to finded ideas")
                    .result(ideasDTO)
                    .build();
        } else {
            status = HttpStatus.NOT_FOUND;
            response = ApiResponse.<IdeasDTO>builder()
                    .code(status.value())
                    .message("Fail to find ideas")
                    .result(null)
                    .build();
        }
        return ResponseEntity.status(status).body(response);
    }
}
