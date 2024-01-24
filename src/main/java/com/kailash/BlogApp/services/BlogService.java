package com.kailash.BlogApp.services;

import com.kailash.BlogApp.dto.BlogDto;
import com.kailash.BlogApp.models.PaginatedResponse;


public interface BlogService {
    BlogDto createBlog(BlogDto blogDto, Integer userId);
    BlogDto updateBlog(BlogDto blogDto, Integer userId, Integer blogId);
    void deleteBlog(Integer blogId, Integer userId);
    BlogDto getBlogById(Integer blogId);
    PaginatedResponse<BlogDto> getAllBlogs(Integer pageNumber, Integer pageSize, String sortBy, String sortDir);
    PaginatedResponse<BlogDto> getBlogsByUserId(Integer userId, Integer pageNumber, Integer pageSize, String sortBy, String sortDir);
    PaginatedResponse<BlogDto> getBlogsByCategory(Integer categoryId, Integer pageNumber, Integer pageSize, String sortBy, String sortDir);
    PaginatedResponse<BlogDto> searchBlogsByKeyword(String keyword, Integer pageNumber, Integer pageSize, String sortBy, String sortDir);
}
