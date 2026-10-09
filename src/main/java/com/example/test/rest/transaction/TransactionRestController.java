package com.example.test.rest.transaction;

import com.example.test.dto.ApiResponse;
import com.example.test.dto.transaction.req.EditOrAddTransactionRequestDto;
import com.example.test.dto.transaction.res.TransactionResponseDto;
import com.example.test.security.UserPrincipal;
import com.example.test.service.TransactionService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/transaction")
public class TransactionRestController {
    private final TransactionService transactionService;

    public TransactionRestController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping("list")
    public ApiResponse<List<TransactionResponseDto>> list(@AuthenticationPrincipal UserPrincipal userPrincipal) {
        return transactionService.list(userPrincipal.getUser());
    }

    @GetMapping("/{transaction_id}")
    public ApiResponse<TransactionResponseDto> transaction(@PathVariable("transaction_id") UUID transactionId) {
        return transactionService.findById(transactionId);
    }

    @PostMapping("add")
    public ApiResponse<TransactionResponseDto> add(@RequestBody EditOrAddTransactionRequestDto req, @AuthenticationPrincipal UserPrincipal userPrincipal) {
        return transactionService.add(req, userPrincipal.getUser());
    }

    @PutMapping("/{id}/edit")
    public ApiResponse<TransactionResponseDto> edit(@RequestBody EditOrAddTransactionRequestDto req, @PathVariable("id") UUID transactionId) {
        return transactionService.edit(req, transactionId);
    }

    @DeleteMapping("/{id}/delete")
    public ApiResponse<Void> delete(@PathVariable("id") UUID transactionId) {
        return transactionService.delete(transactionId);
    }
}
