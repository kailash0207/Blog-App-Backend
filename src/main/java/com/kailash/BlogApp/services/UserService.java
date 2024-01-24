package com.kailash.BlogApp.services;

import com.kailash.BlogApp.dto.UserDto;
import com.kailash.BlogApp.models.PaginatedResponse;

public interface UserService {

    UserDto createUser(UserDto user);
    UserDto updateUser(UserDto user, Integer id);
    UserDto getUserById(Integer id);
    PaginatedResponse<UserDto> getAllUsers(Integer pageNumber, Integer pageSize, String sortBy, String sortDir);
    void deleteUser(Integer id);


}
