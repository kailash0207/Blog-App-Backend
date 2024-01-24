package com.kailash.BlogApp.services.impl;

import com.kailash.BlogApp.dto.BlogDto;
import com.kailash.BlogApp.exceptions.ActionNotAllowedException;
import com.kailash.BlogApp.exceptions.ResourceNotFoundException;
import com.kailash.BlogApp.models.Entities.BlogEntity;
import com.kailash.BlogApp.models.Entities.UserEntity;
import com.kailash.BlogApp.models.PaginatedResponse;
import com.kailash.BlogApp.repositories.BlogRepository;
import com.kailash.BlogApp.repositories.CategoryRepository;
import com.kailash.BlogApp.repositories.UserRepository;
import com.kailash.BlogApp.services.BlogService;
import com.kailash.BlogApp.utils.BlogConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.kailash.BlogApp.utils.Constants.ErrorMessages.*;
import static com.kailash.BlogApp.utils.Constants.ColumnNames.*;
import static java.util.Map.entry;

@Service
public class BlogServiceImpl implements BlogService {
    @Autowired
    private BlogRepository blogRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private BlogConverter blogConverter;
    @Override
    public BlogDto createBlog(BlogDto blogDto, Integer userId) {
        UserEntity userEntity = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException(HttpStatus.NOT_FOUND, USER_NOT_FOUND, Map.ofEntries(entry(USER_ID, userId))));
        BlogEntity blogEntity = blogConverter.convertToEntity(blogDto);
        blogEntity.setCreatedBy(userEntity);
        blogEntity.setCategories(blogDto.getCategories().stream().map(categoryId -> categoryRepository.findById(categoryId).orElseThrow(()->new ResourceNotFoundException(HttpStatus.NOT_FOUND, CATEGORY_NOT_FOUND, Map.of(CATEGORY_ID, categoryId)))).toList());
        BlogEntity createdBlog = blogRepository.save(blogEntity);
        return blogConverter.convertToDto(createdBlog);
    }

    @Override
    public BlogDto updateBlog(BlogDto blogDto, Integer userId, Integer blogId) {
        userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException(HttpStatus.NOT_FOUND, USER_NOT_FOUND, Map.ofEntries(entry(USER_ID, userId))));
        BlogEntity blogEntity = blogRepository.findById(blogId).orElseThrow(() -> new ResourceNotFoundException(HttpStatus.NOT_FOUND, BLOG_NOT_FOUND, Map.ofEntries(entry(BLOG_ID, blogId))));
        if(Objects.equals(blogEntity.getCreatedBy().getUserId(), userId)){
            blogEntity.setBlogTitle(blogDto.getBlogTitle());
            blogEntity.setBlogContent(blogDto.getBlogContent());
            blogEntity.setCategories(blogDto.getCategories().stream().map(categoryId -> categoryRepository.findById(categoryId).orElseThrow(()->new ResourceNotFoundException(HttpStatus.NOT_FOUND, CATEGORY_NOT_FOUND, Map.of(CATEGORY_ID, categoryId)))).collect(Collectors.toList()));
            blogEntity.setImageUrl(blogDto.getImageUrl());
            BlogEntity updatedBlog = blogRepository.save(blogEntity);
            return blogConverter.convertToDto(updatedBlog);
        }
        else{
            throw new ActionNotAllowedException(HttpStatus.FORBIDDEN, BLOG_UPDATE_ALLOWED_TO_OWNER_ONLY, Map.ofEntries(entry(USER_ID, userId), entry(CREATED_BY, blogEntity.getCreatedBy().getUserId())));
        }

    }

    @Override
    public void deleteBlog(Integer blogId, Integer userId) {
        userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException(HttpStatus.NOT_FOUND, USER_NOT_FOUND, Map.ofEntries(entry(USER_ID, userId))));
        BlogEntity blogEntity = blogRepository.findById(blogId).orElseThrow(() -> new ResourceNotFoundException(HttpStatus.NOT_FOUND, BLOG_NOT_FOUND, Map.ofEntries(entry(BLOG_ID, blogId))));
        if(Objects.equals(blogEntity.getCreatedBy().getUserId(), userId)){
            blogRepository.deleteById(blogId);
        }
        else{
            throw new ActionNotAllowedException(HttpStatus.FORBIDDEN, BLOG_DELETION_ALLOWED_TO_OWNER_ONLY, Map.ofEntries(entry(USER_ID, userId), entry(CREATED_BY, blogEntity.getCreatedBy().getUserId())));
        }

    }

    @Override
    public BlogDto getBlogById(Integer blogId) {
        BlogEntity blogEntity = blogRepository.findById(blogId).orElseThrow(() -> new ResourceNotFoundException(HttpStatus.NOT_FOUND, BLOG_NOT_FOUND, Map.ofEntries(entry(BLOG_ID, blogId))));
        return blogConverter.convertToDto(blogEntity);
    }

    @Override
    public PaginatedResponse<BlogDto> getAllBlogs(Integer pageNumber, Integer pageSize, String sortBy, String sortDir) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.Direction.fromString(sortDir), sortBy);
        Page<BlogEntity> page = blogRepository.findAll(pageable);
        return PaginatedResponse.fromPage(page, blogConverter);
    }

    @Override
    public PaginatedResponse<BlogDto> getBlogsByUserId(Integer userId, Integer pageNumber, Integer pageSize, String sortBy, String sortDir) {
        userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException(HttpStatus.NOT_FOUND, USER_NOT_FOUND, Map.ofEntries(entry(USER_ID, userId))));
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.Direction.fromString(sortDir), sortBy);
        Page<BlogEntity> page = blogRepository.findByCreatedBy_UserId(userId, pageable);
        return PaginatedResponse.fromPage(page, blogConverter);
    }

    @Override
    public PaginatedResponse<BlogDto> getBlogsByCategory(Integer categoryId, Integer pageNumber, Integer pageSize, String sortBy, String sortDir) {
        categoryRepository.findById(categoryId).orElseThrow(()->new ResourceNotFoundException(HttpStatus.NOT_FOUND, CATEGORY_NOT_FOUND, Map.of(CATEGORY_ID, categoryId)));
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.Direction.fromString(sortDir), sortBy);
        Page<BlogEntity> page = blogRepository.findByCategories_CategoryId(categoryId, pageable);
        return PaginatedResponse.fromPage(page, blogConverter);
    }

    @Override
    public PaginatedResponse<BlogDto> searchBlogsByKeyword(String keyword, Integer pageNumber, Integer pageSize, String sortBy, String sortDir) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.Direction.fromString(sortDir), sortBy);
        Page<BlogEntity> page = blogRepository.findByBlogTitleIgnoreCaseContainingOrBlogContentIgnoreCaseContaining(keyword, keyword, pageable);
        return PaginatedResponse.fromPage(page, blogConverter);
    }
}
