package com.example.test.db.repositories;

import com.example.test.db.entity.Category;
import org.jspecify.annotations.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CategoryRepository extends JpaRepository<Category, UUID> {

    boolean existsByNameEn(String nameEn);

    @Query("select c from Category c where c.active = :active order by c.orderIndex asc")
    List<Category> findAll(@Param("active") boolean active);
    @Query("select c from Category c where c.active=true and c.pkey = :category_id")
    Optional<Category> findById(@Param("category_id") @NonNull UUID categoryId);

    boolean existsByNameEnAndPkeyNot(String nameEn, UUID id);

    @Query("""
            SELECT c
            FROM Category c
            WHERE c.active = true
              AND (
                    LOWER(c.nameEn) LIKE LOWER(CONCAT('%', :keyword, '%'))
                    OR
                    LOWER(c.nameTr) LIKE LOWER(CONCAT('%', :keyword, '%'))
                  )
            """)
    List<Category> search(@Param("keyword") String keyword);

}
