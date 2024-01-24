package com.kailash.BlogApp.services;

import com.kailash.BlogApp.dto.CategoryDto;
import com.kailash.BlogApp.models.PaginatedResponse;


public interface CategoryService {
    CategoryDto createCategory(CategoryDto categoryDto);
    CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId);
    CategoryDto getCategoryById(Integer categoryId);
    PaginatedResponse<CategoryDto> getAllCategories(Integer pageNumber, Integer pageSize, String sortBy, String sortDir);
    void deleteCategory(Integer categoryId);
}
