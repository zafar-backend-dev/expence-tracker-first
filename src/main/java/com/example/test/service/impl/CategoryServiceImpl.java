package com.example.test.service.impl;

import com.example.test.db.entity.Category;
import com.example.test.db.entity.enums.UserRole;
import com.example.test.db.repositories.CategoryRepository;
import com.example.test.db.repositories.TransactionRepository;
import com.example.test.dto.ApiResponse;
import com.example.test.dto.category.req.EditOrAddCategoryRequestDto;
import com.example.test.dto.category.res.CategoryResponseDto;
import com.example.test.mapper.CategoryMapper;
import com.example.test.service.CategoryService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;
    private final TransactionRepository transactionRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository, CategoryMapper categoryMapper, TransactionRepository transactionRepository) {
        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;
        this.transactionRepository = transactionRepository;
    }

    @Override
    public ApiResponse<List<CategoryResponseDto>> getAllCategories() {
        List<Category> list = categoryRepository.findAll(true);
        List<CategoryResponseDto> res = list.stream().map(categoryMapper::toDto).toList();
        return new ApiResponse<>(true, "Ok", "Tamam", res);
    }

    @Override
    public ApiResponse<CategoryResponseDto> getCategoryById(UUID categoryId) {

        return categoryRepository.findById(categoryId)
                .map(category -> new ApiResponse<>(
                        true,
                        "Category found successfully",
                        "Kategori başarıyla bulundu",
                        categoryMapper.toDto(category)
                ))
                .orElseGet(() -> new ApiResponse<>(
                        false,
                        "Category not found",
                        "Kategori bulunamadı",
                        null
                ));
    }

    @Override
    public ApiResponse<CategoryResponseDto> create(EditOrAddCategoryRequestDto req) {

        if (categoryRepository.existsByNameEn(req.getNameEn())) {
            return new ApiResponse<>(
                    false,
                    "Category already exists",
                    "Kategori zaten mevcut",
                    null
            );
        }

        Category category = new Category();

        category.setNameEn(req.getNameEn());
        category.setNameTr(req.getNameTr());
        category.setDescriptionEn(req.getDescriptionEn());
        category.setDescriptionTr(req.getDescriptionTr());
        category.setActive(true);
        category.setCreatedAt(LocalDateTime.now());
        category.setUpdatedAt(LocalDateTime.now());
        Category savedCategory = categoryRepository.save(category);

        return new ApiResponse<>(
                true,
                "Category created successfully",
                "Kategori başarıyla oluşturuldu",
                categoryMapper.toDto(savedCategory)
        );
    }

    @Override
    public ApiResponse<CategoryResponseDto> update(
            EditOrAddCategoryRequestDto req,
            UUID categoryId
    ) {

        return categoryRepository.findById(categoryId)
                .map(category -> {

                    category.setNameEn(req.getNameEn());
                    category.setNameTr(req.getNameTr());
                    category.setDescriptionEn(req.getDescriptionEn());
                    category.setDescriptionTr(req.getDescriptionTr());
                    category.setUpdatedAt(LocalDateTime.now());

                    Category updatedCategory = categoryRepository.save(category);

                    return new ApiResponse<>(
                            true,
                            "Category updated successfully",
                            "Kategori başarıyla güncellendi",
                            categoryMapper.toDto(updatedCategory)
                    );
                })
                .orElseGet(() -> new ApiResponse<>(
                        false,
                        "Category not found",
                        "Kategori bulunamadı",
                        null
                ));
    }

    @Override
    public ApiResponse<Void> delete(UUID categoryId) {

        return categoryRepository.findById(categoryId)
                .map(category -> {

                    category.setActive(false);
                    category.setUpdatedAt(LocalDateTime.now());

                    categoryRepository.save(category);

                    return new ApiResponse<Void>(
                            true,
                            "Category deleted successfully",
                            "Kategori başarıyla silindi",
                            null
                    );
                })
                .orElseGet(() -> new ApiResponse<>(
                        false,
                        "Category not found",
                        "Kategori bulunamadı",
                        null
                ));
    }

    @Override
    public ApiResponse<List<CategoryResponseDto>> search(String keyword) {

        List<Category> categories = categoryRepository.search(keyword);

        List<CategoryResponseDto> response = categories.stream()
                .map(categoryMapper::toDto)
                .toList();

        return new ApiResponse<>(
                true,
                "Categories found successfully",
                "Kategoriler başarıyla bulundu",
                response
        );
    }

}
