package com.example.test.service;

import com.example.test.db.entity.Transaction;
import com.example.test.db.entity.user.User;
import com.example.test.dto.ApiResponse;
import com.example.test.dto.transaction.req.EditOrAddTransactionRequestDto;
import com.example.test.dto.transaction.res.TransactionResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface TransactionService  {
    ApiResponse<List<TransactionResponseDto>> list(User currentUser) ;
    ApiResponse<TransactionResponseDto> findById(UUID id);
    ApiResponse<TransactionResponseDto> add(EditOrAddTransactionRequestDto req , User currentUser) ;
    ApiResponse<TransactionResponseDto> edit(EditOrAddTransactionRequestDto req, UUID transactionId);
    ApiResponse<Void> delete(UUID transactionId);

    /////ADMIN ROLE
    ApiResponse<List<TransactionResponseDto>> findAllByUserId(UUID userid) ;
    ApiResponse<List<TransactionResponseDto>> findAllByCategoryId(UUID categoryId) ;
    ApiResponse<List<TransactionResponseDto>> findAllByUserAndCategoryId(UUID userId, UUID categoryId);
    ApiResponse<Page<TransactionResponseDto>> list(int page, int size);

}
