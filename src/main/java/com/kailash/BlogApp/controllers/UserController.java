package com.kailash.BlogApp.controllers;


import com.kailash.BlogApp.dto.UserDto;
import com.kailash.BlogApp.models.ApiResponse;
import com.kailash.BlogApp.models.PaginatedResponse;
import com.kailash.BlogApp.services.impl.UserServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

import static com.kailash.BlogApp.utils.Constants.ApiConstants.*;
import static com.kailash.BlogApp.utils.Constants.ColumnNames.USER_ID;
import static com.kailash.BlogApp.utils.Constants.SuccessMessages.USER_DELETION_SUCCESSFUL;


@RestController
@RequestMapping(USERS_API_PREFIX)
public class UserController {

    @Autowired
    private UserServiceImpl userService;
    @RequestMapping(
        method = RequestMethod.POST,
        consumes = {APPLICATION_JSON},
        produces = {APPLICATION_JSON}
    )
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody UserDto createUser(@Valid @RequestBody UserDto userDto){
        return userService.createUser(userDto);
    }

    @RequestMapping(
        value = USER_ID_PATH_VARIABLE,
        method = RequestMethod.PUT,
        consumes = {APPLICATION_JSON},
        produces = {APPLICATION_JSON}
    )
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody UserDto updateUser(@Valid @RequestBody UserDto userDto, @PathVariable(USER_ID) Integer user_id){
        return userService.updateUser(userDto, user_id);
    }

    @RequestMapping(
        value = USER_ID_PATH_VARIABLE,
        method = RequestMethod.DELETE,
        produces = {APPLICATION_JSON}
    )
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody ApiResponse deleteUser(@PathVariable(USER_ID) Integer user_id){
        userService.deleteUser(user_id);
        return new ApiResponse(USER_DELETION_SUCCESSFUL, HttpStatus.OK, true, Map.of(USER_ID, user_id));
    }

    @RequestMapping(
        value = USER_ID_PATH_VARIABLE,
        method = RequestMethod.GET,
        produces = {APPLICATION_JSON}
    )
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody UserDto getUser(@PathVariable(USER_ID) Integer user_id){
        return userService.getUserById(user_id);
    }

    @RequestMapping(
        method = RequestMethod.GET,
        produces = {APPLICATION_JSON}
    )
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody PaginatedResponse<UserDto> getAllUsers(@RequestParam(name = PAGE_NUMBER, defaultValue = DEFAULT_PAGE_NUMBER, required = false) Integer pageNumber,
                                                                @RequestParam(name = PAGE_SIZE, defaultValue = DEFAULT_PAGE_SIZE, required = false) Integer pageSize,
                                                                @RequestParam(name = SORT_BY, defaultValue = DEFAULT_USER_SORT_FIELD, required = false) String sortBy,
                                                                @RequestParam(name = SORT_DIR, defaultValue = ASC, required = false) String sortDir){
        return userService.getAllUsers(pageNumber, pageSize, sortBy, sortDir);
    }
}
