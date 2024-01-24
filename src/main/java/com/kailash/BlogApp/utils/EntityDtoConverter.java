package com.kailash.BlogApp.utils;

public interface EntityDtoConverter<E, D> {

    D convertToDto(E entity);

    E convertToEntity(D dto);
}