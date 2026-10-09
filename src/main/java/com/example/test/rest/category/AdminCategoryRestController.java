package com.example.test.rest.category;

import com.example.test.dto.ApiResponse;
import com.example.test.dto.category.req.EditOrAddCategoryRequestDto;
import com.example.test.dto.category.res.CategoryResponseDto;
import com.example.test.service.CategoryService;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("api/admin/category")
public class AdminCategoryRestController {
    private final CategoryService categoryService;

    public AdminCategoryRestController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PutMapping("/{id}/edit")
    public ApiResponse<CategoryResponseDto> update(@PathVariable UUID id, EditOrAddCategoryRequestDto req) {
        return categoryService.update(req, id);
    }

    @PostMapping("add")
    public ApiResponse<CategoryResponseDto> create(EditOrAddCategoryRequestDto req) {
        return categoryService.create(req);
    }

    @DeleteMapping("{id}/delete")
    public ApiResponse<Void> delete(@PathVariable UUID id) {
        return categoryService.delete(id);
    }

}
