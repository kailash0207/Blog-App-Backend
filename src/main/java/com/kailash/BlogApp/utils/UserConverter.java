package com.kailash.BlogApp.utils;

import com.kailash.BlogApp.dto.UserDto;
import com.kailash.BlogApp.models.Entities.UserEntity;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class UserConverter implements EntityDtoConverter<UserEntity, UserDto> {

    private final ModelMapper modelMapper;

    public UserConverter() {
        this.modelMapper = createModelMapper();
    }

    private ModelMapper createModelMapper() {
        return new ModelMapper();
    }
    @Override
    public UserDto convertToDto(UserEntity entity) {
        return modelMapper.map(entity, UserDto.class);
    }

    @Override
    public UserEntity convertToEntity(UserDto dto) {
        return modelMapper.map(dto, UserEntity.class);
    }
}
