package com.kailash.BlogApp.controllers;

import com.kailash.BlogApp.dto.BlogDto;
import com.kailash.BlogApp.models.ApiResponse;
import com.kailash.BlogApp.models.PaginatedResponse;
import com.kailash.BlogApp.services.impl.BlogServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

import static com.kailash.BlogApp.utils.Constants.ApiConstants.*;
import static com.kailash.BlogApp.utils.Constants.ColumnNames.*;
import static com.kailash.BlogApp.utils.Constants.SuccessMessages.BLOG_DELETION_SUCCESSFUL;
import static com.kailash.BlogApp.utils.Constants.TableNames.CATEGORY;


@RestController
public class BlogController {

    @Autowired
    private BlogServiceImpl blogService;

    @RequestMapping(
        value = USERS_API_PREFIX + USER_ID_PATH_VARIABLE + BLOGS_API_PREFIX,
        method = RequestMethod.POST,
        consumes = {APPLICATION_JSON},
        produces = {APPLICATION_JSON}
    )
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody BlogDto createBlog(@Valid @RequestBody BlogDto blogDto, @PathVariable(USER_ID) Integer userId){
        return blogService.createBlog(blogDto, userId);
    }

    @RequestMapping(
        value = USERS_API_PREFIX + USER_ID_PATH_VARIABLE + BLOGS_API_PREFIX + BLOG_ID_PATH_VARIABLE,
        method = RequestMethod.PUT,
        consumes = {APPLICATION_JSON},
        produces = {APPLICATION_JSON}
    )
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody BlogDto updateBlog(@Valid @RequestBody BlogDto blogDto, @PathVariable(USER_ID) Integer userId, @PathVariable(BLOG_ID) Integer blogId){
        return blogService.updateBlog(blogDto, userId, blogId);
    }
    @RequestMapping(
        value = USERS_API_PREFIX + USER_ID_PATH_VARIABLE + BLOGS_API_PREFIX + BLOG_ID_PATH_VARIABLE,
        method = RequestMethod.DELETE,
        produces = {APPLICATION_JSON}
    )
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody ApiResponse deleteBlog(@PathVariable(BLOG_ID) Integer blogId, @PathVariable(USER_ID) Integer userId){
        blogService.deleteBlog(blogId, userId);
        return new ApiResponse(BLOG_DELETION_SUCCESSFUL, HttpStatus.OK, true, Map.of(BLOG_ID, blogId));
    }
    @RequestMapping(
        value = BLOGS_API_PREFIX + BLOG_ID_PATH_VARIABLE,
        method = RequestMethod.GET,
        produces = {APPLICATION_JSON}
    )
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody BlogDto getBlog(@PathVariable(BLOG_ID) Integer blogId){
        return blogService.getBlogById(blogId);
    }
    @RequestMapping(
        value = BLOGS_API_PREFIX,
        method = RequestMethod.GET,
        produces = {APPLICATION_JSON}
    )
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody PaginatedResponse<BlogDto> getAllBlogs(@RequestParam(name = PAGE_NUMBER, defaultValue = DEFAULT_PAGE_NUMBER, required = false) Integer pageNumber,
                                                                @RequestParam(name = PAGE_SIZE, defaultValue = DEFAULT_PAGE_SIZE, required = false) Integer pageSize,
                                                                @RequestParam(name = SORT_BY, defaultValue = DEFAULT_BLOG_SORT_FIELD, required = false) String sortBy,
                                                                @RequestParam(name = SORT_DIR, defaultValue = DESC, required = false) String sortDir,
                                                                @RequestParam(name = CATEGORY, required = false) Integer categoryId){
        if(categoryId == null) {
            return blogService.getAllBlogs(pageNumber, pageSize, sortBy, sortDir);
        }else{
            return blogService.getBlogsByCategory(categoryId, pageNumber, pageSize, sortBy, sortDir);
        }
    }

    @RequestMapping(
        value = USERS_API_PREFIX + USER_ID_PATH_VARIABLE + BLOGS_API_PREFIX,
        method = RequestMethod.GET,
        produces = {APPLICATION_JSON}
    )
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody PaginatedResponse<BlogDto> getBlogsByUserId(@PathVariable(USER_ID) Integer userId,
                                                        @RequestParam(name = PAGE_NUMBER, defaultValue = DEFAULT_PAGE_NUMBER, required = false) Integer pageNumber,
                                                        @RequestParam(name = PAGE_SIZE, defaultValue = DEFAULT_PAGE_SIZE, required = false) Integer pageSize,
                                                        @RequestParam(name = SORT_BY, defaultValue = DEFAULT_BLOG_SORT_FIELD, required = false) String sortBy,
                                                        @RequestParam(name = SORT_DIR, defaultValue = DESC, required = false) String sortDir
                                                        ){
        return blogService.getBlogsByUserId(userId, pageNumber, pageSize, sortBy, sortDir);
    }

    @RequestMapping(
            value = BLOGS_API_PREFIX + SEARCH,
            method = RequestMethod.GET,
            produces = {APPLICATION_JSON}
    )
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody PaginatedResponse<BlogDto> searchBlogsByKeyword(@RequestParam(name = PAGE_NUMBER, defaultValue = DEFAULT_PAGE_NUMBER, required = false) Integer pageNumber,
                                                                         @RequestParam(name = PAGE_SIZE, defaultValue = DEFAULT_PAGE_SIZE, required = false) Integer pageSize,
                                                                         @RequestParam(name = SORT_BY, defaultValue = DEFAULT_BLOG_SORT_FIELD, required = false) String sortBy,
                                                                         @RequestParam(name = SORT_DIR, defaultValue = DESC, required = false) String sortDir,
                                                                         @RequestParam(name = KEYWORD) String keyword
                                                                         ){
        return blogService.searchBlogsByKeyword(keyword, pageNumber, pageSize, sortBy, sortDir);
    }

}
