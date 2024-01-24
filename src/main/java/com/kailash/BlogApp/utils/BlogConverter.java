package com.kailash.BlogApp.utils;

import com.kailash.BlogApp.dto.BlogDto;
import com.kailash.BlogApp.models.Entities.BlogEntity;
import com.kailash.BlogApp.models.Entities.CategoryEntity;
import org.modelmapper.AbstractConverter;
import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class BlogConverter implements EntityDtoConverter<BlogEntity, BlogDto>{


    private final ModelMapper modelMapper;

    public BlogConverter() {
        this.modelMapper = createModelMapper();
    }

    private ModelMapper createModelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.STRICT)
                .setFieldMatchingEnabled(true)
                .setFullTypeMatchingRequired(true)
                .setFieldAccessLevel(Configuration.AccessLevel.PRIVATE);
        modelMapper.addConverter(new CategoryEntityToIntegerConverter());
        modelMapper.typeMap(BlogDto.class, BlogEntity.class).addMappings(mapper -> {
            mapper.skip(BlogEntity::setCategories);
            mapper.skip(BlogEntity::setCreatedBy);
        });

        modelMapper.typeMap(BlogEntity.class, BlogDto.class).addMappings(mapper -> {
            mapper.map(src -> src.getCreatedBy().getUserId(), BlogDto::setCreatedBy);
            mapper.map(src -> src.getCategories() != null ? src.getCategories().stream().map(CategoryEntity::getCategoryId).collect(Collectors.toList()) : null,
                    BlogDto::setCategories);
        });


        return modelMapper;
    }

    @Override
    public BlogDto convertToDto(BlogEntity blogEntity) {
        return modelMapper.map(blogEntity, BlogDto.class);
    }

    @Override
    public BlogEntity convertToEntity(BlogDto blogDto) {
        return modelMapper.map(blogDto, BlogEntity.class);
    }

    @Component
    public static class CategoryEntityToIntegerConverter extends AbstractConverter<CategoryEntity, Integer> {

        @Override
        protected Integer convert(CategoryEntity source) {
            return (source != null) ? source.getCategoryId() : null;
        }
    }
}


