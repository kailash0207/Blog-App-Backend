package com.kailash.BlogApp.controllers;

import com.kailash.BlogApp.dto.CategoryDto;
import com.kailash.BlogApp.models.ApiResponse;
import com.kailash.BlogApp.models.PaginatedResponse;
import com.kailash.BlogApp.services.impl.CategoryServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

import static com.kailash.BlogApp.utils.Constants.ApiConstants.*;
import static com.kailash.BlogApp.utils.Constants.ColumnNames.CATEGORY_ID;
import static com.kailash.BlogApp.utils.Constants.SuccessMessages.CATEGORY_DELETION_SUCCESSFUL;



@RestController
@RequestMapping(CATEGORIES_API_PREFIX)
public class CategoryController {
    @Autowired
    private CategoryServiceImpl categoryService;
    
    @RequestMapping(
        method = RequestMethod.POST,
        consumes = {APPLICATION_JSON},
        produces = {APPLICATION_JSON}
    )
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody CategoryDto createCategory(@Valid @RequestBody CategoryDto categoryDto){
        return categoryService.createCategory(categoryDto);
    }
    @RequestMapping(
            value = CATEGORY_ID_PATH_VARIABLE,
            method = RequestMethod.PUT,
            consumes = {APPLICATION_JSON},
            produces = {APPLICATION_JSON}
    )
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody CategoryDto updateCategory(@Valid @RequestBody CategoryDto categoryDto, @PathVariable(CATEGORY_ID) Integer category_id){
        return categoryService.updateCategory(categoryDto, category_id);
    }

    @RequestMapping(
            value = CATEGORY_ID_PATH_VARIABLE,
            method = RequestMethod.DELETE,
            produces = {APPLICATION_JSON}
    )
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody ApiResponse deleteCategory(@PathVariable(CATEGORY_ID) Integer category_id){
        categoryService.deleteCategory(category_id);
        return new ApiResponse(CATEGORY_DELETION_SUCCESSFUL, HttpStatus.OK, true, Map.of(CATEGORY_ID, category_id));
    }

    @RequestMapping(
            value = CATEGORY_ID_PATH_VARIABLE,
            method = RequestMethod.GET,
            produces = {APPLICATION_JSON}
    )
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody CategoryDto getCategory(@PathVariable(CATEGORY_ID) Integer category_id){
        return categoryService.getCategoryById(category_id);
    }

    @RequestMapping(
            method = RequestMethod.GET,
            produces = {APPLICATION_JSON}
    )
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody PaginatedResponse<CategoryDto> getAllCategories(@RequestParam(name = PAGE_NUMBER, defaultValue = DEFAULT_PAGE_NUMBER, required = false) Integer pageNumber,
                                                                         @RequestParam(name = PAGE_SIZE, defaultValue = DEFAULT_PAGE_SIZE, required = false) Integer pageSize,
                                                                         @RequestParam(name = SORT_BY, defaultValue = DEFAULT_CATEGORY_SORT_FIELD, required = false) String sortBy,
                                                                         @RequestParam(name = SORT_DIR, defaultValue = ASC, required = false) String sortDir){
        return categoryService.getAllCategories(pageNumber, pageSize, sortBy, sortDir);
    }
    
    
}
