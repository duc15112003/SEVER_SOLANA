package com.solana.com.controller;

import com.solana.com.dto.UsersDTO;
import com.solana.com.respone.ApiResponse;
import com.solana.com.service.UsersService;
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
@RequestMapping("/api/users")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class UsersController {

    private final UsersService usersService;

    @GetMapping("/all")
    public ResponseEntity<ApiResponse<PagedModel<EntityModel<UsersDTO>>>> getAllUser(@RequestParam(value = "page", defaultValue = "0") int page,
                                                                                     @RequestParam(value = "size", defaultValue = "10") int size,
                                                                                     PagedResourcesAssembler<UsersDTO> assembler) {
        Page<UsersDTO> users = usersService.getAll(PageRequest.of(page, size));
        HttpStatus status;
        ApiResponse<PagedModel<EntityModel<UsersDTO>>> response;
        if (users.hasContent()) {
            status = HttpStatus.OK;
            response = ApiResponse.<PagedModel<EntityModel<UsersDTO>>>builder()
                    .code(status.value())
                    .message("Successfully find all users")
                    .result(assembler.toModel(users))
                    .build();
        } else {
            status = HttpStatus.NOT_FOUND;
            response = ApiResponse.<PagedModel<EntityModel<UsersDTO>>>builder()
                    .code(status.value())
                    .message("Fail to find all users")
                    .result(null)
                    .build();
        }
        return ResponseEntity.status(status).body(response);
    }

    @PostMapping("/create")
    public ResponseEntity<ApiResponse<UsersDTO>> createUser(@RequestBody UsersDTO userDTO) {
        UsersDTO userD = usersService.save(userDTO);
        ApiResponse<UsersDTO> response;
        HttpStatus status;
        if (userD != null) {
            status = HttpStatus.CREATED;
            response = ApiResponse.<UsersDTO>builder()
                    .code(status.value())
                    .message("Successfully created users")
                    .result(userD)
                    .build();
        } else {
            status = HttpStatus.BAD_REQUEST;
            response = ApiResponse.<UsersDTO>builder()
                    .code(HttpStatus.BAD_REQUEST.value())
                    .message("Failed to create user")
                    .result(null)
                    .build();
        }
        return ResponseEntity.status(status).body(response);
    }

    @PutMapping("/update")
    public ResponseEntity<ApiResponse<UsersDTO>> updateUser(@RequestBody UsersDTO userDTO) {
        UsersDTO updatedUser = usersService.update(userDTO);

        ApiResponse<UsersDTO> response;
        HttpStatus status;

        if (updatedUser != null) {
            status = HttpStatus.OK;
            response = ApiResponse.<UsersDTO>builder()
                    .code(status.value())
                    .message("Successfully updated user")
                    .result(updatedUser)
                    .build();
        } else {
            status = HttpStatus.BAD_REQUEST;
            response = ApiResponse.<UsersDTO>builder()
                    .code(status.value())
                    .message("User not found")
                    .result(null)
                    .build();
        }
        return ResponseEntity.status(status).body(response);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse<String>> deleteUser(@PathVariable Long id) {
        boolean idDeleted = usersService.delete(id);
        HttpStatus status;
        ApiResponse<String> response;
        if (idDeleted) {
            status = HttpStatus.NO_CONTENT;
            response = ApiResponse.<String>builder()
                    .code(status.value())
                    .message("Successfully deleted user")
                    .result(null)
                    .build();
        } else {
            status = HttpStatus.NOT_FOUND;
            response = ApiResponse.<String>builder()
                    .code(status.value())
                    .message("Fail to delete user")
                    .result(null)
                    .build();
        }
        return ResponseEntity.status(status).body(response);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<ApiResponse<UsersDTO>> getUserById(@PathVariable Long id) {
        UsersDTO userDTO = usersService.getUsersById(id);
        HttpStatus status;
        ApiResponse<UsersDTO> response;
        if (userDTO != null) {
            status = HttpStatus.OK;
            response = ApiResponse.<UsersDTO>builder()
                    .code(status.value())
                    .message("Successfully to finded user")
                    .result(userDTO)
                    .build();
        } else {
            status = HttpStatus.NOT_FOUND;
            response = ApiResponse.<UsersDTO>builder()
                    .code(status.value())
                    .message("Fail to find user")
                    .result(null)
                    .build();
        }
        return ResponseEntity.status(status).body(response);
    }

}
