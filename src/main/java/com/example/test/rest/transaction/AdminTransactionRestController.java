package com.example.test.rest.transaction;

import com.example.test.dto.ApiResponse;
import com.example.test.dto.transaction.res.TransactionResponseDto;
import com.example.test.service.TransactionService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/admin/transaction")
public class AdminTransactionRestController {
    private final TransactionService transactionService;

    public AdminTransactionRestController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping("list")
    public ApiResponse<Page<TransactionResponseDto>> getAllTransactions(@RequestParam int page, @RequestParam int size) {
        return transactionService.list(page, size);
    }

    @GetMapping("list/by-userid/{userid}")
    public ApiResponse<List<TransactionResponseDto>> findAllByUserId(@PathVariable UUID userid) {
        return transactionService.findAllByUserId(userid);
    }

    @GetMapping("list/by-category-id/{category_id}")
    public ApiResponse<List<TransactionResponseDto>> findAllByCategoryId(@PathVariable("category_id") UUID categoryId) {
        return transactionService.findAllByCategoryId(categoryId);
    }


    @GetMapping("list/by-userid-and-category-id/{userid}/{category_id}")
    public ApiResponse<List<TransactionResponseDto>> findAllByUserIdAndCategoryId(@PathVariable UUID userid, @PathVariable("category_id") UUID categoryId) {
        return transactionService.findAllByUserAndCategoryId(userid, categoryId);
    }
    
}
