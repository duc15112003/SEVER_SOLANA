package com.solana.com.controller;

import com.solana.com.dto.IdeasDTO;
import com.solana.com.respone.ApiResponse;
import com.solana.com.service.IdeasService;
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
@RequestMapping("/api/ideas")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class IdeasController {
    @Autowired
    private final IdeasService ideasService;

    @GetMapping("/all")
    public ResponseEntity<ApiResponse<PagedModel<EntityModel<IdeasDTO>>>> getAllIdeas(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size,
            PagedResourcesAssembler<IdeasDTO> assembler) {
        Page<IdeasDTO> ideasPage = ideasService.getAll(PageRequest.of(page, size));
        return ideasPage.hasContent()
                ? ResponseEntity.ok(ApiResponse.<PagedModel<EntityModel<IdeasDTO>>>builder()
                .code(HttpStatus.OK.value())
                .message("Successfully found all ideas")
                .result(assembler.toModel(ideasPage))
                .build())
                : ResponseEntity.status(HttpStatus.NOT_FOUND).body(ApiResponse.<PagedModel<EntityModel<IdeasDTO>>>builder()
                .code(HttpStatus.NOT_FOUND.value())
                .message("No ideas found")
                .result(null)
                .build());
    }

    @PostMapping("/create")
    public ResponseEntity<ApiResponse<IdeasDTO>> createIdea(@RequestBody IdeasDTO ideasDTO) {
        IdeasDTO createdIdea = ideasService.save(ideasDTO);
        return createdIdea != null
                ? ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.<IdeasDTO>builder()
                .code(HttpStatus.CREATED.value())
                .message("Successfully created idea")
                .result(createdIdea)
                .build())
                : ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ApiResponse.<IdeasDTO>builder()
                .code(HttpStatus.BAD_REQUEST.value())
                .message("Failed to create idea")
                .result(null)
                .build());
    }

    @PutMapping("/update")
    public ResponseEntity<ApiResponse<IdeasDTO>> updateIdea(@RequestBody IdeasDTO ideasDTO) {
        IdeasDTO updatedIdea = ideasService.update(ideasDTO);
        return updatedIdea != null
                ? ResponseEntity.ok(ApiResponse.<IdeasDTO>builder()
                .code(HttpStatus.OK.value())
                .message("Successfully updated idea")
                .result(updatedIdea)
                .build())
                : ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ApiResponse.<IdeasDTO>builder()
                .code(HttpStatus.BAD_REQUEST.value())
                .message("Failed to update idea or idea not found")
                .result(null)
                .build());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse<String>> deleteIdea(@PathVariable Long id) {
        return ideasService.delete(id)
                ? ResponseEntity.status(HttpStatus.NO_CONTENT).body(ApiResponse.<String>builder()
                .code(HttpStatus.NO_CONTENT.value())
                .message("Successfully deleted idea")
                .result(null)
                .build())
                : ResponseEntity.status(HttpStatus.NOT_FOUND).body(ApiResponse.<String>builder()
                .code(HttpStatus.NOT_FOUND.value())
                .message("Idea not found")
                .result(null)
                .build());
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<ApiResponse<IdeasDTO>> getIdeaById(@PathVariable Long id) {
        IdeasDTO ideasDTO = ideasService.getIdeasById(id);
        return ideasDTO != null
                ? ResponseEntity.ok(ApiResponse.<IdeasDTO>builder()
                .code(HttpStatus.OK.value())
                .message("Successfully found idea")
                .result(ideasDTO)
                .build())
                : ResponseEntity.status(HttpStatus.NOT_FOUND).body(ApiResponse.<IdeasDTO>builder()
                .code(HttpStatus.NOT_FOUND.value())
                .message("Idea not found")
                .result(null)
                .build());
    }
}
