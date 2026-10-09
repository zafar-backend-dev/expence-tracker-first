package com.example.test.db.repositories;

import com.example.test.db.entity.Transaction;
import org.jspecify.annotations.NonNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TransactionRepository extends JpaRepository<Transaction, UUID> {
    @Query("""
            SELECT t
            FROM Transaction t
            WHERE t.active = true
              AND t.user.pkey = :userid
            ORDER BY t.transactionDate DESC
            """)
    List<Transaction> findAll(@Param("userid") UUID userid);

    @Query("select t from Transaction t where t.active = true and t.id=:id")
    Optional<Transaction> findById(@Param("id") UUID id);


    @Query("select t from Transaction t where t.user.pkey = :userid and t.active=true order by t.transactionDate desc")
    List<Transaction> findAllByUserId(@Param("userid") UUID userid);

    @Query("select t from Transaction t where t.category.pkey = :category_id and t.active=true order by t.transactionDate desc")
    List<Transaction> findAllByCategoryId(@Param("category_id") UUID cid);

    @Query("select t from Transaction t where t.category.pkey = :category_id and t.user.pkey=:userId and t.active=true order by t.transactionDate asc")
    List<Transaction> findAllByUserAndCategoryId(@Param("category_id") UUID categoryId,@Param("userid") UUID userId);
    @Query("select t from Transaction t where t.active=true order by t.transactionDate desc")
    Page<Transaction> findAll(@NonNull Pageable pageable);
}
