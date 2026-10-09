package com.example.test.mapper;

import com.example.test.db.entity.Transaction;
import com.example.test.dto.transaction.res.TransactionResponseDto;
import org.springframework.stereotype.Component;

@Component
public class TransactionMapper {
    public TransactionResponseDto toDto(Transaction t) {
        TransactionResponseDto dto = new TransactionResponseDto();
        dto.setId(t.getId());
        dto.setAmount(t.getAmount());
        dto.setDescription(t.getDescription());
        dto.setType(t.getType());
        dto.setTransactionDate(t.getTransactionDate());
        dto.setUserid(t.getUser().getPkey());
        dto.setCategoryId(t.getCategory().getPkey());
        dto.setCreatedAt(t.getCreatedAt());
        dto.setUpdatedAt(t.getUpdatedAt());
        return dto;
    }
}
