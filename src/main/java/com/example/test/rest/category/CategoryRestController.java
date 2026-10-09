package com.example.test.rest.category;

import com.example.test.dto.ApiResponse;
import com.example.test.dto.category.res.CategoryResponseDto;
import com.example.test.service.CategoryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/category")

public class CategoryRestController {
    private final CategoryService categoryService;

    public CategoryRestController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("list")
    public ApiResponse<?> list() {
        return categoryService.getAllCategories();
    }

    @GetMapping("search")
    public ApiResponse<List<CategoryResponseDto>> search(@RequestParam String keyword) {
        return categoryService.search(keyword);
    }
}
