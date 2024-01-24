package com.kailash.BlogApp.utils;


import com.kailash.BlogApp.dto.CategoryDto;
import com.kailash.BlogApp.models.Entities.CategoryEntity;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class CategoryConverter implements EntityDtoConverter<CategoryEntity, CategoryDto> {

    private final ModelMapper modelMapper;
    public CategoryConverter() {
        this.modelMapper = createModelMapper();
    }

    private ModelMapper createModelMapper() {
        return new ModelMapper();
    }
    @Override
    public CategoryDto convertToDto(CategoryEntity entity) {
        return modelMapper.map(entity, CategoryDto.class);
    }

    @Override
    public CategoryEntity convertToEntity(CategoryDto dto) {
        return modelMapper.map(dto, CategoryEntity.class);
    }
}
