package com.example.test.service;

import com.example.test.dto.ApiResponse;
import com.example.test.dto.category.req.EditOrAddCategoryRequestDto;
import com.example.test.dto.category.res.CategoryResponseDto;

import java.util.List;
import java.util.UUID;

public interface CategoryService {
    ApiResponse<List<CategoryResponseDto>> getAllCategories();

    ApiResponse<CategoryResponseDto> getCategoryById(UUID categoryId);

    public ApiResponse<List<CategoryResponseDto>> search(String keyword);

    ApiResponse<CategoryResponseDto> create(EditOrAddCategoryRequestDto req);

    ApiResponse<CategoryResponseDto> update(EditOrAddCategoryRequestDto req, UUID categoryId);

    ApiResponse<Void> delete(UUID categoryId);

}
