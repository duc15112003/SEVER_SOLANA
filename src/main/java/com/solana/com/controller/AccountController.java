package com.solana.com.controller;

import com.solana.com.dto.AccountDTO;
import com.solana.com.respone.ApiResponse;
import com.solana.com.service.AccountService;
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
@RequestMapping("/api/account")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class AccountController {
    @Autowired
    AccountService accountService;

    @GetMapping("/all")
    public ResponseEntity<ApiResponse<PagedModel<EntityModel<AccountDTO>>>> getAllAccount(@RequestParam(value = "page", defaultValue = "0") int page,
                                                                                          @RequestParam(value = "size", defaultValue = "10") int size,
                                                                                          PagedResourcesAssembler<AccountDTO> assembler) {
        Page<AccountDTO> accounts = accountService.getAll(PageRequest.of(page,size));
        HttpStatus status;
        ApiResponse<PagedModel<EntityModel<AccountDTO>>> response;
        if (accounts.hasContent()) {
            status = HttpStatus.OK;
            response = ApiResponse.<PagedModel<EntityModel<AccountDTO>>>builder()
                    .code(status.value())
                    .message("Successfully find all accounts")
                    .result(assembler.toModel(accounts))
                    .build();
        } else {
            status = HttpStatus.NOT_FOUND;
            response = ApiResponse.<PagedModel<EntityModel<AccountDTO>>>builder()
                    .code(status.value())
                    .message("Fail to find all accounts")
                    .result(null)
                    .build();
        }
        return ResponseEntity.status(status).body(response);
    }

    @PostMapping("/create")
    public ResponseEntity<ApiResponse<AccountDTO>> createAccount(@RequestBody AccountDTO accountDTO) {
        AccountDTO accountD = accountService.save(accountDTO);
        ApiResponse<AccountDTO> response;
        HttpStatus status;
        if (accountD != null) {
            status = HttpStatus.CREATED;
            response = ApiResponse.<AccountDTO>builder()
                    .code(status.value())
                    .message("Successfully created accounts")
                    .result(accountD)
                    .build();
        } else {
            status = HttpStatus.BAD_REQUEST;
            response = ApiResponse.<AccountDTO>builder()
                    .code(HttpStatus.BAD_REQUEST.value())
                    .message("Failed to create account")
                    .result(null)
                    .build();
        }
        return ResponseEntity.status(status).body(response);
    }

    @PutMapping("/update")
    public ResponseEntity<ApiResponse<AccountDTO>> updateAccount(@RequestBody AccountDTO accountDTO) {
        AccountDTO updatedAccount = accountService.update(accountDTO);

        ApiResponse<AccountDTO> response;
        HttpStatus status;

        if (updatedAccount != null) {
            status = HttpStatus.OK;
            response = ApiResponse.<AccountDTO>builder()
                    .code(status.value())
                    .message("Successfully updated account")
                    .result(updatedAccount)
                    .build();
        } else {
            status = HttpStatus.BAD_REQUEST;
            response = ApiResponse.<AccountDTO>builder()
                    .code(status.value())
                    .message("Account not found")
                    .result(null)
                    .build();
        }
        return ResponseEntity.status(status).body(response);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse<String>> deleteAccount(@PathVariable String id) {
        boolean idDeleted = accountService.delete(id);
        HttpStatus status;
        ApiResponse<String> response;
        if (idDeleted) {
            status = HttpStatus.NO_CONTENT;
            response = ApiResponse.<String>builder()
                    .code(status.value())
                    .message("Successfully deleted account")
                    .result(null)
                    .build();
        } else {
            status = HttpStatus.NOT_FOUND;
            response = ApiResponse.<String>builder()
                    .code(status.value())
                    .message("Fail to delete account")
                    .result(null)
                    .build();
        }
        return ResponseEntity.status(status).body(response);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<ApiResponse<AccountDTO>> getAccountById(@PathVariable String id) {
        AccountDTO accountDTO = accountService.getAccountById(id);
        HttpStatus status;
        ApiResponse<AccountDTO> response;
        if (accountDTO != null) {
            status = HttpStatus.OK;
            response = ApiResponse.<AccountDTO>builder()
                    .code(status.value())
                    .message("Successfully to finded account")
                    .result(accountDTO)
                    .build();
        } else {
            status = HttpStatus.NOT_FOUND;
            response = ApiResponse.<AccountDTO>builder()
                    .code(status.value())
                    .message("Fail to find account")
                    .result(null)
                    .build();
        }
        return ResponseEntity.status(status).body(response);
    }
}
