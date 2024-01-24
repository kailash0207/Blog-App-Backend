package com.kailash.BlogApp.services.impl;

import com.kailash.BlogApp.dto.UserDto;
import com.kailash.BlogApp.exceptions.ResourceAlreadyExistsException;
import com.kailash.BlogApp.exceptions.ResourceNotFoundException;
import com.kailash.BlogApp.models.Entities.UserEntity;
import com.kailash.BlogApp.models.PaginatedResponse;
import com.kailash.BlogApp.repositories.UserRepository;
import com.kailash.BlogApp.services.UserService;
import com.kailash.BlogApp.utils.UserConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Map;

import static com.kailash.BlogApp.utils.Constants.ErrorMessages.USER_ALREADY_EXISTS;
import static com.kailash.BlogApp.utils.Constants.ErrorMessages.USER_NOT_FOUND;
import static com.kailash.BlogApp.utils.Constants.ColumnNames.EMAIL_ID;
import static com.kailash.BlogApp.utils.Constants.ColumnNames.USER_ID;
import static java.util.Map.entry;


@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserConverter userConverter;
    @Override
    public UserDto createUser(UserDto user) {
        UserEntity userEntity = userRepository.findByEmailId(user.getEmailId());
        if(userEntity == null) {
            userEntity = userConverter.convertToEntity(user);
            UserEntity createdUser = userRepository.save(userEntity);
            return userConverter.convertToDto(createdUser);
        }else{
            throw new ResourceAlreadyExistsException(HttpStatus.BAD_REQUEST, USER_ALREADY_EXISTS, Map.of(EMAIL_ID, user.getEmailId()));
        }
    }

    @Override
    public UserDto updateUser(UserDto user, Integer id) {
        UserEntity userEntity = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(HttpStatus.NOT_FOUND, USER_NOT_FOUND, Map.ofEntries(entry(USER_ID, id))));
        userEntity.setUserName(user.getUserName());
        userEntity.setPassword(user.getPassword());
        userEntity.setAbout(user.getAbout());
        UserEntity updatedUserEntity = userRepository.save(userEntity);
        return userConverter.convertToDto(updatedUserEntity);
    }

    @Override
    public UserDto getUserById(Integer id) {
        UserEntity userEntity = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(HttpStatus.NOT_FOUND, USER_NOT_FOUND, Map.ofEntries(entry(USER_ID, id))));
        return userConverter.convertToDto(userEntity);
    }

    @Override
    public PaginatedResponse<UserDto> getAllUsers(Integer pageNumber, Integer pageSize, String sortBy, String sortDir) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.Direction.fromString(sortDir), sortBy);
        Page<UserEntity> page = userRepository.findAll(pageable);
        return PaginatedResponse.fromPage(page, userConverter);
    }

    @Override
    public void deleteUser(Integer id) {
        userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(HttpStatus.NOT_FOUND, USER_NOT_FOUND, Map.ofEntries(entry(USER_ID, id))));
        userRepository.deleteById(id);
    }
}
