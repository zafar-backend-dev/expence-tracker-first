package com.example.test.service.impl;

import com.example.test.db.entity.Category;
import com.example.test.db.entity.Transaction;
import com.example.test.db.entity.user.User;
import com.example.test.db.repositories.CategoryRepository;
import com.example.test.db.repositories.TransactionRepository;
import com.example.test.db.repositories.UserRepository;
import com.example.test.dto.ApiResponse;
import com.example.test.dto.transaction.req.EditOrAddTransactionRequestDto;
import com.example.test.dto.transaction.res.TransactionResponseDto;
import com.example.test.mapper.TransactionMapper;
import com.example.test.service.TransactionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TransactionServiceImpl implements TransactionService {
    private final TransactionRepository transactionRepository;
    private final TransactionMapper transactionMapper;
    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;

    public TransactionServiceImpl(TransactionRepository transactionRepository, TransactionMapper transactionMapper, CategoryRepository categoryRepository, UserRepository userRepository) {
        this.transactionRepository = transactionRepository;
        this.transactionMapper = transactionMapper;
        this.categoryRepository = categoryRepository;
        this.userRepository = userRepository;
    }

    @Override
    public ApiResponse<List<TransactionResponseDto>> list(User currentUser) {
        List<Transaction> all = transactionRepository.findAll(currentUser.getPkey());
        List<TransactionResponseDto> dtos = all.stream().map(transactionMapper::toDto).toList();
        return new ApiResponse<>(true, "Ok", "Tamam", dtos);
    }

    @Override
    public ApiResponse<TransactionResponseDto> findById(UUID id) {

        Optional<Transaction> transactionOptional =
                transactionRepository.findById(id);

        return transactionOptional
                .map(transaction -> new ApiResponse<>(
                        true,
                        "Transaction found successfully",
                        "İşlem başarıyla bulundu",
                        transactionMapper.toDto(transaction)
                ))
                .orElseGet(() -> new ApiResponse<>(
                        false,
                        "Transaction not found",
                        "İşlem bulunamadı",
                        null
                ));
    }

    @Override
    public ApiResponse<TransactionResponseDto> add(
            EditOrAddTransactionRequestDto req,
            User currentUser
    ) {

        if (currentUser == null) {
            return new ApiResponse<>(
                    false,
                    "User not found",
                    "Kullanıcı bulunamadı",
                    null
            );
        }

        Optional<Category> categoryOptional =
                categoryRepository.findById(req.getCategoryId());

        if (categoryOptional.isEmpty()) {
            return new ApiResponse<>(
                    false,
                    "Category not found",
                    "Kategori bulunamadı",
                    null
            );
        }

        Transaction transaction = new Transaction();

        transaction.setAmount(req.getAmount());
        transaction.setDescription(req.getDescription());
        transaction.setActive(true);
        transaction.setType(req.getType());
        transaction.setTransactionDate(req.getTransactionDate());
        transaction.setCategory(categoryOptional.get());
        transaction.setUser(currentUser);
        transaction.setCreatedAt(LocalDateTime.now());
        transaction.setUpdatedAt(LocalDateTime.now());

        Transaction savedTransaction =
                transactionRepository.save(transaction);

        return new ApiResponse<>(
                true,
                "Transaction created successfully",
                "İşlem başarıyla oluşturuldu",
                transactionMapper.toDto(savedTransaction)
        );
    }

    @Override
    public ApiResponse<TransactionResponseDto> edit(
            EditOrAddTransactionRequestDto req,
            UUID transactionId
    ) {

        Optional<Transaction> transactionOptional =
                transactionRepository.findById(transactionId);

        if (transactionOptional.isEmpty()) {
            return new ApiResponse<>(
                    false,
                    "Transaction not found",
                    "İşlem bulunamadı",
                    null
            );
        }

        Optional<Category> categoryOptional =
                categoryRepository.findById(req.getCategoryId());

        if (categoryOptional.isEmpty()) {
            return new ApiResponse<>(
                    false,
                    "Category not found",
                    "Kategori bulunamadı",
                    null
            );
        }

        Transaction transaction = transactionOptional.get();

        transaction.setAmount(req.getAmount());
        transaction.setDescription(req.getDescription());
        transaction.setType(req.getType());
        transaction.setTransactionDate(req.getTransactionDate());
        transaction.setCategory(categoryOptional.get());
        transaction.setUpdatedAt(LocalDateTime.now());

        Transaction updatedTransaction =
                transactionRepository.save(transaction);

        return new ApiResponse<>(
                true,
                "Transaction updated successfully",
                "İşlem başarıyla güncellendi",
                transactionMapper.toDto(updatedTransaction)
        );
    }

    @Override
    public ApiResponse<Void> delete(UUID transactionId) {

        Optional<Transaction> transactionOptional =
                transactionRepository.findById(transactionId);

        if (transactionOptional.isEmpty()) {
            return new ApiResponse<>(
                    false,
                    "Transaction not found",
                    "İşlem bulunamadı"
            );
        }

        Transaction transaction = transactionOptional.get();

        transaction.setActive(false);
        transaction.setUpdatedAt(LocalDateTime.now());

        transactionRepository.save(transaction);

        return new ApiResponse<>(
                true,
                "Transaction deleted successfully",
                "İşlem başarıyla silindi"
        );
    }

    @Override
    public ApiResponse<List<TransactionResponseDto>> findAllByUserId(UUID userid) {
        if (userRepository.findById(userid).isEmpty()) {
            return new ApiResponse<>(
                    false,
                    "Not found user",
                    "User bulunamadı",

                    null
            );
        }
        List<Transaction> transactions = transactionRepository.findAllByUserId(userid);
        List<TransactionResponseDto> res = transactions.stream().map(transactionMapper::toDto).toList();
        return new ApiResponse<>(true, "Success", "Tamam", res);
    }

    @Override
    public ApiResponse<List<TransactionResponseDto>> findAllByCategoryId(UUID categoryId) {
        if (categoryRepository.findById(categoryId).isEmpty()) {
            return new ApiResponse<>(
                    false,
                    "Category not found",
                    "Kategori bulunamadı",
                    null
            );
        }
        List<Transaction> transactions = transactionRepository.findAllByCategoryId(categoryId);
        List<TransactionResponseDto> res = transactions.stream().map(transactionMapper::toDto).toList();
        return new ApiResponse<>(true, "Success", "Tamam", res);
    }

    @Override
    public ApiResponse<List<TransactionResponseDto>> findAllByUserAndCategoryId(
            UUID userId,
            UUID categoryId
    ) {

        if (categoryRepository.findById(categoryId).isEmpty()) {
            return new ApiResponse<>(
                    false,
                    "Category not found",
                    "Kategori bulunamadı",
                    null
            );
        }

        if (userRepository.findById(userId).isEmpty()) {
            return new ApiResponse<>(
                    false,
                    "User not found",
                    "Kullanıcı bulunamadı",
                    null
            );
        }

        List<Transaction> transactions =
                transactionRepository.findAllByUserAndCategoryId(categoryId, userId);

        List<TransactionResponseDto> res = transactions.stream()
                .map(transactionMapper::toDto)
                .toList();

        return new ApiResponse<>(
                true,
                "Transactions retrieved successfully",
                "İşlemler başarıyla getirildi",
                res
        );
    }

    @Override
    public ApiResponse<Page<TransactionResponseDto>> list(int page, int size) {
        Page<Transaction> transactions = transactionRepository.findAll(PageRequest.of(page, size));
        Page<TransactionResponseDto> res = transactions.map(transactionMapper::toDto);
        return new ApiResponse<>(true, "Success", "Tamam", res);
    }
}
