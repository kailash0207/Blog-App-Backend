package com.kailash.BlogApp.services.impl;

import com.kailash.BlogApp.dto.CategoryDto;
import com.kailash.BlogApp.exceptions.ResourceAlreadyExistsException;
import com.kailash.BlogApp.exceptions.ResourceNotFoundException;
import com.kailash.BlogApp.models.Entities.CategoryEntity;
import com.kailash.BlogApp.models.PaginatedResponse;
import com.kailash.BlogApp.repositories.CategoryRepository;
import com.kailash.BlogApp.services.CategoryService;
import com.kailash.BlogApp.utils.CategoryConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Map;

import static com.kailash.BlogApp.utils.Constants.ErrorMessages.CATEGORY_ALREADY_EXISTS;
import static com.kailash.BlogApp.utils.Constants.ErrorMessages.CATEGORY_NOT_FOUND;
import static com.kailash.BlogApp.utils.Constants.ColumnNames.CATEGORY_ID;
import static com.kailash.BlogApp.utils.Constants.ColumnNames.CATEGORY_NAME;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private CategoryConverter categoryConverter;
    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) {
        CategoryEntity categoryEntity = categoryRepository.findByCategoryName(categoryDto.getCategoryName());
        if(categoryEntity == null){
            categoryEntity = categoryConverter.convertToEntity(categoryDto);
            CategoryEntity createdCategory = categoryRepository.save(categoryEntity);
            return categoryConverter.convertToDto(createdCategory);
        }
        else{
            throw new ResourceAlreadyExistsException(HttpStatus.BAD_REQUEST, CATEGORY_ALREADY_EXISTS, Map.of(CATEGORY_NAME, categoryDto.getCategoryName()));
        }
    }

    @Override
    public CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId) {
        CategoryEntity categoryEntity = categoryRepository.findById(categoryId).orElseThrow(()->new ResourceNotFoundException(HttpStatus.NOT_FOUND, CATEGORY_NOT_FOUND, Map.of(CATEGORY_ID, categoryId)));
        if(categoryEntity.getCategoryName().equals(categoryDto.getCategoryName())){
            categoryEntity.setCategoryDescription(categoryDto.getCategoryDescription());
            CategoryEntity updatedCategory = categoryRepository.save(categoryEntity);
            return categoryConverter.convertToDto(updatedCategory);
        }else{
            categoryDto.setCategoryId(categoryEntity.getCategoryId());
            return createCategory(categoryDto);
        }
    }

    @Override
    public CategoryDto getCategoryById(Integer categoryId) {
        CategoryEntity categoryEntity = categoryRepository.findById(categoryId).orElseThrow(()->new ResourceNotFoundException(HttpStatus.NOT_FOUND, CATEGORY_NOT_FOUND, Map.of(CATEGORY_ID, categoryId)));
        return categoryConverter.convertToDto(categoryEntity);
    }

    @Override
    public PaginatedResponse<CategoryDto> getAllCategories(Integer pageNumber, Integer pageSize, String sortBy, String sortDir) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.Direction.fromString(sortDir), sortBy);
        Page<CategoryEntity> page = categoryRepository.findAll(pageable);
        return PaginatedResponse.fromPage(page, categoryConverter);
    }

    @Override
    public void deleteCategory(Integer categoryId) {
        getCategoryById(categoryId);
        categoryRepository.deleteById(categoryId);
    }
}
