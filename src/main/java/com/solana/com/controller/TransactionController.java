package com.solana.com.controller;

import com.solana.com.dto.TransactionDTO;
import com.solana.com.respone.ApiResponse;
import com.solana.com.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transaction")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class TransactionController {
    @Autowired
    TransactionService transactionService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<TransactionDTO>>> getAllTransaction() {
        List<TransactionDTO> transactions = transactionService.getAll();
        HttpStatus status;
        ApiResponse<List<TransactionDTO>> response;
        if (!transactions.isEmpty()) {
            status = HttpStatus.OK;
            response = ApiResponse.<List<TransactionDTO>>builder()
                    .code(status.value())
                    .message("Successfully find all transactions")
                    .result(transactions)
                    .build();
        } else {
            status = HttpStatus.NOT_FOUND;
            response = ApiResponse.<List<TransactionDTO>>builder()
                    .code(status.value())
                    .message("Fail to find all transactions")
                    .result(null)
                    .build();
        }
        return ResponseEntity.status(status).body(response);
    }

    @PostMapping
    public ResponseEntity<ApiResponse<TransactionDTO>> createTransaction(@RequestBody TransactionDTO transactionDTO) {
        TransactionDTO transactionD = transactionService.save(transactionDTO);
        ApiResponse<TransactionDTO> response;
        HttpStatus status;
        if (transactionD != null) {
            status = HttpStatus.CREATED;
            response = ApiResponse.<TransactionDTO>builder()
                    .code(status.value())
                    .message("Successfully created transactions")
                    .result(transactionD)
                    .build();
        } else {
            status = HttpStatus.BAD_REQUEST;
            response = ApiResponse.<TransactionDTO>builder()
                    .code(HttpStatus.BAD_REQUEST.value())
                    .message("Failed to create transaction")
                    .result(null)
                    .build();
        }
        return ResponseEntity.status(status).body(response);
    }

    @PutMapping
    public ResponseEntity<ApiResponse<TransactionDTO>> updateTransaction(@RequestBody TransactionDTO transactionDTO) {
        TransactionDTO updatedTransaction = transactionService.update(transactionDTO);

        ApiResponse<TransactionDTO> response;
        HttpStatus status;

        if (updatedTransaction != null) {
            status = HttpStatus.OK;
            response = ApiResponse.<TransactionDTO>builder()
                    .code(status.value())
                    .message("Successfully updated transaction")
                    .result(updatedTransaction)
                    .build();
        } else {
            status = HttpStatus.BAD_REQUEST;
            response = ApiResponse.<TransactionDTO>builder()
                    .code(status.value())
                    .message("Transaction not found")
                    .result(null)
                    .build();
        }
        return ResponseEntity.status(status).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> deleteTransaction(@PathVariable Long id) {
        boolean idDeleted = transactionService.delete(id);
        HttpStatus status;
        ApiResponse<String> response;
        if (idDeleted) {
            status = HttpStatus.NO_CONTENT;
            response = ApiResponse.<String>builder()
                    .code(status.value())
                    .message("Successfully deleted transaction")
                    .result(null)
                    .build();
        } else {
            status = HttpStatus.NOT_FOUND;
            response = ApiResponse.<String>builder()
                    .code(status.value())
                    .message("Fail to delete transaction")
                    .result(null)
                    .build();
        }
        return ResponseEntity.status(status).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<TransactionDTO>> getTransactionById(@PathVariable Long id) {
        TransactionDTO transactionDTO = transactionService.getTransactionById(id);
        HttpStatus status;
        ApiResponse<TransactionDTO> response;
        if (transactionDTO != null) {
            status = HttpStatus.OK;
            response = ApiResponse.<TransactionDTO>builder()
                    .code(status.value())
                    .message("Successfully to finded transaction")
                    .result(transactionDTO)
                    .build();
        } else {
            status = HttpStatus.NOT_FOUND;
            response = ApiResponse.<TransactionDTO>builder()
                    .code(status.value())
                    .message("Fail to find transaction")
                    .result(null)
                    .build();
        }
        return ResponseEntity.status(status).body(response);
    }
}
